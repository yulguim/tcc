package me.ulguim.tcc.manager;

import in.k2s.sdk.core.interfaces.Entity;
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
import me.ulguim.tcc.parser.AccountParser;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.service.LoginService;
import me.ulguim.tcc.service.PerfilService;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContatosManager extends TCCBaseManager {

	@Inject
	private AccountService accountService;

	@Inject
	private PerfilService perfilService;

	public List<AccountView> list(Profile profile) {
		Account accountLogada = super.getAccountLogadaLoaded(profile);

		List<AccountView> list = new ArrayList<>();
		accountLogada.getContactsIdList().forEach(c -> {
			Account a = accountService.selectById(Account.class, c);
			list.add(AccountParser.parse(a));
		});

		return list;
	}

	public ContatoView request(Profile profile, ContatoView view) {
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

	public ContatoView cancelRequest(Profile profile, ContatoView view) {
		Account myAccount = super.getAccountLogadaLoaded(profile);
		Account toCancel = accountService.selectByChave(Account.class, view.getKey());
		if (toCancel != null) {
			toCancel.getExtraParams().removeRequest(myAccount.getId());
			super.update(toCancel);
		}

		return view;
	}

	public ContatoView acceptRequest(Profile profile, ContatoView view) throws ValidationException {
		if (view == null || view.getKey() == null) {
			throw new ValidationException(new MessageWarning("warn.save"));
		}

		Account myAccount = super.getAccountLogadaLoaded(profile);
		Account toAccept = accountService.selectByChave(Account.class, view.getKey());
		if (toAccept == null) return view;

		if (myAccount.getExtraParams().existsRequest(toAccept.getId())) {
			//Add nos meus contatos
			myAccount.addContact(toAccept.getId());
			myAccount.getExtraParams().removeRequest(toAccept.getId());
			myAccount = super.update(myAccount, profile);
			//Add nos contatos da pessoa
			toAccept.addContact(myAccount.getId());
			toAccept.getExtraParams().removeRequest(myAccount.getId());
			super.update(toAccept, profile);
		}

		return view;
	}

	public ContatoView remove(Profile profile, ContatoView view) throws ValidationException {
		if (view == null || view.getKey() == null) {
			throw new ValidationException(new MessageWarning("warn.delete"));
		}

		Account myAccount = super.getAccountLogadaLoaded(profile);
		Account toRemove = accountService.selectByChave(Account.class, view.getKey());
		if (toRemove == null) return view;

		if (myAccount.contactExists(toRemove.getId())) {
			//Remove dos meus contatos
			myAccount.removeContact(toRemove.getId());
			myAccount = super.update(myAccount, profile);
			//Remove dos contatos da pessoa
			toRemove.removeContact(myAccount.getId());
		}

		return view;
	}

	public ContatoView load(Profile profile, ContatoView view) {
		return null;
	}

}
