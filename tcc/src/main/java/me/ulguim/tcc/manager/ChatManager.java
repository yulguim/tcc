package me.ulguim.tcc.manager;

import in.k2s.sdk.jpa.sequence.SequenceGenerator;
import in.k2s.sdk.util.data.DataUtil;
import in.k2s.sdk.web.message.MessageWarning;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.ArquivoBean;
import me.ulguim.tcc.bean.MensagemBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Chat;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.parser.ChatParser;
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

	@Inject
	private AccountService accountService;

	public List<ChatView> list(Profile profile) {
		List<Chat> chatList = chatService.selectAllChatByAccountId(getAccountLogada(profile).getId());
		List<ChatView> viewList = new ArrayList<>();
		chatList.forEach(c -> {
			ChatView view = new ChatView();
			view.setId(c.getId());
			if (!c.getUser1().equals(getAccountLogada(profile).getId())) {
				view.setUser(c.getUser1());
				view.setUserLabel(((Account)accountService.selectById(Account.class, c.getUser1())).getLabel());
			} else {
				view.setUser(c.getUser2());
				view.setUserLabel(((Account)accountService.selectById(Account.class, c.getUser2())).getLabel());
			}

			viewList.add(view);
		});

		return viewList;
	}

	public ChatView load(Profile profile, Long id) throws ValidationException {
		Chat chat = chatService.selectChatByIdAccountId(id, getAccountLogada(profile).getId());

		ChatView view = ChatParser.parse(chat, true);

		return view;
	}

	public MensagemView saveMensagem(Profile profile, MensagemView view) {
		Chat chat;
		//Se nao existe chat entre usuarios, criar
		if (view.getChatId() != null) {
			chat = chatService.selectChatByIdAccountId(view.getChatId(), getAccountLogada(profile).getId());
		} else {
			Long user2 = accountService.selectByChave(Account.class, view.getUserKey()).getId();
			chat = chatService.selectChatByUser1IdUser2Id(getAccountLogada(profile).getId(), user2);
			if (chat == null) {
				chat = new Chat();
				chat.setUser1(getAccountLogada(profile).getId());
				chat.setUser2(user2);
				chat = super.save(chat, profile);
			}
		}

		MensagemBean bean = new MensagemBean();
		bean.setId(SequenceGenerator.generate());
		bean.setUserId(getAccountLogada(profile).getId());
		bean.setData(DataUtil.getTimestamp());
		bean.setMensagem(view.getMensagem());

		chat.addMensagem(bean);
		super.update(chat, profile);

		view.setId(bean.getId());
		view.setUserId(bean.getUserId());
		view.setData(bean.getData());
		return view;
	}

	public MensagemView deleteMensagem(Profile profile, MensagemView view) throws ValidationException {
		Chat chat = chatService.selectById(Chat.class, view.getChatId());
		if (chat.getUser1().equals(getAccountLogada(profile).getId()) || chat.getUser2().equals(getAccountLogada(profile).getId())) {
			//Usuario nao esta no chat, quer apagar porque?
		}

		MensagemBean mensagemBean = chat.getMensagemById(view.getId());
		if (mensagemBean == null || !mensagemBean.getUserId().equals(getAccountLogada(profile).getId())) {
			//Mensagem nao existe ou nao foi usuario que escreveu
			throw new ValidationException(new MessageWarning("warn.delete"));
		}

		chat.deleteMensagemById(view.getId());
		super.update(chat, profile);
		return view;
	}

}
