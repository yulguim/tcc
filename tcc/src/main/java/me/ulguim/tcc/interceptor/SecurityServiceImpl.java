package me.ulguim.tcc.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.k2s.sdk.springboot.interceptor.base.SecurityService;
import in.k2s.sdk.springboot.singleton.ProfileSingleton;
import in.k2s.sdk.web.profile.Profile;

@Service
public class SecurityServiceImpl implements SecurityService {	
	
	@Autowired
	private ProfileSingleton profileSingleton;

	@Override
	public boolean validate(String token) {
		return profileSingleton.get(token) != null;
	}

	@Override
	public Profile load(String token) {
		return profileSingleton.get(token);
	}

}