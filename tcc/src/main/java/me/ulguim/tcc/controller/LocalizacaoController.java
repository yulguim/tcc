package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.entity.location.Cidade;
import me.ulguim.tcc.manager.HomeManager;
import me.ulguim.tcc.manager.LocalizacaoManager;
import me.ulguim.tcc.view.FeedView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/localizacao", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LocalizacaoController extends TCCBaseController {

	@Autowired
	private LocalizacaoManager localizacaoManager;

	@RequestMapping(value="/cidade", method = RequestMethod.POST)
	public ReturnDTO searchCidade(@RequestBody String str) throws ValidationException {
		ReturnDTO returnDTO = new ReturnDTO();
		returnDTO.cidades = localizacaoManager.searchCidade(str);
		returnDTO.cidadesNome = new HashMap<String, String>();
		returnDTO.cidades.forEach(c -> {
			returnDTO.cidadesNome.put(c.getNome(), c.getNome());
		});

		return returnDTO;
	}

	public static class ReturnDTO {
		public List<Cidade> cidades;
		public Map<String, String> cidadesNome;
	}

}
