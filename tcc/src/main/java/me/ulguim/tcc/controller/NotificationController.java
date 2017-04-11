package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.NotificationBean;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.ChatManager;
import me.ulguim.tcc.manager.NotificationManager;
import me.ulguim.tcc.view.ChatView;
import me.ulguim.tcc.view.MensagemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/notifications", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class NotificationController extends TCCBaseController {

	@Autowired
	private NotificationManager notificationManager;

	@RequestMapping(method = RequestMethod.GET)
	public List<NotificationBean> list() throws ValidationException {
		return notificationManager.list(getProfile());
	}

//	@RequestMapping(value="/{id}", method = RequestMethod.GET)
//	public ChatView load(@PathVariable("id") Long id) throws ValidationException {
//		return chatManager.load(getProfile(), id);
//	}
//
//	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//	public MensagemView save(@RequestBody MensagemView view) throws ValidationException {
//		return chatManager.saveMensagem(getProfile(), view);
//	}
//
//	@RequestMapping(value="/delete-mensagem", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//	public MensagemView deleteComentario(@RequestBody MensagemView view) throws ValidationException {
//		return chatManager.deleteMensagem(getProfile(), view);
//	}

}
