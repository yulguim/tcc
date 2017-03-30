package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.ContatosManager;
import me.ulguim.tcc.manager.PerfilManager;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.PerfilView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sun.misc.Perf;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/profile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PerfilController extends TCCBaseController {

	@Autowired
	private PerfilManager perfilManager;

	@RequestMapping(value="/{key}", method = RequestMethod.GET)
	public PerfilView load(@PathVariable("key") String key) throws ValidationException {
		System.out.println("key = " + key);
		PerfilView view = new PerfilView();
		view.setKey(key);
		return perfilManager.load(getProfile(), view);
	}

}
