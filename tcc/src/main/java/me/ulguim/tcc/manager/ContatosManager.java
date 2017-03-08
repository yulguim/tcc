package me.ulguim.tcc.manager;

import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.profile.ProfileBean;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Contato;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.LoginService;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.ref.SoftReference;
import java.util.List;

@Component
public class ContatosManager extends TCCBaseManager {

	public List<ContatoView> list(Profile profile) {
		Account accountLogada = super.getAccountLogada(profile);
		return null;
	}

	public ContatoView acceptRequest(Profile profile, ContatoView view) {
		return null;
	}

	public ContatoView load(Profile profile, ContatoView view) {
		return null;
	}

}
