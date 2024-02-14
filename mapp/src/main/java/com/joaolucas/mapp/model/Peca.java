package com.joaolucas.mapp.model;

import java.io.Serializable;
import java.util.Base64;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "obra")
public class Peca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Artista artesao;
	@NotNull(message = "Titulo da peça deve ser obrigatório")
	@NotBlank(message = "Titulo da peça não pode ser em branco")
	private String tituloPeca;
	private String tipologia;
	private String formaAssociativa;
	private String relacaoCultural;
	private String tecnica;
	private String classificacao;
	private String produto;
	private FichaTecnicaObra fichatecnica;
	private byte[] qrCode;

	public Peca() {
	};

	public Peca(String id, Artista artesao, String tituloPeca, String tipologia, String formaAssociativa,
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

	public Peca(Peca obj) {
		this.id = obj.id;
		this.artesao = obj.artesao;
		this.tituloPeca = obj.tituloPeca;
		this.tipologia = obj.tipologia;
		this.formaAssociativa = obj.formaAssociativa;
		this.relacaoCultural = obj.relacaoCultural;
		this.tecnica = obj.tecnica;
		this.classificacao = obj.classificacao;
		this.produto = obj.produto;
		this.fichatecnica = obj.fichatecnica;
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

	public byte[] getQrCode() {
		return qrCode;
	}

	public void setQrCode(byte[] qrCode) {
		this.qrCode = qrCode;
	}

	public String getQrCodeBinaryStr() {
		return Base64.getEncoder().encodeToString(qrCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

}
