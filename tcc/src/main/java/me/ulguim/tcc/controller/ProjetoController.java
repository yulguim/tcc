package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.entity.Projeto;
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
	public ProjetoDTO load(@PathVariable("key") String key) throws ValidationException {
		ProjetoView view = new ProjetoView();
		view.setKey(key);

		ProjetoDTO dto = new ProjetoDTO();
		dto.projeto = projetoManager.load(getProfile(), view);
		if (dto.projeto.getMeuProjeto() || dto.projeto.getSouParticipante()) {
			dto.participantes = projetoManager.loadParticipantes(getProfile(), view);
			dto.chat = projetoManager.loadChat(getProfile(), view);
		}

		return dto;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ProjetoView save(@RequestBody ProjetoView view) throws ValidationException {
		return projetoManager.save(getProfile(), view);
	}

	/** PARTICIPANTES **/

	@RequestMapping(value="/request", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ContatoView request(@RequestBody ContatoView view) throws ValidationException {
		return projetoManager.request(getProfile(), view);
	}

	@RequestMapping(value="/accept", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ContatoView acceptRequest(@RequestBody ContatoView view) throws ValidationException {
		return projetoManager.acceptRequest(getProfile(), view);
	}

	@RequestMapping(value="/delete-participante", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ContatoView removeRequest(@RequestBody ContatoView view) throws ValidationException {
		return projetoManager.deleteParticipante(getProfile(), view);
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

	public static class ProjetoDTO {

		public ProjetoView projeto;

		public List<ContatoView> participantes;

		public ChatView chat;

	}

}
