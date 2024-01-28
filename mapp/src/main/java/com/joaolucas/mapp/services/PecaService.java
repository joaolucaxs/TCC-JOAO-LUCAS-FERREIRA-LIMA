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

import com.joaolucas.mapp.dtos.PecaDTOForm;
import com.joaolucas.mapp.model.FichaTecnicaObra;
import com.joaolucas.mapp.model.Image;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.repositories.PecaRepository;
import com.joaolucas.mapp.services.exceptions.DataBaseException;
import com.joaolucas.mapp.services.exceptions.ResourceNotFoundException;
import com.joaolucas.mapp.utils.Utils;

@Service
public class PecaService {

	@Autowired
	private PecaRepository repo;

	@Autowired
	private ArtistaService serviceArtista;

	public List<Peca> findAll() {
		return repo.findAll();
	}

	public Peca findById(String id) {
		Optional<Peca> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Obra com ID " + id + " não foi encontrado."));
	}

	public Peca insert(Peca obj) {
		return repo.save(obj);
	}

	public void delete(String id) {
		try {
			findById(id);
			Peca peca = findById(id);
			peca.getArtesao().getListaObras().remove(peca);
			serviceArtista.updateObras(peca.getArtesao());
			serviceArtista.insert(peca.getArtesao());
			if (peca.getArtesao().getListaObras().isEmpty()) {
				serviceArtista.delete(peca.getArtesao().getId());
			}
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Obra com ID " + id + " não foi encontrado.");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public Peca update(String id, Peca obj) {
		try {
			Peca newObj = findById(id);
			updateData(obj, newObj);
			return repo.save(newObj);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Obra com ID " + id + " não foi encontrado.");
		}
	}

	private void updateData(Peca obj, Peca newObj) {
		newObj.setTituloPeca(obj.getTituloPeca());
		newObj.setTipologia(obj.getTipologia());
		newObj.setFormaAssociativa(obj.getFormaAssociativa());
		newObj.setRelacaoCultural(obj.getRelacaoCultural());
		newObj.setTecnica(obj.getTecnica());
		newObj.setClassificacao(obj.getClassificacao());
		newObj.setProduto(obj.getProduto());
		newObj.setFichatecnica(obj.getFichatecnica());
	}

	public List<Peca> filtrarPorCampo(String filtro, String pesquisa) {
		if (filtro.equals("dataAquisicao")) {
			return filtrarPorDataAquisicao(Utils.stringToLocalDate(pesquisa));
		}
		if (filtro.equals("assinada")) {
			return filtrarPorAssinada(Utils.stringToBoolean(pesquisa));
		}
		if (filtro.equals("datada")) {
			return filtrarPorDatada(Utils.stringToBoolean(pesquisa));
		}

		return repo.filtrarPorCampo(pesquisa);
	}

	public List<Peca> filtrarPorDataAquisicao(LocalDate dataAquisicao) {
		return repo.filtrarPorDataAquisicao(dataAquisicao);
	}

	public List<Peca> filtrarPorAssinada(Boolean assinada) {
		return repo.filtrarPorAssinada(assinada);
	}

	public List<Peca> filtrarPorDatada(Boolean datada) {
		return repo.filtrarPorDatada(datada);
	}

	public Peca fromDTOFormulario(PecaDTOForm objDTO) throws IOException {
		Peca peca = new Peca();
		peca.setTituloPeca(objDTO.getTituloPeca());
		peca.setTipologia(objDTO.getTipologia());
		peca.setFormaAssociativa(objDTO.getFormaAssociativa());
		peca.setRelacaoCultural(objDTO.getRelacaoCultural());
		peca.setTecnica(objDTO.getTecnica());
		peca.setClassificacao(objDTO.getClassificacao());
		peca.setProduto(objDTO.getProduto());

		Image imagemCapa = new Image(new Binary(BsonBinarySubType.BINARY, objDTO.getImagemPecaFile().getBytes()));
		FichaTecnicaObra fichaTecnicaObra = new FichaTecnicaObra(imagemCapa, objDTO.getCodigoPeca(),
				objDTO.getDataAquisicao(), objDTO.getAssinada(), objDTO.getDatada(), objDTO.getDimensao(),
				objDTO.getPeso());

		peca.setFichatecnica(fichaTecnicaObra);
		return peca;
	}

}
