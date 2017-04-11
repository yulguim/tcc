package me.ulguim.tcc.manager;

import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import in.k2s.sdk.util.string.StringUtil;
import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.message.MessageSeverity;
import in.k2s.sdk.web.message.MessageWarning;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.HabilidadeBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.entity.other.Habilidade;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.parser.PerfilParser;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.service.HabilidadeService;
import me.ulguim.tcc.service.PerfilService;
import me.ulguim.tcc.view.PerfilView;
import org.springframework.stereotype.Component;
import sun.misc.Perf;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class PerfilManager extends TCCBaseManager {

	@Inject
	private PerfilService perfilService;

	@Inject
	private AccountService accountService;

	@Inject
	private HabilidadeService habilidadeService;

	@Inject
	private ProfileSingleton profileSingleton;

	public List<HabilidadeBean> listHabilidades(Profile profile) {
		List<Habilidade> habilidadeList = habilidadeService.selectAll();

		List<HabilidadeBean> beanList = new ArrayList<>();
		habilidadeList.forEach(h -> beanList.add(new HabilidadeBean(h.getId(), h.getLabel())));

		return beanList;
	}

	public PerfilView meuPerfil(Profile profile) throws ValidationException {
		PerfilView view = new PerfilView();
		view.setName(getAccountLogadaLoaded(profile).getName());
		view.setLastname(getAccountLogada(profile).getLastname());

		Perfil perfil = getAccountLogadaLoaded(profile).getProfile();
		if (perfil == null) {
			view.setHasNoProfile(true);
		} else {
			view.setHabilidades(perfil.getHabilidadeList());
		}

		return view;
	}

	public PerfilView load(Profile profile, PerfilView view) throws ValidationException {
		Account accountLogadaLoaded = getAccountLogadaLoaded(profile);

		Account account = accountService.selectByChave(Account.class, view.getKey());
		Perfil perfil = perfilService.selectByChave(Perfil.class, view.getKey());
		if (account == null || perfil == null) {
			throw new ValidationException(new MessageWarning("warn.load"));
		}

		view = PerfilParser.parse(account, perfil);
		if (account.getId().equals(accountLogadaLoaded.getId())) { //Ver se o profile eh o meu
			view.setMyProfile(true);
		} else if (accountLogadaLoaded.contactExists(accountLogadaLoaded.getId())) { //Ver se eh amigo
			view.setFriend(true);
		} else if (account.getExtraParams().existsRequest(accountLogadaLoaded.getId())) { //Ver se tem request meu
			view.setRequestedByMe(true);
		} else if (accountLogadaLoaded.getExtraParams().existsRequest(account.getId())) { //Ver se tem request do user para mim
			view.setRequestedByUser(true);
		}

		return view;
	}

	public PerfilView save(Profile profile, PerfilView view) throws ValidationException {
		validate(view);

		Account accountLogada = getAccountLogadaLoaded(profile);
		accountLogada.setName(view.getName());
		accountLogada.setLastname(view.getLastname());
		accountLogada = super.update(accountLogada, profile);
		Perfil perfil = accountLogada.getProfile();
		if (perfil == null) {
			perfil = new Perfil();
			perfil.setId(accountLogada.getId());
			perfil.setChave(accountLogada.getChave());
			perfil.setAccount(accountLogada);
			perfil.setHabilidadeList(view.getHabilidades());
			perfil = auditoria(perfil, profile);
			perfil = super.service.insert(perfil);

			System.out.println("perfil = " + perfil);
		} else {
			perfil.setHabilidadeList(view.getHabilidades());
			perfil = super.update(perfil, profile);
		}

		profile.setUsuario(accountLogada);
		profileSingleton.add(profile);
		return view;
	}

	private void validate(PerfilView view) throws ValidationException {
		if (view == null) throw new ValidationException(new Message("warn.save", MessageSeverity.WARN));

		ValidationException ex = new ValidationException();
		if (StringUtil.isEmpty(view.getName())) {
			ex.addMessage(new Message("message.warn.required", MessageSeverity.WARN, "Nome"));
		}
		if (StringUtil.isEmpty(view.getLastname())) {
			ex.addMessage(new Message("message.warn.required", MessageSeverity.WARN, "Sobrenome"));
		}

		if (ex.haveMessages()) throw ex;
	}

}
