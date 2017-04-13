package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.PerfilManager;
import me.ulguim.tcc.view.PerfilView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/projeto", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProjetoController extends TCCBaseController {

	@Autowired
	private PerfilManager perfilManager;

	//TODO
	@RequestMapping(value="/{key}", method = RequestMethod.GET)
	public PerfilView load(@PathVariable("key") String key) throws ValidationException {
		PerfilView view = new PerfilView();
		view.setKey(key);
		return perfilManager.load(getProfile(), view);
	}

}
