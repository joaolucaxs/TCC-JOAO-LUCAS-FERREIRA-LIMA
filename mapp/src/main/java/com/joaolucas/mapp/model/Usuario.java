package com.joaolucas.mapp.model;

import java.io.Serializable;
import java.util.Objects;

import com.joaolucas.mapp.model.enums.TipoUsuario;

public abstract class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String senha;
	private String email;
	private Integer tipoUsuario;

	public Usuario(Long id, String nome, String senha, String email, TipoUsuario tipoUsuario) {
		super();
		this.setId(id);
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		setTipoUsuario(tipoUsuario);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public abstract void realizarLogin();

	public TipoUsuario getTipoUsuario() {
		return TipoUsuario.valueOf(tipoUsuario);
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		if (tipoUsuario != null) {
			this.tipoUsuario = tipoUsuario.getCode();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nome, senha, tipoUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha) && Objects.equals(tipoUsuario, other.tipoUsuario);
	}

}
