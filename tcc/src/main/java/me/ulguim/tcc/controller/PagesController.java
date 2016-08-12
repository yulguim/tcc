package me.ulguim.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

	@RequestMapping("/")
    public String home() {
		System.out.println("AAAAAAA");
		//model.addAttribute("submenuModulosClasses", "");
		return "home";
    }
	
}
