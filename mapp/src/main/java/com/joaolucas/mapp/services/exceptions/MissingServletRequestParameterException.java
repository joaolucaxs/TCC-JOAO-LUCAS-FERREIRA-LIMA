package com.joaolucas.mapp.services.exceptions;

public class MissingServletRequestParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MissingServletRequestParameterException(String msg) {
		super(msg);
	}
}
