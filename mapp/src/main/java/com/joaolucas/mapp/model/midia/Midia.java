package com.joaolucas.mapp.model.midia;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import com.joaolucas.mapp.model.Apresentacao;

public class Midia implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String formato;
	private byte[] conteudo;
	private Apresentacao apresentacao;

	public Midia() {

	}

	public Midia(Long id, String nome, String formato, byte[] conteudo, Apresentacao apresentacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.formato = formato;
		this.conteudo = conteudo;
		this.apresentacao = apresentacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public byte[] getConteudo() {
		return conteudo;
	}

	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}

	public Apresentacao getApresentacao() {
		return apresentacao;
	}

	public void setApresentacao(Apresentacao apresentacao) {
		this.apresentacao = apresentacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(conteudo);
		result = prime * result + Objects.hash(apresentacao, formato, id, nome);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Midia other = (Midia) obj;
		return Objects.equals(apresentacao, other.apresentacao) && Arrays.equals(conteudo, other.conteudo)
				&& Objects.equals(formato, other.formato) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}

}
