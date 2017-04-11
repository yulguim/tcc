package me.ulguim.tcc.manager;

import in.k2s.sdk.web.profile.Profile;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.NotificationBean;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Post;
import me.ulguim.tcc.manager.base.TCCBaseManager;
import me.ulguim.tcc.parser.PostParser;
import me.ulguim.tcc.service.PostService;
import me.ulguim.tcc.view.PostView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationManager extends TCCBaseManager {

	public List<NotificationBean> list(Profile profile) throws ValidationException {
		Account accountLogadaLoaded = getAccountLogadaLoaded(profile);
		List<NotificationBean> notifications = accountLogadaLoaded.getNotifications(10);

		System.out.println("notifications.size() = " + notifications.size()); //TODO Remover

		return notifications;
	}

}
