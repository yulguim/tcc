package me.ulguim.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {
	
	@RequestMapping("/login")
    public String login() {
		//model.addAttribute("submenuModulosClasses", "");
		return "login";
    }

	@RequestMapping("/")
    public String app() {
		//model.addAttribute("submenuModulosClasses", "");
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
	
}
