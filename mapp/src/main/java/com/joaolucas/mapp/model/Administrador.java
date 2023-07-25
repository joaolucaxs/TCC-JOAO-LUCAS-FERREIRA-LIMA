package com.joaolucas.mapp.model;

import com.joaolucas.mapp.model.enums.TipoUsuario;

public class Administrador extends Usuario {

	private static final long serialVersionUID = 1L;

	public Administrador(Long id, String nome, String senha, String email) {
		super(id, nome, senha, email, TipoUsuario.ADMINISTRADOR);
	}

	@Override
	public void realizarLogin() {
		// TODO Auto-generated method stub

	}

}
