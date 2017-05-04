package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;
import me.ulguim.tcc.bean.HabilidadeBean;
import me.ulguim.tcc.bean.SocialNetworkBean;

import java.util.List;

@View
public class PerfilView extends BaseView {
	private static final long serialVersionUID = 1L;

	private String username;

	private String name;

	private String lastname;

	private String label;

	private String avatar;

	private String about;

	private OcupacaoView ocupacao;

	private String ocupacaoNome;

	private LocalizacaoView localizacao;

	private String localizacaoNome;

	private List<HabilidadeBean> habilidades;

	private List<SocialNetworkBean> links;

	private List<ProjetoSimpleView> projetos;

	//Extras

	private String key;

	private Boolean hasNoProfile;

	private Boolean isFriend;

	private Boolean isRequestedByMe;

	private Boolean isRequestedByUser;

	private Boolean isMyProfile;

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

	public List<SocialNetworkBean> getLinks() {
		return links;
	}

	public void setLinks(List<SocialNetworkBean> links) {
		this.links = links;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getFriend() {
		return isFriend;
	}

	public void setFriend(Boolean friend) {
		isFriend = friend;
	}

	public Boolean getMyProfile() {
		return isMyProfile;
	}

	public void setMyProfile(Boolean myProfile) {
		isMyProfile = myProfile;
	}

	public Boolean getRequestedByMe() {
		return isRequestedByMe;
	}

	public void setRequestedByMe(Boolean requestedByMe) {
		isRequestedByMe = requestedByMe;
	}

	public Boolean getRequestedByUser() {
		return isRequestedByUser;
	}

	public void setRequestedByUser(Boolean requestedByUser) {
		isRequestedByUser = requestedByUser;
	}

	public String getLocalizacaoNome() {
		return localizacaoNome;
	}

	public void setLocalizacaoNome(String localizacaoNome) {
		this.localizacaoNome = localizacaoNome;
	}

	public OcupacaoView getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(OcupacaoView ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String getOcupacaoNome() {
		return ocupacaoNome;
	}

	public void setOcupacaoNome(String ocupacaoNome) {
		this.ocupacaoNome = ocupacaoNome;
	}

	public List<ProjetoSimpleView> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<ProjetoSimpleView> projetos) {
		this.projetos = projetos;
	}
}
