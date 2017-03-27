package me.ulguim.tcc.manager;

import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import in.k2s.sdk.util.string.StringUtil;
import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.message.MessageSeverity;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.HabilidadeBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.entity.other.Habilidade;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.HabilidadeService;
import me.ulguim.tcc.service.PerfilService;
import me.ulguim.tcc.view.PerfilView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class PerfilManager extends TCCBaseManager {

	@Inject
	private PerfilService perfilService;

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

		Perfil perfil = getAccountLogadaLoaded(profile).getProfile();
		if (perfil == null) {
			view.setHasNoProfile(true);
			view.setName(getAccountLogadaLoaded(profile).getName());
			view.setLastname(getAccountLogada(profile).getLastname());
		}

		return view;
	}

	public PerfilView save(Profile profile, PerfilView view) throws ValidationException {
		validate(view);

		Account accountLogada = getAccountLogadaLoaded(profile);
		accountLogada.setName(view.getName());
		accountLogada.setLastname(view.getLastname());
		accountLogada = super.update(accountLogada, profile);
		Perfil perfil = perfilService.selectPerfilByAccountId(getAccountLogada(profile).getId());
		if (perfil == null) {
			perfil = new Perfil();
			perfil.setAccount(accountLogada);
			perfil = super.save(perfil, profile);

			System.out.println("perfil = " + perfil);
		} else {

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
