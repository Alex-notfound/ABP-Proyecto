package com.padelclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PruebaController {
	@RequestMapping("/prueba")
	public String prueba(Model model) {
		return "UsuariosView/formulariousuario";
	}

	@RequestMapping("/prueba2")
	public String prueba2(Model model) {
		return "promocionados";
	}

	@RequestMapping("/prueba3")
	public String prueba3(Model model) {
		return "UsuariosView/UsuarioProfile";
	}
}