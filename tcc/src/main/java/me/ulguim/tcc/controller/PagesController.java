package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity.Security;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import in.k2s.sdk.springboot.resource.ResourceList;
import in.k2s.sdk.web.validation.ValidationException;

@Controller
@ControllerSecurity(Security.PUBLIC)
public class PagesController {

	@RequestMapping("/")
    public String app(Model model) {
		model.addAttribute("cssList", ResourceList.getCSS(null, "node_modules"));
		model.addAttribute("jsList", ResourceList.getJS(null, "node_modules"));
		return "app";
    }
	
	/** INCLUDES **/
	
	@RequestMapping("/head.html")
    public String head() {
		return "include/head";
    }
	
	@RequestMapping("/footer.html")
    public String footer() {
		return "include/footer";
    }
	
	@RequestMapping("/nav.html")
    public String nav() {
		return "include/nav";
    }
	
	/** PARTIALS **/
	
	@RequestMapping("/home.html")
	public String home() throws ValidationException {
		return "partials/home";
	}
	
	@RequestMapping("/contatos.html")
	public String contatos() throws ValidationException {
		return "partials/contatos";
	}

	@RequestMapping("/my-account.html")
	public String myAccount() throws ValidationException {
		return "partials/my-account";
	}
	
	@RequestMapping("/profile.html")
	public String profile() throws ValidationException {
		return "partials/profile";
	}

	@RequestMapping("/profile-edit.html")
	public String profileEdit() throws ValidationException {
		return "partials/profile-edit";
	}
	
	@RequestMapping("/mensagens.html")
	public String mensagens() throws ValidationException {
		return "partials/mensagens";
	}

	@RequestMapping("/search.html")
	public String search() throws ValidationException {
		return "partials/search";
	}

	@RequestMapping("/projeto.html")
	public String projeto() throws ValidationException {
		return "partials/projeto";
	}

	@RequestMapping("/my-projects.html")
	public String meusProjetos() throws ValidationException {
		return "partials/my-projects";
	}

	@RequestMapping("/new-project.html")
	public String novoProjeto() throws ValidationException {
		return "partials/new-project";
	}

}
