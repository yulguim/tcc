package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;

@View
public class ContatoView extends BaseView {
	private static final long serialVersionUID = 1L;

	private String key;

	private String avatar;

	private String label;

	private Boolean isFriend;

	private Boolean isRequestedByMe;

	private Boolean isRequestedByUser;

	private Boolean isMyProfile;

	//Usado para projeto

	private Long projetoId;

	private String projetoKey;

	public ContatoView() {
		
	}

	public ContatoView(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public Boolean getMyProfile() {
		return isMyProfile;
	}

	public void setMyProfile(Boolean myProfile) {
		isMyProfile = myProfile;
	}

	public Long getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Long projetoId) {
		this.projetoId = projetoId;
	}

	public String getProjetoKey() {
		return projetoKey;
	}

	public void setProjetoKey(String projetoKey) {
		this.projetoKey = projetoKey;
	}
}
