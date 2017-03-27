package me.ulguim.tcc.manager;

import in.k2s.sdk.web.profile.Profile;
import me.ulguim.tcc.bean.ArquivoBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ContatoView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class AccountManager extends TCCBaseManager {

	@Inject
	private AccountService accountService;

	public AccountView load(Profile profile) {
		Account accountLogadaLoaded = getAccountLogadaLoaded(profile);

		AccountView view = new AccountView();
		view.setAvatar(accountLogadaLoaded.getAvatar());
		view.setLabel(accountLogadaLoaded.getLabel());

		return view;
	}

	public AccountView saveAvatar(Profile profile, ArquivoBean avatar) {
		Account accountLogadaLoaded = getAccountLogadaLoaded(profile);

		AccountView view = new AccountView();

		return view;
	}

}
