package com.joaolucas.mapp.model;

public enum TipoUsuario {

	ADMIN("ADMIN"), CURADOR("CURADOR"), VISITANTE("VISITANTE");

	private String role;

	TipoUsuario(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public static TipoUsuario valueOfRole(String role) {
		for (TipoUsuario tipo : TipoUsuario.values()) {
			if (tipo.role.equals(role)) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Tipo de usuário não encontrado: " + role);
	}
}
