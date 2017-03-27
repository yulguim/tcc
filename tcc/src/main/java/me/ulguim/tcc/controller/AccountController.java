package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.ArquivoBean;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.entity.other.Arquivo;
import me.ulguim.tcc.manager.AccountManager;
import me.ulguim.tcc.manager.ContatosManager;
import me.ulguim.tcc.view.AccountView;
import me.ulguim.tcc.view.ContatoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController extends TCCBaseController {

	@Autowired
	private AccountManager accountManager;

	@RequestMapping(method = RequestMethod.GET)
	public AccountView load() throws ValidationException {
		return accountManager.load(getProfile());
	}

	@RequestMapping(value = "/save-avatar", method = RequestMethod.POST)
	public AccountView uploadFile(@RequestParam(value="file", required=true) MultipartFile file) throws ValidationException, IOException {
		ArquivoBean bean = new ArquivoBean();
		bean.setNome(file.getOriginalFilename());
		bean.setContentType(file.getContentType());
		bean.setArquivo(file.getInputStream());
		return accountManager.saveAvatar(getProfile(), bean);
	}

}
