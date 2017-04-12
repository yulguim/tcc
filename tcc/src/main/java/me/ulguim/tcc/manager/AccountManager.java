package me.ulguim.tcc.manager;

import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.ArquivoBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.other.Arquivo;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ContatoView;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
		account.setName(view.getNome());
		account.setLastname(view.getSobrenome());
		account.setEmail(view.getEmail());
		account.setPassword(view.getPassword());

		account.setChave(view.getKey());
		account.setUsername(view.getKey());

		account = super.update(account, profile);

		profile.setUsuario(account);
		super.getProfileSingleton().add(profile);
		return view;
	}

	private void validate(Profile profile, AccountView view) throws ValidationException {

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
