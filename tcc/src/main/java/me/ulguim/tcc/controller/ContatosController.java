package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.ContatosManager;
import me.ulguim.tcc.view.ContatoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/contato", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ContatosController extends TCCBaseController {

	@Autowired
	private ContatosManager contatosManager;

	@RequestMapping(method = RequestMethod.GET)
	public List<ContatoView> list() throws ValidationException {
		return contatosManager.list(getProfile());
	}

	@RequestMapping(value="/request/{key}", method = RequestMethod.GET)
	public ContatoView request(@PathVariable("key") String key) throws ValidationException {
		return contatosManager.request(getProfile(), new ContatoView(key));
	}

	@RequestMapping(value="/accept/{key}", method = RequestMethod.GET)
	public ContatoView acceptRequest(@PathVariable("key") String key) throws ValidationException {
		return contatosManager.acceptRequest(getProfile(), new ContatoView(key));
	}

	@RequestMapping(value="/ignore/{key}", method = RequestMethod.GET)
	public ContatoView ignoreRequest(@PathVariable("key") String key) throws ValidationException {
		return contatosManager.ignoreRequest(getProfile(), new ContatoView(key));
	}

	@RequestMapping(value="/cancel/{key}", method = RequestMethod.GET)
	public ContatoView cancelRequest(@PathVariable("key") String key) throws ValidationException {
		return contatosManager.cancelRequest(getProfile(), new ContatoView(key));
	}

	@RequestMapping(value="/{key}", method = RequestMethod.DELETE)
	public ContatoView unfriend(@PathVariable("key") String key) throws ValidationException {
		return contatosManager.remove(getProfile(), new ContatoView(key));
	}

}
