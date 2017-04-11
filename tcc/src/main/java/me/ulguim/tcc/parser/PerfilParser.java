package me.ulguim.tcc.parser;

import in.k2s.sdk.util.data.DataUtil;
import in.k2s.sdk.web.view.parse.BaseParser;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.entity.Post;
import me.ulguim.tcc.view.PerfilView;
import me.ulguim.tcc.view.PostView;

/**
 * Created by yulle on 09/03/17.
 */
public class PerfilParser extends BaseParser {

	public static PerfilView parse(Account account, Perfil entity) {
		PerfilView view = new PerfilView();
		view.setKey(entity.getChave());
		view.setLabel(entity.getLabel());
		if (account != null) {
			view.setAvatar(account.getAvatar());
		}

		return view;
	}

}
