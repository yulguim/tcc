package me.ulguim.tcc.manager;

import in.k2s.sdk.web.profile.Profile;
import me.ulguim.tcc.bean.ArquivoBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.service.ChatService;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ChatView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class ChatManager extends TCCBaseManager {

	@Inject
	private ChatService chatService;

	public List<ChatView> list(Profile profile) {

		return null;
	}

}
