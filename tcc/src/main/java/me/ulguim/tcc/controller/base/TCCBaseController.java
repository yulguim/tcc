package me.ulguim.tcc.controller.base;

import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import in.k2s.sdk.web.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yulle on 17/01/17.
 */
public abstract class TCCBaseController {

	/**
	 * Definido no application.properties
	 */
	@Value("${cookie.name}") String cookieName;

	@Autowired(required=true)
	private HttpServletRequest request;

	@Autowired
	ProfileSingleton profileSingleton;

	public Profile getProfile() {
		String cookie = getCookie();
		if (cookie == null) return null;

		Profile profile = profileSingleton.get(cookie);
		return profile;
	}

	private String getCookie() {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookieName.equals(cookie.getName())) return cookie.getValue();
			}
		}
		return null;
	}

	@ModelAttribute("profile")
	public Profile profile() {
		Profile profile = getProfile();
		return profile;
	}

}
