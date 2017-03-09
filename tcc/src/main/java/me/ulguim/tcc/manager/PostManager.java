package me.ulguim.tcc.manager;

import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.message.MessageSeverity;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Post;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.parser.PostParser;
import me.ulguim.tcc.service.PostService;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ComentarioView;
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
		validate(view);

		Post entity;
		if (view.getKey() == null) {
			entity = PostParser.parse(view);
			entity = super.save(entity, profile);
		} else {
			entity = postService.selectByChave(Post.class, view.getKey());
			if (!isMyPost(profile, entity)) throw new ValidationException(new Message("error.save", MessageSeverity.ERROR));

			//TODO update
			entity = super.update(entity, profile);
		}

		return view;
	}

	public PostView delete(Profile profile, PostView view) throws ValidationException {
		Post entity = postService.selectByChave(Post.class, view.getKey());
		if (!isMyPost(profile, entity)) throw new ValidationException(new Message("error.save", MessageSeverity.ERROR));

		super.delete(entity, profile);
		return view;
	}

	/* Comentarios */

	public ComentarioView saveComentario(Profile profile, ComentarioView view) throws ValidationException {
		return null;
	}

	public ComentarioView deleteComentario(Profile profile, ComentarioView view) throws ValidationException {
		return null;
	}

	/* PRIVATE */

	private void validate(PostView view) throws ValidationException {
		if (view == null) throw new ValidationException(new Message("error.save", MessageSeverity.ERROR));
	}

	private boolean isMyPost(Profile profile, Post entity) throws ValidationException {
		return !entity.getAuthor().getId().equals(super.getAccountLogada(profile).getId());
	}

}
