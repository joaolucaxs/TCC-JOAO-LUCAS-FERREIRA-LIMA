package com.joaolucas.mapp.dtos;

import com.joaolucas.mapp.model.File;
import com.joaolucas.mapp.model.Peca;

import jakarta.validation.constraints.Max;

public class FileApresentacaoDTOShow {

	private String id;
	private String idObra;
	private String nomeArquivo;
	private Peca peca;

	public FileApresentacaoDTOShow() {
	}

	public FileApresentacaoDTOShow(File obj) {
		this.id = obj.getId();
		this.idObra = obj.getIdObra();
		this.nomeArquivo = obj.getName();
	}

	public String getId() {
		return id;
	}
	
	public String getIdObra() {
		return idObra;
	}

	public Peca getPeca() {
		return peca;
	}
	
	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}
}
