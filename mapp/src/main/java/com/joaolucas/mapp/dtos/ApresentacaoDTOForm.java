package com.joaolucas.mapp.dtos;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ApresentacaoDTOForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Titulo da apresentação deve ser obrigatório")
	@NotBlank(message = "Titulo da apresentação não pode ser em branco")
	private String tituloApresentacao;
	private String descricaoApresentacao;
	@NotNull(message = "Imagem de Capa deve ser obrigatória")
	@NotNull(message = "Imagem de Capa deve ser obrigatória")
	private MultipartFile imagemApresentacaoFile;

	public ApresentacaoDTOForm() {

	}

	public String getTituloApresentacao() {
		return tituloApresentacao;
	}

	public void setTituloApresentacao(String tituloApresentacao) {
		this.tituloApresentacao = tituloApresentacao;
	}

	public String getDescricaoApresentacao() {
		return descricaoApresentacao;
	}

	public void setDescricaoApresentacao(String descricaoApresentacao) {
		this.descricaoApresentacao = descricaoApresentacao;
	}

	public MultipartFile getImagemApresentacaoFile() {
		return imagemApresentacaoFile;
	}

	public void setImagemApresentacaoFile(MultipartFile imagemApresentacaoFile) {
		this.imagemApresentacaoFile = imagemApresentacaoFile;
	}

}
