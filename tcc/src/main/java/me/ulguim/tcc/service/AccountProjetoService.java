package me.ulguim.tcc.service;

import in.k2s.sdk.springboot.service.base.BaseService;
import me.ulguim.tcc.entity.AccountProjeto;
import me.ulguim.tcc.entity.Projeto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountProjetoService extends BaseService {

	public AccountProjeto selectByProjetoIdAndAccountId(Long projetoId, Long accountId) {
		String QUERY = "SELECT obj FROM AccountProjeto obj WHERE obj.projeto.id = ?1 AND obj.account.id = ?2";
		return super.selectFirstByQuery(QUERY, projetoId, accountId);
	}

}
