package com.joaolucas.mapp.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.joaolucas.mapp.dtos.PecaDTO;
import com.joaolucas.mapp.model.Peca;

@Repository
public interface PecaRepository extends MongoRepository<Peca, String>{

	
	List<PecaDTO> findByTituloPecaIgnoreCase(String tituloPeca);
	List<PecaDTO> findByTipologiaIgnoreCase(String tipologia);
	List<PecaDTO> findByFormaAssociativaIgnoreCase(String formaAssociativa);
	List<PecaDTO> findByRelacaoCulturalIgnoreCase(String relacaoCultural);
	List<PecaDTO> findByTecnicaIgnoreCase(String tecnica);
	List<PecaDTO> findByClassificacaoIgnoreCase(String classificacao);
	List<PecaDTO> findByProdutoIgnoreCase(String produto);
	
//	List<PecaDTO> findByFichaTecnicaTipoImagem(String tipoImagem);
//	List<PecaDTO> findByFichaTecnicaCodigoPeca(String codigoPeca);
//	List<PecaDTO> findByFichaTecnicaDataAquisicao(Date dataAquisicao);
//	List<PecaDTO> findByFichaTecnicaAssinada(Boolean assinada);
//	List<PecaDTO> findByFichaTecnicaDatada(Boolean datada);
//	List<PecaDTO> findByFichaTecnicaDimensao(String dimensao);
//	List<PecaDTO> findByFichaTecnicaPeso(String peso);

}
