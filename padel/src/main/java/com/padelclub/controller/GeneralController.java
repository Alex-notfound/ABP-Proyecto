package com.padelclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {

	@RequestMapping("/prueba")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping("/prueba2")
	public String prueba2(Model model) {
		return "CampeonatosView/ResultadoForm";
	}

}