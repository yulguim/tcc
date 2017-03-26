package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;
import me.ulguim.tcc.bean.HabilidadeBean;

import java.util.List;

@View
public class PerfilView extends BaseView {
	private static final long serialVersionUID = 1L;

	private String username;

	private String name;

	private String lastname;

	private String avatar;

	private String about;

	private LocalizacaoView localizacao;

	private List<HabilidadeBean> habilidades;

	private List<LinkView> links;

	//Extras

	private Boolean hasNoProfile;

	public PerfilView() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Boolean getHasNoProfile() {
		return hasNoProfile;
	}

	public void setHasNoProfile(Boolean hasNoProfile) {
		this.hasNoProfile = hasNoProfile;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public LocalizacaoView getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(LocalizacaoView localizacao) {
		this.localizacao = localizacao;
	}

	public List<HabilidadeBean> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<HabilidadeBean> habilidades) {
		this.habilidades = habilidades;
	}

	public List<LinkView> getLinks() {
		return links;
	}

	public void setLinks(List<LinkView> links) {
		this.links = links;
	}
}
