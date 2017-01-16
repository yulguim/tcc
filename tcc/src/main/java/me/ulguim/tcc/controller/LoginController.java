package me.ulguim.tcc.controller;

import javax.servlet.http.HttpServletResponse;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.manager.LoginManager;
import me.ulguim.tcc.view.LoginView;
import me.ulguim.tcc.view.ProfileView;

@Controller
@ControllerSecurity(ControllerSecurity.Security.PUBLIC)
public class LoginController {
	
	@Autowired LoginManager loginManager;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody ProfileView login(@RequestBody LoginView view, HttpServletResponse response) throws ValidationException {		
		Profile profile = loginManager.login(view);
		//response.addCookie(createCookie(profile));
		
		Account account = profile.getUsuario();
		ProfileView profileView = new ProfileView();
		profileView.setAvatar(account.getAvatar());
		profileView.setUsername(account.getUsername());
		profileView.setName(account.getName());

		return profileView;
	}

	@RequestMapping(value="/logoff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody ProfileView logoff(@RequestBody LoginView view, HttpServletResponse response) throws ValidationException {

		ProfileView profileView = new ProfileView();
		return profileView;
	}

}
