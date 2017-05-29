package me.ulguim.tcc.manager;

import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Projeto;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.service.ProjetoService;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.FeedView;
import me.ulguim.tcc.view.ProjetoSimpleView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class HomeManager extends TCCBaseManager {

	@Inject
	private ProjetoService projetoService;

	public List<ProjetoSimpleView> listProjects(Profile profile) throws ValidationException {
		List<Projeto> meusProjetos = projetoService.selectAll();

		List<ProjetoSimpleView> list = new ArrayList<>();

		if (meusProjetos != null) {
			meusProjetos.forEach(p -> {
				ProjetoSimpleView view = new ProjetoSimpleView(p.getId(), p.getChave(), p.getTitulo());
				list.add(view);
			});
		}

		return list;
	}

}
