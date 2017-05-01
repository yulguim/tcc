package me.ulguim.tcc.manager.base;

import in.k2s.sdk.springboot.manager.base.BaseManager;
import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import in.k2s.sdk.web.profile.Profile;
import me.ulguim.tcc.bean.NotificationBean;
import me.ulguim.tcc.entity.Account;
import org.apache.commons.io.IOUtils;

import javax.inject.Inject;
import java.io.*;

/**
 * Created by yulle on 17/01/17.
 */
public abstract class TCCBaseManager extends BaseManager {

	@Inject
	private ProfileSingleton profileSingleton;

	public ProfileSingleton getProfileSingleton() {
		return profileSingleton;
	}

	private Account getUsuario(Profile profile) {
		return (Account) profile.getUsuario();
	}

	public Account getAccountLogada(Profile profile) {
		return super.load(Account.class, getUsuario(profile).getId());
	}

	public Account getAccountLogadaLoaded(Profile profile) {
		return super.load(Account.class, getUsuario(profile).getId());
	}

	/**
	 * Salva um arquivo no disco
	 * @param file
	 * @param inputStream
	 * @param image
	 * @return
	 */
	public File saveArquivoNoDisco(File file, InputStream inputStream, boolean image) {
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
