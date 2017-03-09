package me.ulguim.tcc.parser;

import in.k2s.sdk.web.view.parse.BaseParser;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.view.AccountView;

/**
 * Created by yulle on 09/03/17.
 */
public class AccountParser extends BaseParser {

	public static AccountView parse(Account entity) {
		AccountView view = new AccountView();
		view.setKey(entity.getChave());
		view.setEmail(entity.getEmail());
		view.setLabel(entity.getLabel());
		view.setAvatar(entity.getAvatar());
		view.setNome(entity.getName());
		view.setSobrenome(entity.getLastname());

		return view;
	}

	public static Account parse(AccountView view) {
		Account entity = new Account();

		return entity;
	}

}
