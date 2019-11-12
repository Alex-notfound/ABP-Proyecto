package com.padelclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PruebaController {
	@RequestMapping("/prueba")
	public String prueba(Model model) {
		return "campeonato";
	}
}
