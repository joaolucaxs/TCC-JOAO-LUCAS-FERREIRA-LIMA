package com.joaolucas.mapp.model;

import com.joaolucas.mapp.model.enums.TipoUsuario;

public class Curador extends Usuario {


	private static final long serialVersionUID = 1L;

	public Curador(Long id, String nome, String senha, String email) {
		super(id, nome, senha, email, TipoUsuario.CURADOR);
	}

	@Override
	public void realizarLogin() {		
	}
	
	

}