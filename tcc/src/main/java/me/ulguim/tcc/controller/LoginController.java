package me.ulguim.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import me.ulguim.tcc.manager.LoginManager;
import me.ulguim.tcc.view.LoginView;

@RestController
public class LoginController {
	
	@Autowired LoginManager loginManager;
	
	public LoginView login(LoginView view) {
		loginManager.login(view);
		
		return null;
	}

}
