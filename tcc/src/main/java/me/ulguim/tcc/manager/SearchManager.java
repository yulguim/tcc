package me.ulguim.tcc.manager;

import in.k2s.sdk.web.message.MessageWarning;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.parser.AccountParser;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.service.PerfilService;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.other.SearchView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchManager extends TCCBaseManager {

	public SearchView search(Profile profile, SearchView view) throws ValidationException {

		return null;
	}

}
