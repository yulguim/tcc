package me.ulguim.tcc.parser;

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

		return view;
	}

	public static Post parse(PostView view) {
		Post entity = new Post();

		return entity;
	}

}
