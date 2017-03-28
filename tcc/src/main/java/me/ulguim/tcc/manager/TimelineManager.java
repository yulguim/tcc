package me.ulguim.tcc.manager;

import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.entity.Post;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.parser.PostParser;
import me.ulguim.tcc.service.PostService;
import me.ulguim.tcc.view.FeedView;
import me.ulguim.tcc.view.PostView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimelineManager extends TCCBaseManager {

	@Inject
	private PostService postService;

	public List<PostView> listFeed(Profile profile) throws ValidationException {
		List<Post> postList = postService.selectAllPostsByAccount(getAccountLogada(profile).getId());

		List<PostView> list = new ArrayList<>();
		postList.forEach(p -> {
			list.add(PostParser.parse(p));
		});

		return list;
	}

}
