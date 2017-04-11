package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.bean.ArquivoBean;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.AccountManager;
import me.ulguim.tcc.view.AccountView;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

	@ResponseBody
	@RequestMapping(value = "/avatar/{key}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getAvatar(@PathVariable String key) throws IOException {
		ArquivoBean avatar = accountManager.downloadAvatar(getProfile(), key);
		return IOUtils.toByteArray(avatar.getArquivo());
	}

	@RequestMapping(value = "/save-avatar", method = RequestMethod.POST)
	public AccountView uploadFile(@RequestParam(value="file", required=true) MultipartFile file) throws ValidationException, IOException {
		ArquivoBean bean = new ArquivoBean();
		bean.setNome(file.getOriginalFilename());
		bean.setContentType(file.getContentType());
		bean.setArquivo(file.getInputStream());
		bean.setTamanho(file.getSize());
		return accountManager.saveAvatar(getProfile(), bean);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public AccountView update(@RequestBody AccountView view) throws ValidationException {
		return accountManager.update(getProfile(), view);
	}

}
