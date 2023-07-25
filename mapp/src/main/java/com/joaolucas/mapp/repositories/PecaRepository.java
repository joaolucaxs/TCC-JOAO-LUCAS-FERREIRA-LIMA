package com.joaolucas.mapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.joaolucas.mapp.model.Peca;

@Repository
public interface PecaRepository extends MongoRepository<Peca, Long>{

	
}
