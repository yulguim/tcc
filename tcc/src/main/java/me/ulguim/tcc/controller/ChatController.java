package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.entity.converter.MensagemConverter;
import me.ulguim.tcc.manager.ChatManager;
import me.ulguim.tcc.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/chat", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ChatController extends TCCBaseController {

	@Autowired
	private ChatManager chatManager;

	@RequestMapping(method = RequestMethod.GET)
	public List<ChatView> list() throws ValidationException {
		return chatManager.list(getProfile());
	}

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ChatView load(@PathVariable("id") Long id) throws ValidationException {
		return chatManager.load(getProfile(), id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public MensagemView save(@RequestBody MensagemView view) throws ValidationException {
		return chatManager.saveMensagem(getProfile(), view);
	}

	@RequestMapping(value="/delete-mensagem", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public MensagemView deleteComentario(@RequestBody MensagemView view) throws ValidationException {
		return chatManager.deleteMensagem(getProfile(), view);
	}

}
