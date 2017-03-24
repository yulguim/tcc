package me.ulguim.tcc.manager;

import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.location.Cidade;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.LocalizacaoService;
import me.ulguim.tcc.view.FeedView;
import me.ulguim.tcc.view.LocalizacaoView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocalizacaoManager extends TCCBaseManager {

	@Inject
	private LocalizacaoService localizacaoService;

	public List<LocalizacaoView> searchCidade(String str) {
		List<Cidade> cidades = localizacaoService.selectCidadeByStr(str);

		List<LocalizacaoView> list = new ArrayList<>();
		cidades.forEach(c -> {
			list.add(new LocalizacaoView(c.getId(), c.getNome() + ", " + c.getEstado().getSigla()));
		});

		return list;
	}

}
