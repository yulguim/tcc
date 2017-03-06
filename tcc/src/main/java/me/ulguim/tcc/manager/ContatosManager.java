package me.ulguim.tcc.manager;

import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.profile.ProfileBean;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Contato;
import me.ulguim.tcc.service.LoginService;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.ref.SoftReference;
import java.util.List;

@Component
public class ContatosManager {

	public List<ContatoView> list(Profile profile) {
		StringBuilder hello = new StringBuilder().append("hello");
		SoftReference<StringBuilder> soft = new SoftReference<StringBuilder>(hello);

		hello = null;
		return null;
	}

	public ContatoView load(Profile profile, ContatoView view) {
		return null;
	}

}
