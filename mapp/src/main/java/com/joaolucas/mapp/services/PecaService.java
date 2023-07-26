package com.joaolucas.mapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaolucas.mapp.dtos.PecaDTO;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.repositories.PecaRepository;
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

	public Peca fromDTO(PecaDTO objDTO) {
		return new Peca(objDTO.getId(), objDTO.getArtesao(), objDTO.getTituloPeca(), objDTO.getTipologia(),
				objDTO.getFormaAssociativa(), objDTO.getRelacaoCultural(), objDTO.getTecnica(),
				objDTO.getClassificacao(), objDTO.getProduto(), objDTO.getFichatecnica());
	}

}
