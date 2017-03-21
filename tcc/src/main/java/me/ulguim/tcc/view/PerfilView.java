package me.ulguim.tcc.view;

import in.k2s.sdk.web.view.BaseView;
import in.k2s.sdk.web.view.annotation.View;

@View
public class PerfilView extends BaseView {
	private static final long serialVersionUID = 1L;

	private String username;

	private String name;

	private String lastname;

	private String avatar;

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
}
