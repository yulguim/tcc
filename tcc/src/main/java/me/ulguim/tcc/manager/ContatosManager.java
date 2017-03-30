package me.ulguim.tcc.manager;

import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.message.MessageWarning;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.profile.ProfileBean;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Contato;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.service.LoginService;
import me.ulguim.tcc.service.PerfilService;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.lang.ref.SoftReference;
import java.util.List;

@Component
public class ContatosManager extends TCCBaseManager {

	@Inject
	private AccountService accountService;

	@Inject
	private PerfilService perfilService;

	public List<ContatoView> list(Profile profile) {
		Account accountLogada = super.getAccountLogada(profile);
		return null;
	}

	public ContatoView request(Profile profile, ContatoView view) {
		Account ac = accountService.selectById(Account.class, 4900594292770001L);
		System.out.println("ac.getId() = " + ac.getId());
		
		Perfil perfil = perfilService.selectByChave(Perfil.class, view.getKey());
		Account account = perfil.getAccount();
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

	public ContatoView acceptRequest(Profile profile, ContatoView view) throws ValidationException {
		if (view == null || view.getKey() == null) {
			throw new ValidationException(new MessageWarning("warn.save"));
		}

		Account myAccount = super.getAccountLogadaLoaded(profile);

		Account toAccept = accountService.selectByChave(Account.class, view.getKey());

		if (myAccount.getExtraParams().existsRequest(toAccept.getId())) {
			//Aceitar
			myAccount.addContact(toAccept.getId());
			myAccount.getExtraParams().removeRequest(toAccept.getId());
			super.update(myAccount);
		}

		return view;
	}

	public ContatoView load(Profile profile, ContatoView view) {
		return null;
	}

}
