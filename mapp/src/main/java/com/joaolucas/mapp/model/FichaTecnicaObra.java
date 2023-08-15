package com.joaolucas.mapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FichaTecnicaObra implements Serializable {
	private static final long serialVersionUID = 1L;

	private Image imagemCapa;
	private String codigoPeca;
	private LocalDate dataAquisicao;
	private Boolean assinada;
	private Boolean datada;
	private String dimensao;
	private String peso;

	public FichaTecnicaObra() {
	}

	public FichaTecnicaObra(Image imagemCapa, String codigoPeca, LocalDate dataAquisicao, Boolean assinada, Boolean datada,
			String dimensao, String peso) {
		super();
		this.imagemCapa = imagemCapa;
		this.codigoPeca = codigoPeca;
		this.dataAquisicao = dataAquisicao;
		this.assinada = assinada;
		this.datada = datada;
		this.dimensao = dimensao;
		this.peso = peso;
	}

	public Image getImagemCapa() {
		return imagemCapa;
	}

	public void setImagemCapa(Image imagemCapa) {
		this.imagemCapa = imagemCapa;
	}
	
	public String getCodigoPeca() {
		return codigoPeca;
	}

	public void setCodigoPeca(String codigoPeca) {
		this.codigoPeca = codigoPeca;
	}

	public LocalDate getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(LocalDate dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public String getDataAquisicaoAsStr() {
		return formatDateToString(getDataAquisicao());
	}
	
    private static String formatDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

	public Boolean getAssinada() {
		return assinada;
	}

	public void setAssinada(Boolean assinada) {
		this.assinada = assinada;
	}

	public Boolean getDatada() {
		return datada;
	}

	public void setDatada(Boolean datada) {
		this.datada = datada;
	}

	public String getDimensao() {
		return dimensao;
	}

	public void setDimensao(String dimensao) {
		this.dimensao = dimensao;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(imagemCapa,assinada, codigoPeca, dataAquisicao, datada, dimensao, peso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FichaTecnicaObra other = (FichaTecnicaObra) obj;
		return Objects.equals(assinada, other.assinada) && Objects.equals(codigoPeca, other.codigoPeca)
				&& Objects.equals(dataAquisicao, other.dataAquisicao) && Objects.equals(datada, other.datada)
				&& Objects.equals(dimensao, other.dimensao) && Objects.equals(peso, other.peso);
	}

}
