package me.ulguim.tcc.manager.other;

import in.k2s.sdk.web.profile.Profile;
import me.ulguim.tcc.bean.HabilidadeBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.other.Habilidade;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.service.HabilidadeService;
import me.ulguim.tcc.view.ContatoView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class BatchManager extends TCCBaseManager {

	@Inject
	private HabilidadeService habilidadeService;

	public List<HabilidadeBean> list(Profile profile) {
		List<Habilidade> habilidadeList = habilidadeService.selectAll();

		List<HabilidadeBean> beanList = new ArrayList<>();
		habilidadeList.forEach(h -> {
			beanList.add(new HabilidadeBean(h.getId(), h.getLabel()));
		});

		return beanList;
	}

}
