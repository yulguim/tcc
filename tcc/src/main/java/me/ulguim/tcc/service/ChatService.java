package me.ulguim.tcc.service;

import in.k2s.sdk.springboot.service.base.BaseService;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.Chat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService extends BaseService {
	
	public List<Chat> selectAllChatByAccountId(Long id) {
		String QUERY = "SELECT obj FROM Chat obj WHERE obj.user1 = ?1 OR obj.user2 = ?1";
		return super.selectByQuery(QUERY, id);
	}

	public Chat selectChatByIdAccountId(Long id, Long accountId) {
		String QUERY = "SELECT obj FROM Chat obj WHERE obj.id = ?1 AND (obj.user1 = ?2 OR obj.user2 = ?2)";
		return super.selectFirstByQuery(QUERY, id, accountId);
	}

}
