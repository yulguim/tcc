package me.ulguim.tcc.service;

import in.k2s.sdk.springboot.service.base.BaseService;
import me.ulguim.tcc.entity.Perfil;
import me.ulguim.tcc.entity.Projeto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService extends BaseService {

	public List<Projeto> selectAllByAccountId(Long id) {
		String QUERY = "SELECT obj FROM Projeto obj WHERE obj.owner.id = ?1";
		return super.selectByQuery(QUERY, id);
	}

	public List<Projeto> selectProjetoBySeach(String search) {
		search = search.toLowerCase();
		String QUERY = "SELECT obj FROM Projeto obj WHERE " +
				"LOWER(obj.titulo) LIKE '%" + search + "%' OR " +
				"LOWER(obj.descricao) LIKE '%" + search + "%'";
		return super.selectByQuery(QUERY);
	}

}
