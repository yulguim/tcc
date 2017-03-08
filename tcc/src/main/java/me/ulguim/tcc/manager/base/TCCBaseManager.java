package me.ulguim.tcc.manager.base;

import in.k2s.sdk.springboot.manager.base.BaseManager;
import in.k2s.sdk.web.profile.Profile;
import me.ulguim.tcc.entity.Account;

/**
 * Created by yulle on 17/01/17.
 */
public abstract class TCCBaseManager extends BaseManager {

	public Account getAccountLogada(Profile profile) {
		return (Account) profile.getUsuario();
	}

	public Account getAccountLogadaLoaded(Profile profile) {
		return super.load(Account.class, getAccountLogada(profile).getId());
	}

}
