package com.joaolucas.mapp.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaolucas.mapp.model.File;

@Repository
public interface FileRepository extends MongoRepository<File, String> {

	List<File> findByIdObra(String idObra);

	@Query("{ 'idObra' : ?0 }")
	List<File> findByCustomIdObra(String idObra);
}
