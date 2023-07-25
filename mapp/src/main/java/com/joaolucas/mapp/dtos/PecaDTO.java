package com.joaolucas.mapp.dtos;

import java.io.Serializable;

import com.joaolucas.mapp.model.Artista;
import com.joaolucas.mapp.model.FichaTecnicaObra;
import com.joaolucas.mapp.model.Peca;

public class PecaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private Artista artesao;
	private String tituloPeca;
	private String tipologia;
	private String formaAssociativa;
	private String relacaoCultural;
	private String tecnica;
	private String classificacao;
	private String produto;
	private FichaTecnicaObra fichatecnica;

	public PecaDTO() {

	}

	public PecaDTO(Peca obj) {
		id = obj.getId();
		artesao = obj.getArtesao();
		tituloPeca = obj.getTituloPeca();
		tipologia = obj.getTipologia();
		formaAssociativa = obj.getFormaAssociativa();
		relacaoCultural = obj.getRelacaoCultural();
		tecnica = obj.getTecnica();
		classificacao = obj.getClassificacao();
		produto = obj.getProduto();
		fichatecnica = obj.getFichatecnica();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Artista getArtesao() {
		return artesao;
	}

	public void setArtesao(Artista artesao) {
		this.artesao = artesao;
	}

	public String getTituloPeca() {
		return tituloPeca;
	}

	public void setTituloPeca(String tituloPeca) {
		this.tituloPeca = tituloPeca;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getFormaAssociativa() {
		return formaAssociativa;
	}

	public void setFormaAssociativa(String formaAssociativa) {
		this.formaAssociativa = formaAssociativa;
	}

	public String getRelacaoCultural() {
		return relacaoCultural;
	}

	public void setRelacaoCultural(String relacaoCultural) {
		this.relacaoCultural = relacaoCultural;
	}

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public FichaTecnicaObra getFichatecnica() {
		return fichatecnica;
	}

	public void setFichatecnica(FichaTecnicaObra fichatecnica) {
		this.fichatecnica = fichatecnica;
	}
	
	
}
