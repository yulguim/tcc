package me.ulguim.tcc.controller.other;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.ContatosManager;
import me.ulguim.tcc.view.ContatoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/batchService",
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class BatchController extends TCCBaseController {

	@Autowired
	private ContatosManager contatosManager;

	@RequestMapping(value="/request/{view.key}", method = RequestMethod.GET)
	public ContatoView request(ContatoView view) throws ValidationException {
		return contatosManager.request(getProfile(), view);
	}

	@RequestMapping(value="/accept/{view.key}", method = RequestMethod.GET)
	public ContatoView acceptRequest(ContatoView view) throws ValidationException {
		return contatosManager.acceptRequest(getProfile(), view);
	}

	@RequestMapping(value="/ignore/{view.key}", method = RequestMethod.GET)
	public ContatoView ignoreRequest(ContatoView view) throws ValidationException {
		return contatosManager.ignoreRequest(getProfile(), view);
	}

	@RequestMapping(value="/{view.key}", method = RequestMethod.DELETE)
	public ContatoView unfriend(ContatoView view) throws ValidationException {
		return contatosManager.load(getProfile(), view);
	}

}
