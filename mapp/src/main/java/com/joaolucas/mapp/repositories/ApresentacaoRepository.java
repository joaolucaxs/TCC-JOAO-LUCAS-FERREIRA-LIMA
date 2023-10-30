package com.joaolucas.mapp.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaolucas.mapp.model.Apresentacao;

@Repository
public interface ApresentacaoRepository extends MongoRepository<Apresentacao, String> {

	@Query("{'$or': [{'tituloApresentacao': {$regex: ?0, $options: 'i'}},"
			+ "{'descricaoApresentacao': {$regex: ?0, $options: 'i'}}]}")
	List<Apresentacao> filtrarPorCampo(String pesquisa);

	@Query("{'dataCriacao': ?0}")
	List<Apresentacao> filtrarPorDataCriacao(LocalDate dataCriacao);
}
