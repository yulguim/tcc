package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.ContatosManager;
import me.ulguim.tcc.manager.HomeManager;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.FeedView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/home", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class HomeController extends TCCBaseController {

	@Autowired
	private HomeManager homeManager;

	@RequestMapping(value="/feed", method = RequestMethod.GET)
	public FeedView feed() throws ValidationException {
		return homeManager.listFeed(getProfile());
	}

}
