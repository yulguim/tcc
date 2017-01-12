package me.ulguim.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import in.k2s.sdk.springboot.resource.ResourceList;
import in.k2s.sdk.web.validation.ValidationException;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
    public String login() {
		//model.addAttribute("submenuModulosClasses", "");
		return "index";
    }
	
}
