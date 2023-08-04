package com.joaolucas.mapp.dtos;

import com.joaolucas.mapp.model.Artista;

public class ArtistaDTO {

	private String id;
	private String nome;
	private String apelido;
	private String telefone;
	private String email;
	private String cidade;

	public ArtistaDTO() {
	}

	public ArtistaDTO(Artista obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.apelido = obj.getApelido();
		this.telefone = obj.getTelefone();
		this.email = obj.getEmail();
		this.cidade = obj.getCidade();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
