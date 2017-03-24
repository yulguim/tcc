package me.ulguim.tcc.manager;

import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.HabilidadeBean;
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

	public List<HabilidadeBean> listHabilidades(Profile profile) {
		List<Habilidade> habilidadeList = habilidadeService.selectAll();

		List<HabilidadeBean> beanList = new ArrayList<>();
		habilidadeList.forEach(h -> beanList.add(new HabilidadeBean(h.getId(), h.getLabel())));

		return beanList;
	}

	public PerfilView meuPerfil(Profile profile) throws ValidationException {
		PerfilView view = new PerfilView();

		Perfil perfil = perfilService.selectPerfilByAccountId(getAccountLogada(profile).getId());
		if (perfil == null) {
			view.setHasNoProfile(true);
			view.setName(getAccountLogada(profile).getName());
			view.setLastname(getAccountLogada(profile).getLastname());
		}

		return view;
	}

	public PerfilView save(Profile profile, PerfilView view) throws ValidationException {
		//TODO validate

		Perfil perfil = perfilService.selectPerfilByAccountId(getAccountLogada(profile).getId());
		if (perfil == null) {
			//novo perfil
			perfil = new Perfil();
			perfil.setAccount(getAccountLogadaLoaded(profile));
			perfil = super.save(perfil, profile);

			System.out.println("perfil = " + perfil);
		} else {

		}

		return view;
	}

}
