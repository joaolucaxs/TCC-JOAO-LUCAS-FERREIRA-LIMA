package com.joaolucas.mapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.joaolucas.mapp.model.midia.Midia;

public class Apresentacao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String tituloApresentacao;
	private String descricao;
	private Date dataCriacao;
	private String curador; // MUDAR POSTERIORMENTE PARA TIPO 'CURADOR'
	private List<Midia> midias = new ArrayList<>();
	private List<Peca> pecas = new ArrayList<>();
	private List<Artista> artistas = new ArrayList<>();
	
	public Apresentacao() {

	}

	public Apresentacao(Long id, String tituloApresentacao, String descricao, Date dataCriacao, String curador) {
		super();
		this.id = id;
		this.tituloApresentacao = tituloApresentacao;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.curador = curador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTituloApresentacao() {
		return tituloApresentacao;
	}

	public void setTituloApresentacao(String tituloApresentacao) {
		this.tituloApresentacao = tituloApresentacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getCurador() {
		return curador;
	}

	public void setCurador(String curador) {
		this.curador = curador;
	}

	public List<Midia> getMidias() {
		return midias;
	}

	public List<Peca> getPecas() {
		return pecas;
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(curador, dataCriacao, descricao, id, tituloApresentacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apresentacao other = (Apresentacao) obj;
		return Objects.equals(curador, other.curador) && Objects.equals(dataCriacao, other.dataCriacao)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(tituloApresentacao, other.tituloApresentacao);
	}

}
