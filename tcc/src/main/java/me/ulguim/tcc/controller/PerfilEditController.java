package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import in.k2s.sdk.web.view.ContainerView;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.ContatosManager;
import me.ulguim.tcc.manager.PerfilManager;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.PerfilView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/profile-edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PerfilEditController extends TCCBaseController {

	@Autowired
	private PerfilManager perfilManager;

	@RequestMapping(value="/initial-data", method = RequestMethod.GET)
	public ContainerView initialData() throws ValidationException {
		ContainerView view = new ContainerView();
		view.addFormData("meuPerfil", perfilManager.meuPerfil(getProfile()));

		return view;
	}

	@RequestMapping(method = RequestMethod.GET)
	public PerfilView myProfile() throws ValidationException {
		return perfilManager.meuPerfil(getProfile());
	}

}
