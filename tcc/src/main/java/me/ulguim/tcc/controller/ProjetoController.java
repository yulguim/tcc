package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.PerfilManager;
import me.ulguim.tcc.manager.ProjetoManager;
import me.ulguim.tcc.view.*;
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
	public List<ProjetoSimpleView> listMyProjects() throws ValidationException {
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

	/** PARTICIPANTES **/

	@RequestMapping(value="/request/{key}", method = RequestMethod.GET)
	public ContatoView request(@PathVariable("key") String key) throws ValidationException {
		return projetoManager.request(getProfile(), new ContatoView(key));
	}

	@RequestMapping(value="/accept/{key}", method = RequestMethod.GET)
	public ContatoView acceptRequest(@PathVariable("key") String key) throws ValidationException {
		return projetoManager.acceptRequest(getProfile(), new ContatoView(key));
	}

	@RequestMapping(value="/delete-participante/{key}", method = RequestMethod.GET)
	public ContatoView removeRequest(@PathVariable("key") String key) throws ValidationException {
		return projetoManager.deleteParticipante(getProfile(), new ContatoView(key));
	}

	/** MENSAGENS **/

	@RequestMapping(value="/save-mensagem", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public MensagemView save(@RequestBody MensagemView view) throws ValidationException {
		return projetoManager.saveMensagem(getProfile(), view);
	}

	@RequestMapping(value="/delete-mensagem", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public MensagemView deleteMensagem(@RequestBody MensagemView view) throws ValidationException {
		return projetoManager.deleteMensagem(getProfile(), view);
	}

}
