package com.clubPadel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/index")
	public String index(Model model) {
		model.addAttribute("nombre", "Alex");
		return "home";
	}
}
