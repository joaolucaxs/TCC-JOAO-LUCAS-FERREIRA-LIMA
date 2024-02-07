package com.joaolucas.mapp.dtos;

import com.joaolucas.mapp.model.TipoUsuario;
import com.joaolucas.mapp.model.Usuario;

public class UsuarioDTO {

	private String email;
	private String senha;
	private TipoUsuario role;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario obj) {
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.role = obj.getRole();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getRole() {
		return role;
	}

	public void setRole(TipoUsuario role) {
		this.role = role;
	}

}
