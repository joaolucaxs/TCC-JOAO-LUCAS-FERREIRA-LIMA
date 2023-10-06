package com.joaolucas.mapp.services.exceptions;

public class FileError extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FileError(String msg) {
		super(msg);
	}

}
