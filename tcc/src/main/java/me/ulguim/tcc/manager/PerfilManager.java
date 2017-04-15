package me.ulguim.tcc.manager;

import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import in.k2s.sdk.util.string.StringUtil;
import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.message.MessageSeverity;
import in.k2s.sdk.web.message.MessageSuccess;
import in.k2s.sdk.web.message.MessageWarning;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.HabilidadeBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Ocupacao;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.entity.location.Cidade;
import me.ulguim.tcc.entity.other.Habilidade;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.parser.PerfilParser;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.service.HabilidadeService;
import me.ulguim.tcc.service.OcupacaoService;
import me.ulguim.tcc.service.PerfilService;
import me.ulguim.tcc.view.LocalizacaoView;
import me.ulguim.tcc.view.OcupacaoView;
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
	private OcupacaoService ocupacaoService;

	public List<HabilidadeBean> listHabilidades(Profile profile) {
		List<Habilidade> habilidadeList = habilidadeService.selectAll();

		List<HabilidadeBean> beanList = new ArrayList<>();
		habilidadeList.forEach(h -> beanList.add(new HabilidadeBean(h.getId(), h.getLabel())));

		return beanList;
	}

	public List<OcupacaoView> listOcupacoes() {
		List<Ocupacao> ocupacaoList = ocupacaoService.selectAll();

		List<OcupacaoView> ocupacaoViewList = new ArrayList<>();
		ocupacaoList.forEach(o -> ocupacaoViewList.add(new OcupacaoView(o.getId(), o.getLabel())));

		return ocupacaoViewList;
	}

	public PerfilView meuPerfil(Profile profile) throws ValidationException {
		PerfilView view = new PerfilView();
		view.setName(getAccountLogadaLoaded(profile).getName());
		view.setLastname(getAccountLogada(profile).getLastname());

		Perfil perfil = getAccountLogadaLoaded(profile).getProfile();
		if (perfil == null) {
			view.setHasNoProfile(true);
		} else {
			view.setAbout(perfil.getAbout());
			view.setHabilidades(perfil.getHabilidadeList());
			view.setLinks(perfil.getSocialNetworkList());
			if (perfil.getOcupacao() != null) {
				view.setOcupacao(new OcupacaoView(perfil.getOcupacao().getId(), perfil.getOcupacao().getLabel()));
				view.setOcupacaoNome(perfil.getOcupacao().getLabel());
			}
			if (perfil.getCidade() != null) {
				Cidade c = perfil.getCidade();
				view.setLocalizacao(new LocalizacaoView(c.getId(), c.getNome() + ", " + c.getEstado().getSigla()));
				view.setLocalizacaoNome(c.getNome() + ", " + c.getEstado().getSigla());
			}
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
		} else if (accountLogadaLoaded.contactExists(account.getId())) { //Ver se eh amigo
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
			perfil.setAbout(view.getAbout());
			perfil.setHabilidadeList(view.getHabilidades());
			Cidade cidade = perfilService.selectById(Cidade.class, view.getLocalizacao().getId());
			perfil.setCidade(cidade);
			Ocupacao ocupacao = perfilService.selectById(Ocupacao.class, view.getOcupacao().getId());
			perfil.setOcupacao(ocupacao);
			perfil.setSocialNetworkList(view.getLinks());
			perfil = auditoria(perfil, profile);
			perfil = super.service.insert(perfil);
		} else {
			perfil.setAbout(view.getAbout());
			perfil.setHabilidadeList(view.getHabilidades());
			Cidade cidade = perfilService.selectById(Cidade.class, view.getLocalizacao().getId());
			perfil.setCidade(cidade);
			Ocupacao ocupacao = perfilService.selectById(Ocupacao.class, view.getOcupacao().getId());
			perfil.setOcupacao(ocupacao);
			perfil.setSocialNetworkList(view.getLinks());
			perfil = super.update(perfil, profile);
		}

		profile.setUsuario(accountLogada);
		super.getProfileSingleton().add(profile);
		view.setLocalizacaoNome(view.getLocalizacao().getLabel());
		view.setOcupacaoNome(view.getOcupacao().getLabel());
		view.addMessage(new Message("success.save", MessageSeverity.SUCCESS));
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
