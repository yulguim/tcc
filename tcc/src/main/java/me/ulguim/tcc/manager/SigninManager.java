package me.ulguim.tcc.manager;

import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import in.k2s.sdk.util.data.DataUtil;
import in.k2s.sdk.util.hash.HashSHA;
import in.k2s.sdk.util.validator.EmailValidator;
import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.message.MessageSeverity;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.profile.ProfileBean;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.ExtraParamsBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.util.CookieHelper;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ContatoView;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import java.lang.ref.SoftReference;
import java.util.List;

@Component
public class SigninManager extends TCCBaseManager {

	@Inject
	private AccountService accountService;

	@Inject
	private ProfileSingleton profileSingleton;

	public Profile signin(final AccountView view) throws ValidationException {
		validate(view);

		Account entity = new Account();
		entity.setEmail(view.getEmail());
		entity.setPassword(view.getPassword());
		entity.setName(view.getNome());
		entity.setLastname(view.getSobrenome());
		entity.setExtraParams(new ExtraParamsBean());

		entity = super.save(entity);

		view.setPassword(null);
		view.setKey(entity.getChave());
		Profile profile = new ProfileBean();
		profile.setUsuario(entity);
		profile.addParam("cookie", CookieHelper.generateCookie(entity.getChave()));
		profileSingleton.add(profile);

		return profile;

	}

	private void validate(AccountView view) throws ValidationException {
		if (view == null) throw new ValidationException(new Message("error.save", MessageSeverity.ERROR));

		ValidationException ex = new ValidationException();
		if (view.getEmail() == null || !EmailValidator.validate(view.getEmail())) {
			ex.addMessage(new Message("message.warn.invalid", MessageSeverity.WARN, "Email"));
		}
		if (StringUtils.isEmpty(view.getNome()) || NumberUtils.isNumber(view.getNome())) {
			ex.addMessage(new Message("message.warn.invalid", MessageSeverity.WARN, "Nome"));
		}
		if (StringUtils.isEmpty(view.getSobrenome()) || NumberUtils.isNumber(view.getSobrenome())) {
			ex.addMessage(new Message("message.warn.invalid", MessageSeverity.WARN, "Sobrenome"));
		}
		if (StringUtils.isEmpty(view.getPassword()) || NumberUtils.isNumber(view.getPassword())) {
			ex.addMessage(new Message("message.warn.invalid", MessageSeverity.WARN, "Senha"));
		} else if (view.getPassword().length() < 5) {
			ex.addMessage(new Message("message.warn.minlenght", MessageSeverity.WARN, "Senha", "5"));
		}
		if (ex.haveMessages()) throw ex;

		//Ver se ja tem conta com este email cadastrado
		Account account = accountService.selectByEmail(view.getEmail());
		if (account != null) {
			ex.addMessage(new Message("message.warn.value.exist", MessageSeverity.WARN, "Email"));
			throw ex;
		}
	}

}
