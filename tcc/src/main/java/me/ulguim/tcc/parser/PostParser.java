package me.ulguim.tcc.parser;

import in.k2s.sdk.util.data.DataUtil;
import in.k2s.sdk.web.view.parse.BaseParser;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Post;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.PostView;

/**
 * Created by yulle on 09/03/17.
 */
public class PostParser extends BaseParser {

	public static PostView parse(Post entity) {
		PostView view = new PostView();
		view.setKey(entity.getChave());
		view.setPost(entity.getPost());
		view.setAuthorId(entity.getAuthor().getId());
		view.setAuthorKey(entity.getAuthor().getChave());
		view.setAuthorAvatar(entity.getAuthor().getAvatar());
		view.setAuthorLabel(entity.getAuthor().getLabel());
		view.setInsertTime(DataUtil.format(entity.getInsertTime(), "dd/MM/yyyy HH:mm:ss"));
		if (!entity.getComentarioList().isEmpty()) {
			entity.getComentarioList().forEach(c -> view.getCommentList().add(ComentarioParser.parse(c)));
		}

		return view;
	}

	public static Post parse(PostView view) {
		Post entity = new Post();
		entity.setPost(view.getPost());

		return entity;
	}

}
