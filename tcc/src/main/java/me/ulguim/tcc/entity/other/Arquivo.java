package me.ulguim.tcc.entity.other;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Arquivo extends in.k2s.sdk.jpa.entity.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Version
	private Integer version;

	private String caminho;

	private String chave;

	@Column(name="content_type")
	private String contentType;

	private String extensao;

	@Column(name="insert_by")
	private Long insertBy;

	@Column(name="insert_time")
	private Timestamp insertTime;

	private String nome;

	private Long tamanho;
	
	private Boolean temporario = false;

	@Column(name="update_by")
	private Long updateBy;

	@Column(name="update_time")
	private Timestamp updateTime;

	public Arquivo() {
	}
	
	public Arquivo(Long id) {
		this.id = id;
	}
	
	public Arquivo(String chave) {
		this.chave = chave;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaminho() {
		return this.caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getChave() {
		return this.chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getExtensao() {
		return this.extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public Long getInsertBy() {
		return this.insertBy;
	}

	public void setInsertBy(Long insertBy) {
		this.insertBy = insertBy;
	}

	public Timestamp getInsertTime() {
		return this.insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTamanho() {
		return this.tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public Long getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getTemporario() {
		return temporario;
	}

	public void setTemporario(Boolean temporario) {
		this.temporario = temporario;
	}

}