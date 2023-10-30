package com.joaolucas.mapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "apresentacao")
public class Apresentacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@NotNull(message = "Titulo da apresentação deve ser obrigatório")
	@NotBlank(message = "Titulo da apresentação não pode ser em branco")
	private String tituloApresentacao;
	private String descricaoApresentacao;
	@NotNull(message = "Data de Criação da Apresentação deve ser obrigatório")
	private LocalDate dataCriacao;
	@NotNull(message = "Imagem de Capa deve ser obrigatória")
	private Image imagemCapa;

	@DBRef(lazy = true)
	private List<File> midiasApresentacao = new ArrayList<>();

	public Apresentacao() {
	}

	public Apresentacao(String id, String tituloApresentacao, String descricaoApresentacao, LocalDate dataCriacao,
			Image imagemCapa) {
		super();
		this.id = id;
		this.tituloApresentacao = tituloApresentacao;
		this.descricaoApresentacao = descricaoApresentacao;
		this.dataCriacao = dataCriacao;
		this.imagemCapa = imagemCapa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTituloApresentacao() {
		return tituloApresentacao;
	}

	public void setTituloApresentacao(String tituloApresentacao) {
		this.tituloApresentacao = tituloApresentacao;
	}

	public String getDescricaoApresentacao() {
		return descricaoApresentacao;
	}

	public void setDescricaoApresentacao(String descricaoApresentacao) {
		this.descricaoApresentacao = descricaoApresentacao;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<File> getMidiasApresentacao() {
		return midiasApresentacao;
	}

	public Image getImagemCapa() {
		return imagemCapa;
	}

	public void setImagemCapa(Image imagemCapa) {
		this.imagemCapa = imagemCapa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataCriacao, descricaoApresentacao, id, midiasApresentacao, tituloApresentacao, imagemCapa);
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
		return Objects.equals(dataCriacao, other.dataCriacao)
				&& Objects.equals(descricaoApresentacao, other.descricaoApresentacao) && Objects.equals(id, other.id)
				&& Objects.equals(midiasApresentacao, other.midiasApresentacao)
				&& Objects.equals(tituloApresentacao, other.tituloApresentacao);
	}

}
