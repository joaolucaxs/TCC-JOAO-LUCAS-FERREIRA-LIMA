package com.joaolucas.mapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Peca implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Artista artesao;
	private String tituloPeca;
	private String tipologia;
	private String formaAssociativa;
	private String relacaoCultural;
	private String tecnica;
	private String classificacao;
	private String produto;
	private FichaTecnicaObra fichatecnica;

	public Peca() {
	};

	public Peca(Long id, Artista artesao, String tituloPeca, String tipologia, String formaAssociativa,
			String relacaoCultural, String tecnica, String classificacao, String produto,
			FichaTecnicaObra fichatecnica) {
		super();
		this.id = id;
		this.artesao = artesao;
		this.tituloPeca = tituloPeca;
		this.tipologia = tipologia;
		this.formaAssociativa = formaAssociativa;
		this.relacaoCultural = relacaoCultural;
		this.tecnica = tecnica;
		this.classificacao = classificacao;
		this.produto = produto;
		this.fichatecnica = fichatecnica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public int hashCode() {
		return Objects.hash(artesao, classificacao, fichatecnica, formaAssociativa, id, produto, relacaoCultural,
				tecnica, tipologia, tituloPeca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peca other = (Peca) obj;
		return Objects.equals(artesao, other.artesao) && Objects.equals(classificacao, other.classificacao)
				&& Objects.equals(fichatecnica, other.fichatecnica)
				&& Objects.equals(formaAssociativa, other.formaAssociativa) && Objects.equals(id, other.id)
				&& Objects.equals(produto, other.produto) && Objects.equals(relacaoCultural, other.relacaoCultural)
				&& Objects.equals(tecnica, other.tecnica) && Objects.equals(tipologia, other.tipologia)
				&& Objects.equals(tituloPeca, other.tituloPeca);
	}


}
