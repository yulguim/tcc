package me.ulguim.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import in.k2s.sdk.springboot.resource.ResourceList;
import in.k2s.sdk.web.validation.ValidationException;

@Controller
public class PagesController {
	
	@RequestMapping("/login")
    public String login() {
		//model.addAttribute("submenuModulosClasses", "");
		return "login";
    }

	@RequestMapping("/")
    public String app(Model model) {
		model.addAttribute("cssList", ResourceList.getCSS());
		model.addAttribute("jsList", ResourceList.getJS());
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
	
	/** PARTIALS **/
	
	@RequestMapping("/home.html")
	public String home() throws ValidationException {
		return "partials/home";
	}
	
}
