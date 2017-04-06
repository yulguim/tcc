package me.ulguim.tcc.manager;

import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
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
	private ProfileSingleton profileSingleton;

	@Inject
	private AccountService accountService;

	public AccountView load(Profile profile) {
		Account accountLogadaLoaded = getAccountLogadaLoaded(profile);

		AccountView view = new AccountView();
		view.setId(accountLogadaLoaded.getId());
		view.setKey(accountLogadaLoaded.getChave());
		view.setAvatar(accountLogadaLoaded.getAvatar());
		view.setLabel(accountLogadaLoaded.getLabel());

		return view;
	}

	public AccountView saveAvatar(Profile profile, ArquivoBean avatar) {
		Account accountLogadaLoaded = getAccountLogadaLoaded(profile);

		AccountView view = new AccountView();

		return view;
	}

	public AccountView update(Profile profile, AccountView view) throws ValidationException {
		validate(profile, view);
		Account account = getAccountLogadaLoaded(profile);
		account.setName(view.getNome());
		account.setLastname(view.getSobrenome());
		account.setEmail(view.getEmail());
		account.setPassword(view.getPassword());

		account.setChave(view.getKey());
		account.setUsername(view.getKey());

		account = super.update(account, profile);

		profile.setUsuario(account);
		profileSingleton.add(profile);
		return view;
	}

	public void validate(Profile profile, AccountView view) throws ValidationException {

	}



}
