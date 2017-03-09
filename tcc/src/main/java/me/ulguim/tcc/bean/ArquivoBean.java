package me.ulguim.tcc.bean;

import in.k2s.sdk.core.bean.BaseBean;

import java.io.InputStream;

public class ArquivoBean extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private Long tamanho;
	private Boolean temporario = false;
	private InputStream arquivo;
	private String contentType;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public InputStream getArquivo() {
		return arquivo;
	}
	public void setArquivo(InputStream arquivo) {
		this.arquivo = arquivo;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Long getTamanho() {
		return tamanho;
	}
	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}
	public Boolean getTemporario() {
		return temporario;
	}
	public void setTemporario(Boolean temporario) {
		this.temporario = temporario;
	}

}
