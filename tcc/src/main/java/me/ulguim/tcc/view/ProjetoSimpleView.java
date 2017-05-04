package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;
import me.ulguim.tcc.bean.HabilidadeBean;
import me.ulguim.tcc.bean.SocialNetworkBean;
import me.ulguim.tcc.bean.TagBean;

import java.util.List;

@View
public class ProjetoSimpleView extends BaseView {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String key;

	private String titulo;

	private Integer participantes;

	private String ownerLabel;

	private String ownerKey;

	private String ownerAvatar;

	//Extras

	private String descricao;

	private String insertTime;

	public ProjetoSimpleView() {

	}

	public ProjetoSimpleView(Long id, String key, String titulo) {
		this.id = id;
		this.key = key;
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Integer participantes) {
		this.participantes = participantes;
	}

	public String getOwnerLabel() {
		return ownerLabel;
	}

	public void setOwnerLabel(String ownerLabel) {
		this.ownerLabel = ownerLabel;
	}

	public String getOwnerKey() {
		return ownerKey;
	}

	public void setOwnerKey(String ownerKey) {
		this.ownerKey = ownerKey;
	}

	public String getOwnerAvatar() {
		return ownerAvatar;
	}

	public void setOwnerAvatar(String ownerAvatar) {
		this.ownerAvatar = ownerAvatar;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
}
