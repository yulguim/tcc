package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.HomeManager;
import me.ulguim.tcc.manager.TimelineManager;
import me.ulguim.tcc.view.FeedView;
import me.ulguim.tcc.view.PostView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/timeline", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TimelineController extends TCCBaseController {

	@Autowired
	private TimelineManager timelineManager;

	@RequestMapping(value="/initial-data", method = RequestMethod.GET)
	public FeedDTO feed() throws ValidationException {
		FeedDTO dto = new FeedDTO();
		dto.posts = timelineManager.listFeed(getProfile());

		return dto;
	}

	public static class FeedDTO {
		public List<PostView> posts;
	}

}
