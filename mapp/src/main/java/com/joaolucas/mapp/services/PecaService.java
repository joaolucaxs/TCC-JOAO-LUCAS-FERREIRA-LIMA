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

import com.joaolucas.mapp.dtos.ArtistaDTO;
import com.joaolucas.mapp.dtos.PecaDTO;
import com.joaolucas.mapp.dtos.PecaDTOFormulario;
import com.joaolucas.mapp.model.Artista;
import com.joaolucas.mapp.model.FichaTecnicaObra;
import com.joaolucas.mapp.model.Image;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.repositories.PecaRepository;
import com.joaolucas.mapp.services.exceptions.DataBaseException;
import com.joaolucas.mapp.services.exceptions.ResourceNotFoundException;

@Service
public class PecaService {

	@Autowired
	private PecaRepository repo;

	public List<Peca> findAll() {
		return repo.findAll();
	}

	public Peca findById(String id) {
		Optional<Peca> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Peca insert(Peca obj) {
		return repo.save(obj);
	}

	public void delete(String id) {
		try {
			findById(id);
//			Peca peca = findById(id);
//			Artista artista = fromDTO(peca.getArtesao());
//			artista.getListaObras().remove(peca);
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
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
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Peca obj, Peca newObj) {
		newObj.setArtesao(obj.getArtesao());
		newObj.setTituloPeca(obj.getTituloPeca());
		newObj.setTipologia(obj.getTipologia());
		newObj.setFormaAssociativa(obj.getFormaAssociativa());
		newObj.setRelacaoCultural(obj.getRelacaoCultural());
		newObj.setTecnica(obj.getTecnica());
		newObj.setClassificacao(obj.getClassificacao());
		newObj.setProduto(obj.getProduto());
		newObj.setFichatecnica(obj.getFichatecnica());

	}

	public Peca fromDTO(PecaDTO objDTO) {
		return new Peca(objDTO.getId(), objDTO.getArtesao(), objDTO.getTituloPeca(), objDTO.getTipologia(),
				objDTO.getFormaAssociativa(), objDTO.getRelacaoCultural(), objDTO.getTecnica(),
				objDTO.getClassificacao(), objDTO.getProduto(), objDTO.getFichatecnica());
	}

	public Artista fromDTO(ArtistaDTO objDTO) {
		return new Artista(objDTO.getId(), objDTO.getNome(), objDTO.getApelido(), objDTO.getTelefone(),
				objDTO.getEmail(), objDTO.getCidade());
	}

	public List<PecaDTO> filtrarPorCampo(String pesquisa) {
		return repo.filtrarPorCampo(pesquisa);

	}

	public List<PecaDTO> filtrarPorDataAquisicao(LocalDate dataAquisicao) {
		return repo.filtrarPorDataAquisicao(dataAquisicao);
	}

	public List<PecaDTO> filtrarPorAssinada(Boolean assinada) {
		return repo.filtrarPorAssinada(assinada);
	}

	public List<PecaDTO> filtrarPorDatada(Boolean datada) {
		return repo.filtrarPorDatada(datada);
	}

	public LocalDate stringToLocalDate(String data) {
		DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataAquisicao = LocalDate.parse(data, formatterInput);
		return dataAquisicao;
	}

	public boolean stringToBoolean(String input) {
		String lowerCaseInput = input.toLowerCase(); // Transforma para minúsculas para ignorar caixa
		lowerCaseInput = lowerCaseInput.replaceAll("[áàâã]", "a"); // Remove acentos de 'a'
		lowerCaseInput = lowerCaseInput.replaceAll("[éèê]", "e"); // Remove acentos de 'e'
		lowerCaseInput = lowerCaseInput.replaceAll("[íìî]", "i"); // Remove acentos de 'i'
		lowerCaseInput = lowerCaseInput.replaceAll("[óòôõ]", "o"); // Remove acentos de 'o'
		lowerCaseInput = lowerCaseInput.replaceAll("[úùû]", "u"); // Remove acentos de 'u'

		return lowerCaseInput.equals("sim");
	}

	public Peca novaObra(Peca obj) {
		return repo.save(obj);
	}

	public Peca fromDTOFormulario(PecaDTOFormulario objDTO, ArtistaDTO artista) throws IOException {
		Peca peca = new Peca();
		peca.setId(objDTO.getId());
		peca.setArtesao(artista);
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
