package me.ulguim.tcc.manager.base;

import in.k2s.sdk.springboot.manager.base.BaseManager;
import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import in.k2s.sdk.web.profile.Profile;
import me.ulguim.tcc.bean.NotificationBean;
import me.ulguim.tcc.entity.Account;

import javax.inject.Inject;

/**
 * Created by yulle on 17/01/17.
 */
public abstract class TCCBaseManager extends BaseManager {

	@Inject
	private ProfileSingleton profileSingleton;

	public ProfileSingleton getProfileSingleton() {
		return profileSingleton;
	}

	private Account getUsuario(Profile profile) {
		return (Account) profile.getUsuario();
	}

	public Account getAccountLogada(Profile profile) {
		return super.load(Account.class, getUsuario(profile).getId());
	}

	public Account getAccountLogadaLoaded(Profile profile) {
		return super.load(Account.class, getUsuario(profile).getId());
	}

}
