package me.ulguim.tcc.manager;

import in.k2s.sdk.web.profile.Profile;
import me.ulguim.tcc.bean.ArquivoBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Chat;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.service.ChatService;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ChatView;
import me.ulguim.tcc.view.MensagemView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChatManager extends TCCBaseManager {

	@Inject
	private ChatService chatService;

	public List<ChatView> list(Profile profile) {
		List<Chat> chatList = chatService.selectAllChatByAccountId(getAccountLogada(profile).getId());
		List<ChatView> viewList = new ArrayList<>();
		chatList.forEach(c -> {

		});

		return viewList;
	}

	public MensagemView saveMensagem(Profile profile, MensagemView view) {
		Chat chat = chatService.selectChatByIdAccountId(view.getChatId(), getAccountLogada(profile).getId());


		return view;
	}

}
