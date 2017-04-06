package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.ContatosManager;
import me.ulguim.tcc.manager.SearchManager;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.PerfilView;
import me.ulguim.tcc.view.other.SearchView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SearchController extends TCCBaseController {

	@Autowired
	private SearchManager searchManager;

	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public SearchView search(@RequestBody SearchView view) throws ValidationException {
		return searchManager.search(getProfile(), view);
	}

}
