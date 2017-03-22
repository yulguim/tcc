package me.ulguim.tcc.manager;

import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.location.Cidade;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.LocalizacaoService;
import me.ulguim.tcc.view.FeedView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class LocalizacaoManager extends TCCBaseManager {

	@Inject
	private LocalizacaoService localizacaoService;

	public List<Cidade> searchCidade(String str) {
		List<Cidade> cidades = localizacaoService.selectCidadeByStr(str);
		return cidades;
	}

}
