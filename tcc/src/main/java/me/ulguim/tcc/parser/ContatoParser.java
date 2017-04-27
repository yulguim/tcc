package me.ulguim.tcc.parser;

import in.k2s.sdk.web.view.parse.BaseParser;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.AccountProjeto;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.entity.enumeration.AccountProjetoStatus;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.PerfilView;

/**
 * Created by yulle on 09/03/17.
 */
public class ContatoParser extends BaseParser {

	public static ContatoView parse(Account entity) {
		return parse(null, entity);
	}

	public static ContatoView parse(AccountProjeto accountProjeto, Account entity) {
		ContatoView view = new ContatoView();
		view.setKey(entity.getChave());
		view.setLabel(entity.getLabel());
		view.setAvatar(entity.getAvatar());

		if (accountProjeto != null) {
			view.setRequested(accountProjeto.getStatus().equals(AccountProjetoStatus.REQUESTED));
			view.setParticipante(accountProjeto.getStatus().equals(AccountProjetoStatus.ACTIVE));
		}

		return view;
	}

}
