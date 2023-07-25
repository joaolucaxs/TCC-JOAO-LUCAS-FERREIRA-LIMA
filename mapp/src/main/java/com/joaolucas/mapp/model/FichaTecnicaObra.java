package com.joaolucas.mapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class FichaTecnicaObra implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String tipoImagem;
	private String codigoPeca;
	private Date dataAquisicao;
	private Boolean assinada;
	private Boolean datada;
	private String dimensao;
	private String peso;

	public FichaTecnicaObra() {
	}

	public FichaTecnicaObra(String id, String tipoImagem, String codigoPeca, Date dataAquisicao,
			Boolean assinada, Boolean datada, String dimensao, String peso) {
		super();
		this.id = id;
		this.tipoImagem = tipoImagem;
		this.codigoPeca = codigoPeca;
		this.dataAquisicao = dataAquisicao;
		this.assinada = assinada;
		this.datada = datada;
		this.dimensao = dimensao;
		this.peso = peso;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getTipoImagem() {
		return tipoImagem;
	}

	public void setTipoImagem(String tipoImagem) {
		this.tipoImagem = tipoImagem;
	}

	public String getCodigoPeca() {
		return codigoPeca;
	}

	public void setCodigoPeca(String codigoPeca) {
		this.codigoPeca = codigoPeca;
	}

	public Date getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public Boolean getAssinada() {
		return assinada;
	}

	public void setAssinada(Boolean assinada) {
		this.assinada = assinada;
	}

	public Boolean getDatada() {
		return datada;
	}

	public void setDatada(Boolean datada) {
		this.datada = datada;
	}

	public String getDimensao() {
		return dimensao;
	}

	public void setDimensao(String dimensao) {
		this.dimensao = dimensao;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(assinada, codigoPeca, dataAquisicao, datada, dimensao, id, peso, tipoImagem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FichaTecnicaObra other = (FichaTecnicaObra) obj;
		return Objects.equals(assinada, other.assinada) && Objects.equals(codigoPeca, other.codigoPeca)
				&& Objects.equals(dataAquisicao, other.dataAquisicao) && Objects.equals(datada, other.datada)
				&& Objects.equals(dimensao, other.dimensao) && Objects.equals(id, other.id)
				&&  Objects.equals(peso, other.peso)
				&& Objects.equals(tipoImagem, other.tipoImagem);
	}

}
