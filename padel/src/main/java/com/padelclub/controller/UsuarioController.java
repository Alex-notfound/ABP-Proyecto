package com.padelclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.dao.api.UsuarioRepo;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.UsuarioServiceAPI;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepo usuarioRepo;
	private UsuarioServiceAPI usuarioServiceAPI;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("list", usuarioRepo.findAll());
		return "index";
	}

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model) {
		if (id != null && id != 0) {
			model.addAttribute("usuario", usuarioServiceAPI.get(id));
		}
		return "save";
	}

	@PostMapping("/save")
	public String save(Usuario usuario, Model model) {
		usuarioServiceAPI.save(usuario);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		usuarioServiceAPI.delete(id);
		return "redirect:/";
	}
}
