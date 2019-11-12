package com.padelclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.dao.api.UsuarioRepository;
import com.padelclub.model.Usuario2;
import com.padelclub.service.api.UsuarioServiceAPI;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private UsuarioServiceAPI usuarioServiceAPI;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("list", usuarioRepo.findAll());
		return "UsuariosView/UsuariosShowAll";
	}

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model) {
		if (id != null && id != 0) {
			model.addAttribute("usuario", usuarioServiceAPI.get(id));
		} else {
			model.addAttribute("usuario", new Usuario2());
		}
		return "UsuariosView/UsuariosForm";
	}

	@PostMapping("/save")
	public String save(Usuario2 usuario, Model model) {
		usuarioServiceAPI.save(usuario);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		usuarioServiceAPI.delete(id);
		return "redirect:/";
	}
}
