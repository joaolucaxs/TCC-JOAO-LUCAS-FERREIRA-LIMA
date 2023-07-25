package com.joaolucas.mapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.repositories.PecaRepository;

@Service
public class PecaService {

	
	@Autowired
	private PecaRepository repo;
	
	public List<Peca> findAll(){
		return repo.findAll();
	}
	
}
