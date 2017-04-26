package me.ulguim.tcc.manager;

import in.k2s.sdk.jpa.sequence.SequenceGenerator;
import in.k2s.sdk.util.data.DataUtil;
import in.k2s.sdk.web.message.MessageSuccess;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.MensagemBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.entity.Projeto;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.parser.ContatoParser;
import me.ulguim.tcc.service.PerfilService;
import me.ulguim.tcc.service.ProjetoService;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.MensagemView;
import me.ulguim.tcc.view.ProjetoSimpleView;
import me.ulguim.tcc.view.ProjetoView;
import me.ulguim.tcc.view.other.SearchView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjetoManager extends TCCBaseManager {

	@Inject
	private ProjetoService projetoService;

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
		//TODO validar se projeto eh meu
		//TODO na real pode fazer load de qualquer projeto, soh nao pode editar

		view = new ProjetoView();
		view.setTitulo(entity.getTitulo());
		view.setDescricao(entity.getDescricao());
		view.setKey(entity.getChave());
		if (entity.getOwner().getId().equals(getAccountLogada(profile).getId())) {
			view.setMeuProjeto(true);
		} else if (entity.getAccountProjetoByAccountId(getAccountLogada(profile).getId()) != null) {
			view.setSouParticipante(true);
		}

		//carregar dados privados do projeto TODO
		if (view.getMeuProjeto() || view.getSouParticipante()) {

		}

		return view;
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
				//TODO tentando editar projeto que nao eh meu
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
			//TODO tentando excluir projeto que nao eh meu
		}

		entity.setStatus(Projeto.StatusProjeto.REMOVIDO);
		entity = super.update(entity, profile);

		return view;
	}

	/*
		MENSAGEM
	 */

	public MensagemView saveMensagem(Profile profile, MensagemView view) throws ValidationException {
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
		Projeto projeto = projetoService.selectById(Projeto.class, view.getId());
		//TODO checar se sou dono do projeto ou da mensagem
		projeto.deleteMensagemById(view.getId());

		projeto = super.update(projeto, profile);
		return view;
	}

	/*
		PARTICIPANTE
	 */

	public ContatoView request(Profile profile, ContatoView view) throws ValidationException {
		Projeto projeto = projetoService.selectByChave(Projeto.class, view.getProjetoKey());

		return view;
	}

	public ContatoView acceptRequest(Profile profile, ContatoView view) throws ValidationException {
		Projeto projeto = projetoService.selectByChave(Projeto.class, view.getProjetoKey());

		return view;
	}

	public ContatoView deleteParticipante(Profile profile, ContatoView view) throws ValidationException {
		Projeto projeto = projetoService.selectByChave(Projeto.class, view.getProjetoKey());

		return view;
	}

	/*
		PRIVATE
	 */

	private void validate(ProjetoView view) throws ValidationException {

	}

}
