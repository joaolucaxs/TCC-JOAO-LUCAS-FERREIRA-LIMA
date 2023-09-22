package com.joaolucas.mapp.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.joaolucas.mapp.dtos.ArtistaDTO;
import com.joaolucas.mapp.model.Artista;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.repositories.ArtistaRepository;
import com.joaolucas.mapp.repositories.PecaRepository;
import com.joaolucas.mapp.services.exceptions.DataBaseException;
import com.joaolucas.mapp.services.exceptions.ResourceNotFoundException;

@Service
public class ArtistaService {

	@Autowired
	private ArtistaRepository repo;

	@Autowired
	private PecaRepository repoObra;

	public List<Artista> findAll() {
		return repo.findAll();
	}

	public List<ArtistaDTO> findAllDto() {
		return findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private ArtistaDTO convertToDTO(Artista peca) {
		ArtistaDTO artistaDTO = new ArtistaDTO();
		artistaDTO.setNome(peca.getNome());
		artistaDTO.setApelido(peca.getApelido());
		artistaDTO.setTelefone(peca.getTelefone());
		artistaDTO.setEmail(peca.getEmail());
		artistaDTO.setCidade(peca.getCidade());
		return artistaDTO;
	}

	public Artista findById(String id) {
		Optional<Artista> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Artista insert(Artista obj) {
		return repo.save(obj);
	}

	public void delete(String id) {
		try {
			findById(id);
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public Artista update(String idOldArtista, Artista newEditArtista) {
		try {
			Artista oldArtista = findById(idOldArtista);
			updateData(oldArtista, newEditArtista);
			return repo.save(oldArtista);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idOldArtista);
		}
	}

	private void updateData(Artista oldArtista, Artista newEditArtista) {
		oldArtista.setNome(newEditArtista.getNome());
		oldArtista.setApelido(newEditArtista.getApelido());
		oldArtista.setTelefone(newEditArtista.getTelefone());
		oldArtista.setEmail(newEditArtista.getEmail());
		oldArtista.setCidade(newEditArtista.getCidade());

		updateObras(oldArtista);

	}

	public void updateObras(Artista artistaUpdateObras) {

		List<Peca> atualizarObras = artistaUpdateObras.getListaObras();

		for (Peca peca : atualizarObras) {
			peca.setArtesao(artistaUpdateObras);
			repoObra.save(peca);
		}
	}

	public Artista fromDTO(ArtistaDTO objDTO) {
		Artista artista = new Artista();
		artista.setNome(objDTO.getNome());
		artista.setApelido(objDTO.getApelido());
		artista.setCidade(objDTO.getCidade());
		artista.setEmail(objDTO.getEmail());
		artista.setTelefone(objDTO.getTelefone());

		return artista;
	}

	public ArtistaDTO findByNome(String artesao) {
		return repo.findByNome(artesao);
	}
	

	public boolean equals(Artista obj, Artista other) {
		return Objects.equals(obj.getApelido(), other.getApelido()) && Objects.equals(obj.getCidade(), other.getCidade())
				&& Objects.equals(obj.getEmail(), other.getEmail()) && Objects.equals(obj.getId(), other.getId())
			 && Objects.equals(obj.getNome(), other.getNome())
				&& Objects.equals(obj.getTelefone(), other.getTelefone());
	}

}
