package com.joaolucas.mapp.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.joaolucas.mapp.model.Peca;

public class PecaDTOFormulario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String artesao;
	private String tituloPeca;
	private String tipologia;
	private String formaAssociativa;
	private String relacaoCultural;
	private String tecnica;
	private String classificacao;
	private String produto;
	private String codigoPeca;
	private LocalDate dataAquisicao;
	private Boolean assinada;
	private Boolean datada;
	private String dimensao;
	private String peso;
	private MultipartFile imagemPecaFile;


	public PecaDTOFormulario() {

	}

	public PecaDTOFormulario(Peca obj) {
		id = obj.getId();
		artesao = obj.getArtesao().getNome();
		tituloPeca = obj.getTituloPeca();
		tipologia = obj.getTipologia();
		formaAssociativa = obj.getFormaAssociativa();
		relacaoCultural = obj.getRelacaoCultural();
		tecnica = obj.getTecnica();
		classificacao = obj.getClassificacao();
		produto = obj.getProduto();
		codigoPeca = obj.getFichatecnica().getCodigoPeca();
		dataAquisicao = obj.getFichatecnica().getDataAquisicao();
		assinada = obj.getFichatecnica().getAssinada();
		datada = obj.getFichatecnica().getDatada();
		dimensao = obj.getFichatecnica().getDimensao();
		peso = obj.getFichatecnica().getPeso();
		obj.getFichatecnica().getImagemCapa();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArtesao() {
		return artesao;
	}

	public void setArtesao(String artesao) {
		this.artesao = artesao;
	}

	public String getTituloPeca() {
		return tituloPeca;
	}

	public void setTituloPeca(String tituloPeca) {
		this.tituloPeca = tituloPeca;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getFormaAssociativa() {
		return formaAssociativa;
	}

	public void setFormaAssociativa(String formaAssociativa) {
		this.formaAssociativa = formaAssociativa;
	}

	public String getRelacaoCultural() {
		return relacaoCultural;
	}

	public void setRelacaoCultural(String relacaoCultural) {
		this.relacaoCultural = relacaoCultural;
	}

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
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
	
	public MultipartFile getImagemPecaFile() {
	    return imagemPecaFile;
	}

	public void setImagemPecaFile(MultipartFile imagemPecaFile) {
	    this.imagemPecaFile = imagemPecaFile;
	}


}
