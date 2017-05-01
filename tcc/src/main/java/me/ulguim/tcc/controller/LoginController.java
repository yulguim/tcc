package me.ulguim.tcc.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.SigninManager;
import me.ulguim.tcc.view.AccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.manager.LoginManager;
import me.ulguim.tcc.view.LoginView;
import me.ulguim.tcc.view.PerfilView;

@Controller
@ControllerSecurity(ControllerSecurity.Security.PUBLIC)
public class LoginController extends TCCBaseController {

	@Value(value="${cookie.name}")
	private String cookieName;
	@Value(value="${cookie.domain}")
	private String cookieDomain;

	@Autowired
	private LoginManager loginManager;
	@Autowired
	private SigninManager signinManager;
	@Autowired
	private ProfileSingleton profileSingleton;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody PerfilView login(@RequestBody LoginView view, HttpServletResponse response) throws ValidationException {
		Profile profile = loginManager.login(view);
		response.addCookie(createCookie(profile));
		
		Account account = profile.getUsuario();
		PerfilView perfilView = new PerfilView();
		perfilView.setAvatar(account.getAvatar());
		perfilView.setUsername(account.getUsername());
		perfilView.setName(account.getName());
		if (account.getProfile() == null) {
			perfilView.setHasNoProfile(true);
		}

		profileSingleton.add(profile);
		return perfilView;
	}

	@RequestMapping(value="/facebook-login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody PerfilView facebookLogin(@RequestBody LoginView view, HttpServletResponse response) throws ValidationException {
		Profile profile = loginManager.facebookLogin(view.getFacebookToken());
		response.addCookie(createCookie(profile));

		Account account = profile.getUsuario();
		PerfilView perfilView = new PerfilView();
		perfilView.setAvatar(account.getAvatar());
		perfilView.setUsername(account.getUsername());
		perfilView.setName(account.getName());
		if (account.getProfile() == null) {
			perfilView.setHasNoProfile(true);
		}

		profileSingleton.add(profile);
		return perfilView;
	}

	@RequestMapping(value="/logoff", method = RequestMethod.GET)
	public String logoff(HttpServletRequest request, HttpServletResponse response) throws ValidationException {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookieName.equals(cookie.getName()) && profileSingleton.get(cookie.getValue()) != null) profileSingleton.remove(cookie.getValue());
			}
		}
		Cookie cookie = new Cookie(cookieName, "INVALID");
		cookie.setMaxAge(0);
		cookie.setDomain(cookieDomain);
		cookie.setPath("/");
		response.addCookie(cookie);

		return "redirect:login";
	}

	/**
	 * REGISTRAR CONTA
	 */

	@RequestMapping(value="/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody
	PerfilView signin(@RequestBody AccountView view, HttpServletResponse response) throws ValidationException {
		Profile profile = signinManager.signin(view);
		response.addCookie(createCookie(profile));

		Account account = profile.getUsuario();
		PerfilView perfilView = new PerfilView();
		perfilView.setAvatar(account.getAvatar());
		perfilView.setUsername(account.getUsername());
		perfilView.setName(account.getName());

		profileSingleton.add(profile);
		return perfilView;
	}

	/**
	 * PRIVATE
	 */

	private Cookie createCookie(Profile profile) {
		Cookie cookie = new Cookie(cookieName, (String) profile.getParam("cookie"));
		cookie.setMaxAge(60 * 60 * 24); //Um dia
		cookie.setDomain(cookieDomain);
		cookie.setPath("/");

		return cookie;
	}

}
