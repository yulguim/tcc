package me.ulguim.tcc.manager;

import in.k2s.sdk.jpa.sequence.SequenceGenerator;
import in.k2s.sdk.util.data.DataUtil;
import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.message.MessageSuccess;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.MensagemBean;
import me.ulguim.tcc.bean.NotificationBean;
import me.ulguim.tcc.entity.*;
import me.ulguim.tcc.entity.enumeration.AccountProjetoStatus;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.parser.ChatParser;
import me.ulguim.tcc.parser.ContatoParser;
import me.ulguim.tcc.service.*;
import me.ulguim.tcc.view.*;
import me.ulguim.tcc.view.other.SearchView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjetoManager extends TCCBaseManager {

	@Inject
	private ProjetoService projetoService;

	@Inject
	private AccountService accountService;

	@Inject
	private AccountProjetoService accountProjetoService;

	@Inject
	private ChatService chatService;

	public List<ProjetoSimpleView> list(Profile profile) throws ValidationException {
		List<Projeto> meusProjetos = projetoService.selectAllByAccountId(getAccountLogada(profile).getId());

		List<ProjetoSimpleView> list = new ArrayList<>();
		meusProjetos.forEach(p -> {
			ProjetoSimpleView view = new ProjetoSimpleView(p.getId(), p.getChave(), p.getTitulo());
			//TODO
			list.add(view);
		});

		return list;
	}

	public ProjetoView load(Profile profile, ProjetoView view) throws ValidationException {
		Projeto entity = projetoService.selectByChave(Projeto.class, view.getKey());

		view = new ProjetoView();
		view.setTitulo(entity.getTitulo());
		view.setDescricao(entity.getDescricao());
		view.setKey(entity.getChave());
		view.setPermiteRequest(entity.getPermiteRequest());

		Account owner = entity.getOwner();
		ContatoView ownerView = new ContatoView();
		ownerView.setAvatar(owner.getAvatar());
		ownerView.setKey(owner.getChave());
		ownerView.setLabel(owner.getLabel());
		view.setOwner(ownerView);
		view.setNumeroParticipantes(entity.getAccountProjetoList() != null ? entity.getAccountProjetoList().size() : 0);

		if (owner.getId().equals(getAccountLogada(profile).getId())) {
			view.setMeuProjeto(true);
		} else {
			AccountProjeto accountProjeto = entity.getAccountProjetoByAccountId(getAccountLogada(profile).getId());
			if (accountProjeto != null) {
				if (accountProjeto.getParticipante() && accountProjeto.getStatus().equals(AccountProjetoStatus.ACTIVE)) {
					view.setSouParticipante(true);
				} else {
					view.setRequested(true);
				}
			}
		}

		//carregar dados privados do projeto TODO
		if (view.getMeuProjeto() || view.getSouParticipante()) {

		}

		return view;
	}

	public List<PostView> loadPosts(Profile profile, ProjetoView view) throws ValidationException {
		Projeto entity = projetoService.selectByChave(Projeto.class, view.getKey());

		List<Post> postList = entity.getPostList();
		List<PostView> list = new ArrayList<>();
		//TODO
		return list;
	}

	public List<ContatoView> loadParticipantes(Profile profile, ProjetoView view) throws ValidationException {
		List<ContatoView> list = new ArrayList<>();

		Projeto entity = projetoService.selectByChave(Projeto.class, view.getKey());
		if (entity.getAccountProjetoList() != null) {
			entity.getAccountProjetoList().forEach(ac -> {
				Account account = ac.getAccount();
				list.add(ContatoParser.parse(ac, account));
			});
		}

		return list;
	}

	public ChatView loadChat(Profile profile, ProjetoView view) throws ValidationException {
		Projeto entity = projetoService.selectByChave(Projeto.class, view.getKey());
		ChatView chat = ChatParser.parseToProjeto(entity.getMensagens());

		return chat;
	}

	public ProjetoView save(Profile profile, ProjetoView view) throws ValidationException {
		validate(view);
		Projeto entity;
		if (view.getId() == null) {
			//Cadastrar projeto
			entity = new Projeto();
			entity.setOwner(getAccountLogada(profile));
			entity.setTitulo(view.getTitulo());
			entity.setDescricao(view.getDescricao());
			entity = super.save(entity, profile);

			view.addMessage(new MessageSuccess("success.save"));
		} else {
			//Update
			entity = projetoService.selectById(Projeto.class, view.getId());
			if (!entity.getOwner().getId().equals(getAccountLogada(profile).getId())) {
				throw new ValidationException(new Message("warn.save"));
			}
			entity.setTitulo(view.getTitulo());
			entity.setDescricao(view.getDescricao());
			entity = super.update(entity, profile);

			view.addMessage(new MessageSuccess("success.update"));
		}

		return view;
	}

	public ProjetoView delete(Profile profile, ProjetoView view) throws ValidationException {
		Projeto entity = projetoService.selectById(Projeto.class, view.getId());
		if (!entity.getOwner().getId().equals(getAccountLogada(profile).getId())) {
			throw new ValidationException(new Message("warn.delete"));
		}

		entity.setStatus(Projeto.StatusProjeto.REMOVIDO);
		entity = super.update(entity, profile);

		return view;
	}

	/*
		MENSAGEM
	 */

	public MensagemView saveMensagemToOwner(Profile profile, MensagemView view) throws ValidationException {
		Projeto projeto = projetoService.selectById(Projeto.class, view.getId());

		Account accountLogada = getAccountLogada(profile);
		Chat chat = chatService.selectChatByUser1IdUser2Id(accountLogada.getId(), projeto.getOwner().getId());
		if (chat == null) {
			chat = new Chat();
			chat.setUser1(accountLogada.getId());
			chat.setUser2(projeto.getOwner().getId());
			chat = super.save(chat, profile);
		}

		MensagemBean bean = new MensagemBean();
		bean.setId(SequenceGenerator.generate());
		bean.setUserId(accountLogada.getId());
		bean.setData(DataUtil.getTimestamp());
		bean.setMensagem(view.getMensagem());

		chat.addMensagem(bean);
		super.update(chat, profile);

		return view;
	}

	public synchronized MensagemView saveMensagem(Profile profile, MensagemView view) throws ValidationException {
		Projeto projeto = projetoService.selectById(Projeto.class, view.getId());

		MensagemBean bean = new MensagemBean();
		bean.setId(SequenceGenerator.generate());
		bean.setUserId(getAccountLogada(profile).getId());
		bean.setMensagem(view.getMensagem());
		bean.setData(DataUtil.getTimestamp());

		projeto.addMensagem(bean);
		projeto = super.update(projeto, profile);

		return view;
	}

	public MensagemView deleteMensagem(Profile profile, MensagemView view) throws ValidationException {
		Projeto projeto = projetoService.selectByChave(Projeto.class, view.getProjetoKey());

		MensagemBean mensagem = projeto.getMensagemById(view.getId());
		if (!mensagem.getUserId().equals(getAccountLogada(profile).getId())) {
			throw new ValidationException(new Message("warn.delete"));
		}

		projeto.deleteMensagemById(view.getId());
		projeto = super.update(projeto, profile);
		return view;
	}

	/*
		PARTICIPANTE
	 */

	public ContatoView request(Profile profile, ContatoView view) throws ValidationException {
		Projeto projeto = projetoService.selectByChave(Projeto.class, view.getProjetoKey());

		Account accountLogada = getAccountLogada(profile);

		AccountProjeto accountProjeto = new AccountProjeto();
		accountProjeto.setAccount(accountLogada);
		accountProjeto.setProjeto(projeto);
		accountProjeto.setStatus(AccountProjetoStatus.REQUESTED);
		accountProjeto = super.save(accountProjeto, profile);

		Account owner = projeto.getOwner();
		owner.addNotification(NotificationBean.createNotification(NotificationBean.Label.PROJECT_REQUEST, accountLogada.getLabel() + " pediu para participar do projeto " + projeto.getTitulo() + ".", "/#/projeto/" + projeto.getChave()));
		super.update(owner);

		return view;
	}

	public ContatoView acceptRequest(Profile profile, ContatoView view) throws ValidationException {
		Projeto projeto = projetoService.selectByChave(Projeto.class, view.getProjetoKey());

		//Recuperar accountProjeto
		AccountProjeto accountProjeto = accountProjetoService.selectByProjetoIdAndAccountId(projeto.getId(), getAccountLogada(profile).getId());
		if (accountProjeto != null) {
			//Adiciona como participante ativo
			accountProjeto.setParticipante(true);
			accountProjeto.setStatus(AccountProjetoStatus.ACTIVE);
			accountProjeto = super.update(accountProjeto, profile);

			Account account = accountProjeto.getAccount();
			account.addNotification(NotificationBean.createNotification(NotificationBean.Label.PROJECT_ACCEPT, projeto.getOwner().getLabel() + " aceitou você no projeto " + projeto.getTitulo() + ".", "/#/projeto/" + projeto.getChave()));
			super.update(account);

		}

		return view;
	}

	public ContatoView deleteParticipante(Profile profile, ContatoView view) throws ValidationException {
		Projeto projeto = projetoService.selectByChave(Projeto.class, view.getProjetoKey());

		AccountProjeto accountProjeto;
		//Dono esta excluindo alguem
		if (view.getKey() != null) {
			Account account = accountService.selectByChave(Account.class, view.getKey());
			accountProjeto = accountProjetoService.selectByProjetoIdAndAccountId(projeto.getId(), account.getId());
			super.delete(accountProjeto, profile);
		} else {
			//Eu estou me excluindo
			accountProjeto = accountProjetoService.selectByProjetoIdAndAccountId(projeto.getId(), getAccountLogada(profile).getId());
			if (accountProjeto != null) {
				super.delete(accountProjeto, profile);
			}
		}

		return view;
	}

	/*
		PRIVATE
	 */

	private void validate(ProjetoView view) throws ValidationException {

	}

}
