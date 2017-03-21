package me.ulguim.tcc.service;

import in.k2s.sdk.springboot.service.base.BaseService;
import me.ulguim.tcc.entity.Perfil;
import org.springframework.stereotype.Service;

@Service
public class PerfilService extends BaseService {

	public Perfil selectPerfilByAccountId(Long accountId) {
		String QUERY = "SELECT obj FROM Perfil obj WHERE obj.account.id = ?1";
		return super.selectFirstByQuery(QUERY, accountId);
	}

}
