package me.ulguim.tcc.manager;

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

	public List<ProjetoView> list(Profile profile) throws ValidationException {
		List<Projeto> meusProjetos = projetoService.selectAllByAccountId(getAccountLogada(profile).getId());

		List<ProjetoView> list = new ArrayList<>();
		//TODO

		return list;
	}

	public ProjetoView load(Profile profile, ProjetoView view) throws ValidationException {

		return view;
	}

	public ProjetoView save(Profile profile, ProjetoView view) throws ValidationException {

		return view;
	}

}
