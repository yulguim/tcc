package me.ulguim.tcc.service;

import in.k2s.sdk.springboot.service.base.BaseService;
import me.ulguim.tcc.entity.Ocupacao;
import me.ulguim.tcc.entity.other.Habilidade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcupacaoService extends BaseService {
	
	public List<Ocupacao> selectAll() {
		String QUERY = "SELECT obj FROM Ocupacao obj ORDER BY LOWER(obj.label) ASC";
		return super.selectByQuery(QUERY);
	}

}
