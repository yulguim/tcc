package me.ulguim.tcc.parser;

import in.k2s.sdk.web.view.parse.BaseParser;
import me.ulguim.tcc.bean.MensagemBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Chat;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ChatView;

import java.util.List;

/**
 * Created by yulle on 09/03/17.
 */
public class ChatParser extends BaseParser {

	public static ChatView parse(Chat entity, boolean withMensagens) {
		ChatView view = new ChatView();
		view.setId(entity.getId());
		view.setUser1(entity.getUser1());
		view.setUser2(entity.getUser2());
		if (withMensagens) {
			view.setMensagens(entity.getMensagens());
		}

		return view;
	}

	public static ChatView parseToProjeto(List<MensagemBean> mensagens) {
		ChatView view = new ChatView();
		view.setMensagens(mensagens);

		return view;
	}

}
