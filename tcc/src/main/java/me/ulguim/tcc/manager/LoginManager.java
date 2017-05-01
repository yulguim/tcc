package me.ulguim.tcc.manager;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.json.JsonObject;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.User;
import in.k2s.sdk.jpa.sequence.SequenceGenerator;
import in.k2s.sdk.web.message.MessageError;
import me.ulguim.tcc.bean.ExtraParamsBean;
import me.ulguim.tcc.entity.other.Arquivo;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.service.AccountService;
import me.ulguim.tcc.util.CookieHelper;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import in.k2s.sdk.web.message.Message;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.profile.ProfileBean;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.service.LoginService;
import me.ulguim.tcc.view.LoginView;

import java.io.File;
import java.io.InputStream;

@Component
public class LoginManager extends TCCBaseManager {

	@Value(value="${facebook.app.secret}")
	private String facebookSecret;

	@Value(value="${avatar.folder}")
	private String avatarFolder;
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private AccountService accountService;
	
	public Profile login(LoginView view) throws ValidationException {
		if (view == null || view.getEmail() == null || view.getPassword() == null) {
			throw new ValidationException(new Message("message.warn.auth"));
		}
		
		Account entity = loginService.selectByEmailAndPassword(view.getEmail(), view.getPassword());
		if (entity == null) {
			throw new ValidationException(new Message("message.warn.auth"));
		}

		Profile profile = new ProfileBean();
		profile.setUsuario(entity);
		profile.addParam("cookie", CookieHelper.generateCookie(String.valueOf(entity.getChave())));

		return profile;
	}

	public Profile facebookLogin(String facebookToken) throws ValidationException {
		FacebookClient facebookClient = new DefaultFacebookClient(facebookToken, facebookSecret, Version.VERSION_2_8);
		User fbUser = null;
		try {
			fbUser = facebookClient.fetchObject("me",
					User.class, Parameter.with("fields","name,id,email,about,location,work"));
		} catch (FacebookOAuthException e) {
			e.printStackTrace();
			throw new ValidationException(new MessageError("warn.load"));
		} catch (Throwable t) {
			t.printStackTrace();
			throw new ValidationException(new MessageError("warn.load"));
		}

		if (fbUser == null || fbUser.getId() == null || fbUser.getEmail() == null) {
			throw new ValidationException(new MessageError("warn.load"));
		}

		String fbUserId = fbUser.getId();
		Account account = loginService.selectByFacebookId(fbUserId);

		//Nao existe conta vinculada com id
		if (account == null) {
			account = accountService.selectByEmail(fbUser.getEmail());
			if (account != null) {
				//Existe conta com email do facebook, soh vincula
				account.setFacebookId(fbUserId);
				account.setFacebookToken(facebookToken);
				account = super.update(account);
			} else {
				//Criar conta nova
				account = new Account();
				account.setEmail(fbUser.getEmail());
				account.setPassword(SequenceGenerator.generateUUID());
				account.setName(fbUser.getFirstName());
				account.setLastname(fbUser.getLastName());
				account.setExtraParams(new ExtraParamsBean());
				account = super.save(account);
			}
		} else {
			account.setFacebookToken(facebookToken);
			account = super.update(account);
		}

		JsonObject js =
				facebookClient.fetchObject("/me/picture", JsonObject.class,
						Parameter.with("type","large"),
						Parameter.with("redirect","false"));
		String url = js.getJsonObject("data").getString("url");
		if (url != null) {
			try {
				HttpClient client = HttpClients.custom().build();
				HttpGet httpget = new HttpGet(url);
				HttpResponse response = client.execute(httpget);
				InputStream inputStream = response.getEntity().getContent();

				Arquivo arquivo = new Arquivo();
				arquivo.setCaminho("/");
				arquivo.setContentType(null);
				arquivo.setNome(SequenceGenerator.generateUUID());
				arquivo.setTamanho(new Long(inputStream.available()));
				arquivo.setTemporario(false);
				arquivo = super.save(arquivo);

				saveArquivoNoDisco(new File(avatarFolder + "/" + arquivo.getNome()), inputStream, false);

				account.setAvatar(arquivo.getChave());
				account = super.update(account);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Profile profile = new ProfileBean();
		profile.setUsuario(account);
		profile.addParam("cookie", CookieHelper.generateCookie(String.valueOf(account.getChave())));

		return profile;
	}

}
