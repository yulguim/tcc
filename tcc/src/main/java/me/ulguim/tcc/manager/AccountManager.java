package me.ulguim.tcc.manager;

import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import in.k2s.sdk.util.validator.EmailValidator;
import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.message.MessageSeverity;
import in.k2s.sdk.web.message.MessageSuccess;
import in.k2s.sdk.web.message.MessageWarning;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.ArquivoBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.entity.other.Arquivo;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ContatoView;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@Component
public class AccountManager extends TCCBaseManager {

	@Value(value="${avatar.folder}")
	private String avatarFolder;

	@Inject
	private AccountService accountService;

	public AccountView load(Profile profile) {
		Account accountLogadaLoaded = getAccountLogadaLoaded(profile);

		AccountView view = new AccountView();
		view.setId(accountLogadaLoaded.getId());
		view.setEmail(accountLogadaLoaded.getEmail());
		view.setKey(accountLogadaLoaded.getChave());
		view.setAvatar(accountLogadaLoaded.getAvatar());
		view.setLabel(accountLogadaLoaded.getLabel());

		return view;
	}

	public ArquivoBean downloadAvatar(Profile profile, String key) {
		Arquivo arquivo = accountService.selectByChave(Arquivo.class, key);

		try {
			File file = new File(avatarFolder + "/" + arquivo.getNome());
			InputStream targetStream = new FileInputStream(file);

			ArquivoBean bean = new ArquivoBean();
			bean.setArquivo(targetStream);
			bean.setContentType(arquivo.getContentType());
			bean.setNome(arquivo.getNome());
			bean.setTamanho(arquivo.getTamanho());

			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public AccountView saveAvatar(Profile profile, ArquivoBean avatar) {
		Account accountLogadaLoaded = getAccountLogadaLoaded(profile);

		Arquivo arquivo = new Arquivo();
		arquivo.setCaminho("/");
		arquivo.setContentType(avatar.getContentType());
		arquivo.setNome(avatar.getNome());
		arquivo.setTamanho(avatar.getTamanho());
		arquivo.setTemporario(false);
		arquivo = super.save(arquivo, profile);

		saveArquivoNoDisco(new File(avatarFolder + "/" + arquivo.getNome()), avatar.getArquivo(), false);

		accountLogadaLoaded.setAvatar(arquivo.getChave());
		accountLogadaLoaded = super.update(accountLogadaLoaded, profile);

		profile.setUsuario(accountLogadaLoaded);
		super.getProfileSingleton().add(profile);

		AccountView view = new AccountView();
		return view;
	}

	public AccountView update(Profile profile, AccountView view) throws ValidationException {
		validate(profile, view);
		Account account = getAccountLogadaLoaded(profile);
		account.setEmail(view.getEmail());
		account.setChave(view.getKey());
		account.setUsername(view.getKey());
		if (!StringUtils.isEmpty(view.getPassword())) {
			account.setPassword(view.getPassword());
		}
		account = super.update(account, profile);

		Perfil perfil = account.getProfile();
		if (perfil != null) {
			perfil.setChave(view.getKey());
			super.update(perfil, profile);
		}

		profile.setUsuario(account);
		super.getProfileSingleton().add(profile);

		view.setPassword(null);
		view.addMessage(new MessageSuccess( "success.save"));
		return view;
	}

	private void validate(Profile profile, AccountView view) throws ValidationException {
		ValidationException ex = new ValidationException();
		if (view == null) {
			ex.addMessage(new MessageWarning("warn.save"));
			throw ex;
		}

		if (StringUtils.isEmpty(view.getKey())) {
			ex.addMessage(new Message("message.custom", MessageSeverity.WARN, "A chave é obrigatória."));
		} else {
			Account account = accountService.selectByChave(Account.class, view.getKey());
			if (account != null && !account.getId().equals(getAccountLogada(profile).getId())) {
				ex.addMessage(new Message("message.custom", MessageSeverity.WARN, "Esta chave não está disponível."));
			}
		}
		if (view.getEmail() == null || !EmailValidator.validate(view.getEmail())) {
			ex.addMessage(new Message("message.warn.invalid", MessageSeverity.WARN, "Email"));
		} else {
			Account account = accountService.selectByEmail(view.getEmail());
			if (account != null && !account.getId().equals(getAccountLogada(profile).getId())) {
				ex.addMessage(new Message("message.custom", MessageSeverity.WARN, "Este email já está cadastrado."));
			}
		}
		if (!StringUtils.isEmpty(view.getPassword()) && view.getPassword().length() < 5) {
			ex.addMessage(new Message("message.custom", MessageSeverity.WARN, "A senha deve conter pelo menos 5 caracteres."));
		}

		if (ex.haveMessages()) throw ex;
	}

	private File saveArquivoNoDisco(File file, InputStream inputStream, boolean image) {
		if (image) {
			//inputStream = imageResizeAndCompress(inputStream, this.maxWidth); // Resize and compress image.
		}
		OutputStream outputStream = null;
		try { //FileNotFoundException
			file.createNewFile();
			outputStream = new FileOutputStream(file);
			IOUtils.copy(inputStream, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(outputStream);
			IOUtils.closeQuietly(inputStream);
		}
		return file;
	}

}
