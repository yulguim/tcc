package me.ulguim.tcc.manager;

import in.k2s.sdk.web.message.MessageWarning;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Contato;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.entity.Projeto;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.parser.AccountParser;
import me.ulguim.tcc.parser.ContatoParser;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.service.PerfilService;
import me.ulguim.tcc.service.ProjetoService;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.ProjetoView;
import me.ulguim.tcc.view.other.SearchView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchManager extends TCCBaseManager {

	@Inject
	private PerfilService perfilService;

	@Inject
	private ProjetoService projetoService;

	public SearchView search(Profile profile, SearchView view) throws ValidationException {
		//TODO

		Account accountLogadaLoaded = getAccountLogadaLoaded(profile);

		List<Perfil> perfilList = perfilService.selectPerfilBySeach(view.getSearch());
		if (perfilList != null && !perfilList.isEmpty()) {
			List<ContatoView> result1 = new ArrayList<>();
			perfilList.forEach(p -> {
				ContatoView contato = ContatoParser.parse(p.getAccount());
				if (p.getId().equals(accountLogadaLoaded.getId())) { //Ver se o profile eh o meu
					contato.setMyProfile(true);
				} else if (accountLogadaLoaded.contactExists(accountLogadaLoaded.getId())) { //Ver se eh amigo
					contato.setFriend(true);
				} else if (p.getAccount().getExtraParams().existsRequest(accountLogadaLoaded.getId())) { //Ver se tem request meu
					contato.setRequestedByMe(true);
				} else if (accountLogadaLoaded.getExtraParams().existsRequest(p.getId())) { //Ver se tem request do user para mim
					contato.setRequestedByUser(true);
				}
				result1.add(contato);
			});
			view.setPerfilResults(result1);
		}

		List<Projeto> projetoList = projetoService.selectProjetoBySeach(view.getSearch());
		if (projetoList != null && !projetoList.isEmpty()) {
			List<ProjetoView> result2 = new ArrayList<>();
			projetoList.forEach(p -> {
				ProjetoView projeto = new ProjetoView();
				projeto.setId(p.getId());
				projeto.setKey(p.getChave());
				projeto.setTitulo(p.getTitulo());
				projeto.setDescricao(p.getDescricao());
				projeto.setPermiteRequest(p.getPermiteRequest());
				result2.add(projeto);
			});

			view.setProjetoResults(result2);
		}

		return view;
	}

}
