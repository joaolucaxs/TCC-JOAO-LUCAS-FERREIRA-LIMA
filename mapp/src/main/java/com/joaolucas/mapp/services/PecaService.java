package com.joaolucas.mapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.joaolucas.mapp.dtos.ArtistaDTO;
import com.joaolucas.mapp.dtos.PecaDTO;
import com.joaolucas.mapp.model.Artista;
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
	
//	public List<PecaDTO> findByFichaTecnicaTipoImagem(String tipoImagem){
//		return repo.findByFichaTecnicaTipoImagem(tipoImagem);
//	}
//	
//	public List<PecaDTO> findByFichaTecnicaCodigoPeca(String codigoPeca){
//		return repo.findByFichaTecnicaCodigoPeca(codigoPeca);
//	}
//	
//	public List<PecaDTO> findByFichaTecnicaDataAquisicao(Date dataAquisicao){
//		return repo.findByFichaTecnicaDataAquisicao(dataAquisicao);
//	}
//	
//	public List<PecaDTO> findByFichaTecnicaAssinada(Boolean assinada){
//		return repo.findByFichaTecnicaAssinada(assinada);
//	}
//	
//	public List<PecaDTO> findByFichaTecnicaDatada(Boolean datada){
//		return repo.findByFichaTecnicaDatada(datada);
//	}
//	public List<PecaDTO> findByFichaTecnicaDimensao(String dimensao){
//		return repo.findByFichaTecnicaDimensao(dimensao);
//	}
//	public List<PecaDTO> findByFichaTecnicaPeso(String peso){
//		return repo.findByFichaTecnicaPeso(peso);
//	}

}
