package com.padelclub.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.service.api.UsuarioService;

@Controller
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping({ "", "/", "/index" })
	public String index(Principal principal, Model model) {
		addUserToModel(principal, model);
		return "index";
	}

	@GetMapping("/login")
	public String index() {
		return "login";
	}

	public void addUserToModel(Principal usuario, Model model) {
		model.addAttribute("sesion", usuarioService.getUsuario(usuario));
	}
}
