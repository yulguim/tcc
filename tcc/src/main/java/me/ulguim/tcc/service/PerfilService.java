package me.ulguim.tcc.service;

import in.k2s.sdk.springboot.service.base.BaseService;
import me.ulguim.tcc.entity.Perfil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService extends BaseService {

	public List<Perfil> selectPerfilBySeach(String search) {
		String QUERY = "SELECT obj FROM Perfil obj WHERE LOWER(obj.account.name) = ?1 OR LOWER(obj.account.lastname) = ?1"; //TODO Adicionar email, username
		return super.selectByQuery(QUERY, search.toLowerCase());
	}

	public Perfil selectPerfilByAccountId(Long accountId) {
		String QUERY = "SELECT obj FROM Perfil obj WHERE obj.account.id = ?1";
		return super.selectFirstByQuery(QUERY, accountId);
	}

}
