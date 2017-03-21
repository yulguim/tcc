package me.ulguim.tcc.controller;

import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.ContatosManager;
import me.ulguim.tcc.view.ContatoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerfilController extends TCCBaseController {

	@Autowired
	ContatosManager contatosManager;

	@RequestMapping(value="/{view.key}", method = RequestMethod.GET)
	public ContatoView load(ContatoView view) throws ValidationException {
		return contatosManager.load(getProfile(), view);
	}

}
