package me.ulguim.tcc.parser;

import in.k2s.sdk.web.view.parse.BaseParser;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.PerfilView;

/**
 * Created by yulle on 09/03/17.
 */
public class ContatoParser extends BaseParser {

	public static ContatoView parse(Perfil entity) {
		ContatoView view = new ContatoView();
		view.setKey(entity.getChave());
		view.setLabel(entity.getLabel());

		return view;
	}

}
