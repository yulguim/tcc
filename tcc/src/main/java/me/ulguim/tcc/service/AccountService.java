package me.ulguim.tcc.service;

import in.k2s.sdk.springboot.service.base.BaseService;
import me.ulguim.tcc.entity.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService {
	
	public Account selectByEmail(String email) {
		String QUERY = "SELECT obj FROM Account obj WHERE LOWER(obj.email) = ?1";
		return super.selectFirstByQuery(QUERY, email.toLowerCase());
	}

}
