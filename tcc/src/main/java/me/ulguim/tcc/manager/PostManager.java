package me.ulguim.tcc.manager;

import in.k2s.sdk.util.data.DataUtil;
import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.message.MessageSeverity;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.ComentarioBean;
import me.ulguim.tcc.entity.Account;
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
import java.util.ArrayList;

@Component
public class PostManager extends TCCBaseManager {

	@Inject
	private PostService postService;

	public PostView load(Profile profile, PostView view) throws ValidationException {
		Post entity = postService.selectByChave(Post.class, view.getKey());

		view = PostParser.parse(entity);

		return view;
	}

	public PostView save(Profile profile, PostView view) throws ValidationException {
		validate(view);

		if (view.getKey() != null) {
			throw new ValidationException(new Message("error.save", MessageSeverity.ERROR));
		}

		Post entity = PostParser.parse(view);
		entity.setAuthor(getAccountLogadaLoaded(profile));
		entity.setComentarioList(new ArrayList<>());
		entity = super.save(entity, profile);

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
		validateComment(view);
		Account accountLogada = getAccountLogada(profile);

		ComentarioBean bean = new ComentarioBean();
		bean.setIdUsuario(accountLogada.getId());
		bean.setLabelUsuario(accountLogada.getLabel());
		bean.setComentario(view.getComentario());
		bean.setLikes(0);
		bean.setInsertTime(DataUtil.format(DataUtil.getTimestamp(), "dd/MM/yyyy HH:mm:ss"));

		Post post = postService.selectByChave(Post.class, view.getPostKey());
		post.getComentarioList().add(bean);
		super.update(post, profile);

		view.setAuthorId(bean.getIdUsuario());
		view.setAuthorLabel(bean.getLabelUsuario());
		view.setInsertTime(bean.getInsertTime());
		return view;
	}

	public ComentarioView deleteComentario(Profile profile, ComentarioView view) throws ValidationException {
		Account accountLogada = getAccountLogada(profile);

		Post entity = postService.selectByChave(Post.class, view.getPostKey());
		ComentarioBean comentarioBean = entity.getComentarioList().get(view.getIndex());
		if (!comentarioBean.getIdUsuario().equals(accountLogada.getId())) {
			throw new ValidationException(new Message("error.save", MessageSeverity.ERROR));
		}

		entity.getComentarioList().remove(comentarioBean);
		super.update(entity, profile);

		return view;
	}

	/* PRIVATE */

	private void validate(PostView view) throws ValidationException {
		if (view == null) throw new ValidationException(new Message("error.save", MessageSeverity.ERROR));
	}

	private void validateComment(ComentarioView view) throws ValidationException {
		if (view == null) throw new ValidationException(new Message("error.save", MessageSeverity.ERROR));
	}

	private boolean isMyPost(Profile profile, Post entity) throws ValidationException {
		return !entity.getAuthor().getId().equals(super.getAccountLogada(profile).getId());
	}

}
