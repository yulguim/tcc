package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.ChatManager;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ChatView;
import me.ulguim.tcc.view.ContatoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

//	@RequestMapping(value="/request/{key}", method = RequestMethod.GET)
//	public ContatoView request(@PathVariable("key") String key) throws ValidationException {
//		return chatManager.request(getProfile(), new ContatoView(key));
//	}
//
//	@RequestMapping(value="/accept/{key}", method = RequestMethod.GET)
//	public ContatoView acceptRequest(@PathVariable("key") String key) throws ValidationException {
//		return chatManager.acceptRequest(getProfile(), new ContatoView(key));
//	}
//
//	@RequestMapping(value="/ignore/{key}", method = RequestMethod.GET)
//	public ContatoView ignoreRequest(@PathVariable("key") String key) throws ValidationException {
//		return chatManager.ignoreRequest(getProfile(), new ContatoView(key));
//	}
//
//	@RequestMapping(value="/cancel/{key}", method = RequestMethod.GET)
//	public ContatoView cancelRequest(@PathVariable("key") String key) throws ValidationException {
//		return chatManager.cancelRequest(getProfile(), new ContatoView(key));
//	}
//
//	@RequestMapping(value="/{key}", method = RequestMethod.DELETE)
//	public ContatoView unfriend(@PathVariable("key") String key) throws ValidationException {
//		return chatManager.remove(getProfile(), new ContatoView(key));
//	}

}
