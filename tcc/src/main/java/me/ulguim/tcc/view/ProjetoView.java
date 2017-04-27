package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;
import me.ulguim.tcc.bean.HabilidadeBean;
import me.ulguim.tcc.bean.SocialNetworkBean;
import me.ulguim.tcc.bean.TagBean;

import java.util.List;

@View
public class ProjetoView extends BaseView {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String key;

	private ContatoView owner;

	private String titulo;

	private String descricao;

	private List<HabilidadeBean> habilidades;

	private List<TagBean> tags;

	private List<SocialNetworkBean> links;

	// Extras

	private Integer numeroParticipantes = 0;

	private Boolean souParticipante = false;

	private Boolean permiteRequest = false;

	private Boolean meuProjeto = false;

	private Boolean isRequested = false;

	public ProjetoView() {

	}

	public ProjetoView(String key) {
		this.key = key;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<HabilidadeBean> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<HabilidadeBean> habilidades) {
		this.habilidades = habilidades;
	}

	public List<TagBean> getTags() {
		return tags;
	}

	public void setTags(List<TagBean> tags) {
		this.tags = tags;
	}

	public List<SocialNetworkBean> getLinks() {
		return links;
	}

	public void setLinks(List<SocialNetworkBean> links) {
		this.links = links;
	}

	public Boolean getSouParticipante() {
		return souParticipante;
	}

	public void setSouParticipante(Boolean souParticipante) {
		this.souParticipante = souParticipante;
	}

	public Boolean getPermiteRequest() {
		return permiteRequest;
	}

	public void setPermiteRequest(Boolean permiteRequest) {
		this.permiteRequest = permiteRequest;
	}

	public Boolean getMeuProjeto() {
		return meuProjeto;
	}

	public void setMeuProjeto(Boolean meuProjeto) {
		this.meuProjeto = meuProjeto;
	}

	public Boolean getRequested() {
		return isRequested;
	}

	public void setRequested(Boolean requested) {
		isRequested = requested;
	}

	public ContatoView getOwner() {
		return owner;
	}

	public void setOwner(ContatoView owner) {
		this.owner = owner;
	}

	public Integer getNumeroParticipantes() {
		return numeroParticipantes;
	}

	public void setNumeroParticipantes(Integer numeroParticipantes) {
		this.numeroParticipantes = numeroParticipantes;
	}
}
