package me.ulguim.tcc.service;

import in.k2s.sdk.springboot.service.base.BaseService;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.other.Habilidade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadeService extends BaseService {
	
	public List<Habilidade> selectAll() {
		String QUERY = "SELECT obj FROM Habilidade obj ORDER BY LOWER(obj.label) ASC";
		return super.selectByQuery(QUERY);
	}

}
