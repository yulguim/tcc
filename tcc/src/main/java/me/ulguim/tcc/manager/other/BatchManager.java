package me.ulguim.tcc.manager.other;

import in.k2s.sdk.web.profile.Profile;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.view.ContatoView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class BatchManager extends TCCBaseManager {

	@Inject
	private AccountService accountService;

	public List<ContatoView> list(Profile profile) {
		Account accountLogada = super.getAccountLogada(profile);
		return null;
	}

	public ContatoView request(Profile profile, ContatoView view) {
		Account account = accountService.selectByChave(Account.class, view.getKey());
		account.getExtraParams().addRequest(super.getAccountLogada(profile).getId());
		super.update(account);

		return view;
	}

	public ContatoView ignoreRequest(Profile profile, ContatoView view) {
		Account myAccount = super.getAccountLogadaLoaded(profile);
		Account requestAccount = accountService.selectByChave(Account.class, view.getKey());
		if (requestAccount != null) {
			myAccount.getExtraParams().removeRequest(requestAccount.getId());
			super.update(myAccount);
		}

		return view;
	}

	public ContatoView acceptRequest(Profile profile, ContatoView view) {
		return null;
	}

	public ContatoView load(Profile profile, ContatoView view) {
		return null;
	}

}
