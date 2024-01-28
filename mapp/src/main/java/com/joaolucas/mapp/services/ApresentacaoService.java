package com.joaolucas.mapp.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.joaolucas.mapp.dtos.ApresentacaoDTOForm;
import com.joaolucas.mapp.model.Apresentacao;
import com.joaolucas.mapp.model.Image;
import com.joaolucas.mapp.repositories.ApresentacaoRepository;
import com.joaolucas.mapp.resources.util.QRCodeUtil;
import com.joaolucas.mapp.services.exceptions.DataBaseException;
import com.joaolucas.mapp.services.exceptions.ResourceNotFoundException;
import com.joaolucas.mapp.utils.Utils;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ApresentacaoService {

	@Autowired
	private ApresentacaoRepository repo;

	public List<Apresentacao> findAll() {
		return repo.findAll();
	}

	public Apresentacao findById(String id) {
		Optional<Apresentacao> obj = repo.findById(id);
		obj.get().setQrCode(QRCodeUtil.generateByteQRCode(obj.get(), 250, 250));
		return obj
				.orElseThrow(() -> new ResourceNotFoundException("Apresentacao com ID " + id + " não foi encontrado."));
	}

	public Apresentacao insert(Apresentacao obj) {
		return repo.save(obj);
	}

	public void delete(String id) {
		try {
			findById(id);
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Apresentacao com ID " + id + " não foi encontrado.");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public List<Apresentacao> filtrarPorCampo(String filtro, String pesquisa) {
		if (filtro.equals("dataCriacao")) {
			return filtrarPorDataCriacao(Utils.stringToLocalDate(pesquisa));
		}
		return repo.filtrarPorCampo(pesquisa);
	}

	public List<Apresentacao> filtrarPorDataCriacao(LocalDate dataCriacao) {
		return repo.filtrarPorDataCriacao(dataCriacao);
	}

	public Apresentacao fromDTOFormulario(ApresentacaoDTOForm objDTO) throws IOException {
		Apresentacao apresentacao = new Apresentacao();
		apresentacao.setTituloApresentacao(objDTO.getTituloApresentacao());
		apresentacao.setDescricaoApresentacao(objDTO.getDescricaoApresentacao());
		apresentacao.setDataCriacao(LocalDate.now());

		Image imagemCapa = new Image(
				new Binary(BsonBinarySubType.BINARY, objDTO.getImagemApresentacaoFile().getBytes()));
		apresentacao.setImagemCapa(imagemCapa);
		return apresentacao;
	}
	
	public void visualizarQrCode(HttpServletResponse response, Apresentacao apresentacao) throws IOException {
		response.setContentType("image/png");
		response.setHeader("Content-Disposition", "inline; filename=" + apresentacao.getTituloApresentacao());
		response.getOutputStream().write(apresentacao.getQrCode());
		response.flushBuffer();
	}
}
