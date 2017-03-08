package me.ulguim.tcc.manager;

import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.FeedView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class HomeManager extends TCCBaseManager {

	public FeedView listFeed(Profile profile) throws ValidationException {

		return null;
	}

}
