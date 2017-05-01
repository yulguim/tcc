package me.ulguim.tcc.service;

import org.springframework.stereotype.Service;

import in.k2s.sdk.springboot.service.base.BaseService;
import me.ulguim.tcc.entity.Account;

@Service
public class LoginService extends BaseService {
	
	public Account selectByEmailAndPassword(String email, String password) {
		String QUERY = "SELECT obj FROM Account obj WHERE LOWER(obj.email) = ?1 AND obj.password = ?2";
		return super.selectFirstByQuery(QUERY, email.toLowerCase(), password);
	}

	public Account selectByFacebookId(String id) {
		String QUERY = "SELECT obj FROM Account obj WHERE obj.facebookId = ?1";
		return super.selectFirstByQuery(QUERY, id);
	}

	public Account selectByLinkedinId(String id) {
		String QUERY = "SELECT obj FROM Account obj WHERE obj.linkedinId = ?1";
		return super.selectFirstByQuery(QUERY, id);
	}

}
