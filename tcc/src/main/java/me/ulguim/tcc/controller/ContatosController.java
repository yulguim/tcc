package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Contato;
import me.ulguim.tcc.manager.ContatosManager;
import me.ulguim.tcc.manager.LoginManager;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.LoginView;
import me.ulguim.tcc.view.ProfileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/contatos",
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class ContatosController extends TCCBaseController {

	@Autowired
	ContatosManager contatosManager;

	@RequestMapping(method = RequestMethod.GET)
	public List<ContatoView> list() throws ValidationException {
		return contatosManager.list(getProfile());
	}

	@RequestMapping(value="/{view.key}", method = RequestMethod.GET)
	public ContatoView load(ContatoView view) throws ValidationException {
		return contatosManager.load(getProfile(), view);
	}

}
