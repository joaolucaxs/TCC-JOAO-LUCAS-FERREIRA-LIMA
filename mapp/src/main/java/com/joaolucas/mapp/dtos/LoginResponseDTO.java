package com.joaolucas.mapp.dtos;

public class LoginResponseDTO {

	private String token;
	
	public LoginResponseDTO() {
	}

	public LoginResponseDTO(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
