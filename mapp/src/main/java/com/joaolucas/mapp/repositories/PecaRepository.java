package com.joaolucas.mapp.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaolucas.mapp.dtos.PecaDTO;
import com.joaolucas.mapp.model.Peca;

@Repository
public interface PecaRepository extends MongoRepository<Peca, String> {

	@Query("{'$or': [" + "{'tituloPeca': {$regex: ?0, $options: 'i'}}, "
			+ "{'tipologia': {$regex: ?0, $options: 'i'}}, " + "{'formaAssociativa': {$regex: ?0, $options: 'i'}}, "
			+ "{'relacaoCultural': {$regex: ?0, $options: 'i'}}, " + "{'tecnica': {$regex: ?0, $options: 'i'}}, "
			+ "{'classificacao': {$regex: ?0, $options: 'i'}}, " + "{'produto': {$regex: ?0, $options: 'i'}}"
			+ "{'fichatecnica.codigoPeca': {$regex: ?0, $options: 'i'}}"
			+ "{'fichatecnica.dimensao': {$regex: ?0, $options: 'i'}}" + "{'artesao.nome': {$regex: ?0, $options: 'i'}}"
			+ "]}")
	List<PecaDTO> filtrarPorCampo(String pesquisa);

	@Query("{'fichatecnica.dataAquisicao': ?0}")
	List<PecaDTO> filtrarPorDataAquisicao(LocalDate dataAquisicao);
	
	@Query("{'fichatecnica.assinada': ?0}")
	List<PecaDTO> filtrarPorAssinada(Boolean assinada);
	
	@Query("{'fichatecnica.datada': ?0}")
	List<PecaDTO> filtrarPorDatada(Boolean datada);

}
