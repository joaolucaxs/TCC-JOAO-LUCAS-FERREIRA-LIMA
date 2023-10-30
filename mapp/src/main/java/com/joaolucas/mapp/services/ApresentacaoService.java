package com.joaolucas.mapp.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.joaolucas.mapp.dtos.ApresentacaoDTOForm;
import com.joaolucas.mapp.dtos.PecaDTOForm;
import com.joaolucas.mapp.model.Apresentacao;
import com.joaolucas.mapp.model.FichaTecnicaObra;
import com.joaolucas.mapp.model.Image;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.repositories.ApresentacaoRepository;
import com.joaolucas.mapp.services.exceptions.DataBaseException;
import com.joaolucas.mapp.services.exceptions.ResourceNotFoundException;

@Service
public class ApresentacaoService {

	@Autowired
	private ApresentacaoRepository repo;


	public List<Apresentacao> findAll() {
		return repo.findAll();
	}

	public Apresentacao findById(String id) {
		Optional<Apresentacao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Apresentacao com ID " + id + " não foi encontrado."));
	}

	public Apresentacao novaApresentacao(Apresentacao obj) {
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

	public Apresentacao update(String id, Apresentacao obj) {
		try {
			Apresentacao newObj = findById(id);
			updateData(obj, newObj);
			return repo.save(newObj);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Apresentacao com ID " + id + " não foi encontrado.");
		}
	}

	private void updateData(Apresentacao obj, Apresentacao newObj) {
		newObj.setTituloApresentacao(obj.getTituloApresentacao());
		newObj.setDescricaoApresentacao(obj.getDescricaoApresentacao());
		newObj.setDataCriacao(obj.getDataCriacao());
	}

	public List<Apresentacao> filtrarPorCampo(String filtro, String pesquisa) {
		if (filtro.equals("dataCriacao")) {
			return filtrarPorDataCriacao(stringToLocalDate(pesquisa));
		}

		return repo.filtrarPorCampo(pesquisa);
	}

	public List<Apresentacao> filtrarPorDataCriacao(LocalDate dataCriacao) {
		return repo.filtrarPorDataCriacao(dataCriacao);
	}
	
	public LocalDate stringToLocalDate(String data) {
		DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataCriacao = LocalDate.parse(data, formatterInput);
		return dataCriacao;
	}
	
	public Apresentacao fromDTOFormulario(ApresentacaoDTOForm objDTO) throws IOException {
		Apresentacao apresentacao = new Apresentacao();
		
		
		apresentacao.setTituloApresentacao(objDTO.getTituloApresentacao());
		apresentacao.setDescricaoApresentacao(objDTO.getDescricaoApresentacao());
		apresentacao.setDataCriacao(LocalDate.now());

		Image imagemCapa = new Image(new Binary(BsonBinarySubType.BINARY, objDTO.getImagemApresentacaoFile().getBytes()));
		apresentacao.setImagemCapa(imagemCapa);
		return apresentacao;
	}

}
