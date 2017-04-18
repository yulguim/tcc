package me.ulguim.tcc.manager;

import in.k2s.sdk.web.message.MessageSuccess;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.entity.Projeto;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.parser.ContatoParser;
import me.ulguim.tcc.service.PerfilService;
import me.ulguim.tcc.service.ProjetoService;
import me.ulguim.tcc.view.ContatoView;
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

		view = new ProjetoView();
		view.setTitulo(entity.getTitulo());
		view.setDescricao(entity.getDescricao());

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

			view.addMessage(new MessageSuccess("success.update"));
		}

		return view;
	}

	private void validate(ProjetoView view) throws ValidationException {

	}

}
