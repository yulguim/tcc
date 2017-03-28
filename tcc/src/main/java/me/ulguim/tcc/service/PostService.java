package me.ulguim.tcc.service;

import in.k2s.sdk.springboot.service.base.BaseService;
import me.ulguim.tcc.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService extends BaseService {

	public List<Post> selectAllPostsByAccount(Long accountId) {
		String QUERY = "SELECT obj FROM Post obj WHERE obj.author.id = ?1 ORDER BY obj.insertTime DESC";
		return super.selectByQuery(QUERY, accountId);
	}

}
