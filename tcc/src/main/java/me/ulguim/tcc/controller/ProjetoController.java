package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.PerfilManager;
import me.ulguim.tcc.manager.ProjetoManager;
import me.ulguim.tcc.view.PerfilView;
import me.ulguim.tcc.view.ProjetoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/projeto", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProjetoController extends TCCBaseController {

	@Autowired
	private ProjetoManager projetoManager;

	@RequestMapping(method = RequestMethod.GET)
	public List<ProjetoView> listMyProjects() throws ValidationException {
		return projetoManager.list(getProfile());
	}

	@RequestMapping(value="/{key}", method = RequestMethod.GET)
	public ProjetoView load(@PathVariable("key") String key) throws ValidationException {
		ProjetoView view = new ProjetoView();
		view.setKey(key);
		return projetoManager.load(getProfile(), view);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ProjetoView save(@RequestBody ProjetoView view) throws ValidationException {
		return projetoManager.save(getProfile(), view);
	}

}
