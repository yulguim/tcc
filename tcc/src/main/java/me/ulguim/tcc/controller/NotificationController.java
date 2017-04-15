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

	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public NotificationDTO read(@RequestBody NotificationDTO dto) throws ValidationException {
		NotificationDTO returnDTO = new NotificationDTO();
		returnDTO.result = notificationManager.read(getProfile(), dto.ids);

		return returnDTO;
	}

	public static class NotificationDTO {
		public Boolean result;
		public List<Long> ids;
	}



}
