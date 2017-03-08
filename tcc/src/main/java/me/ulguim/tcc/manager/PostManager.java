package me.ulguim.tcc.manager;

import br.com.caelum.vraptor.Post;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.view.FeedView;
import me.ulguim.tcc.view.PostView;
import org.springframework.stereotype.Component;

@Component
public class PostManager extends TCCBaseManager {

	public PostView load(Profile profile, PostView view) throws ValidationException {

		return view;
	}

	public PostView save(Profile profile, PostView view) throws ValidationException {

		return view;
	}

	public PostView delete(Profile profile, PostView view) throws ValidationException {

		return view;
	}

}
