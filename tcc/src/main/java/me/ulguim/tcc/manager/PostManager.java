package me.ulguim.tcc.manager;

import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Post;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.PostService;
import me.ulguim.tcc.view.FeedView;
import me.ulguim.tcc.view.PostView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class PostManager extends TCCBaseManager {

	@Inject
	private PostService postService;

	public PostView load(Profile profile, PostView view) throws ValidationException {
		Post entity = postService.selectByChave(Post.class, view.getKey());

		return view;
	}

	public PostView save(Profile profile, PostView view) throws ValidationException {

		return view;
	}

	public PostView delete(Profile profile, PostView view) throws ValidationException {
		Post entity = postService.selectByChave(Post.class, view.getKey());
		super.delete(entity, profile);

		return view;
	}

}
