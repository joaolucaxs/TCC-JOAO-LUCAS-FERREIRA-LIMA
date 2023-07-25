package com.joaolucas.mapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artista implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nome;
	private String apelido;
	private String telefone;
	private String email;
	private String cidade;
	private List<Peca> listaObras = new ArrayList<>();

	public Artista() {
	}

	public Artista(String id, String nome, String apelido, String telefone, String email, String cidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.telefone = telefone;
		this.email = email;
		this.cidade = cidade;
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

	public List<Peca> getListaObras() {
		return listaObras;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apelido, cidade, email, id, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return Objects.equals(apelido, other.apelido) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone);
	}

}
