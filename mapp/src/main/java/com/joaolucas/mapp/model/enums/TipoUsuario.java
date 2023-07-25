package com.joaolucas.mapp.model.enums;

public enum TipoUsuario {
	ADMINISTRADOR (1), CURADOR (2);

	private int code;
	
	private TipoUsuario(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static TipoUsuario valueOf(int code) {
		for (TipoUsuario value : TipoUsuario.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo de usuario invalido");
	}
}
