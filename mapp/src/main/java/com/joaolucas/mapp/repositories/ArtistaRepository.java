package com.joaolucas.mapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.joaolucas.mapp.dtos.ArtistaDTO;
import com.joaolucas.mapp.model.Artista;

@Repository
public interface ArtistaRepository extends MongoRepository<Artista, String>{

	ArtistaDTO findByNome(String artesao);
}
