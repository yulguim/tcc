package me.ulguim.tcc.service;

import in.k2s.sdk.springboot.service.base.BaseService;
import me.ulguim.tcc.entity.Account;
import me.ulguim.tcc.entity.location.Cidade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService extends BaseService {
	
	public List<Cidade> selectCidadeByStr(String str) {
		String QUERY = "SELECT obj FROM Cidade obj WHERE LOWER(obj.nome) LIKE '" + str.toLowerCase() + "%'";
		return super.selectByQuery(QUERY);
	}

}
