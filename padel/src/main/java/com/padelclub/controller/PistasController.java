package com.padelclub.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.model.Pista;
import com.padelclub.service.api.PistaService;
import com.padelclub.service.api.UsuarioService;

@Controller
@RequestMapping("/pistas")
public class PistasController {

	@Autowired
	private PistaService pistaService;
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping({ "", "/" })
	public String index(Model model, Principal usuarioLogeado) {
		model.addAttribute("list", pistaService.getAll());
		addUserToModel(usuarioLogeado, model);
		return "PistasView/PistasShowAll";
	}

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model, Principal usuarioLogeado) {
		if (id != null && id != 0) {
			model.addAttribute("pista", pistaService.get(id));
		} else {
			model.addAttribute("pista", new Pista());
		}
		addUserToModel(usuarioLogeado, model);
		return "PistasView/PistasForm";
	}

	@PostMapping("/save")
	public String add(Pista pista, Model model) {
		pistaService.save(pista);
		return "redirect:/pistas/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		pistaService.delete(id);
		return "redirect:/pistas/";
	}

	public void addUserToModel(Principal usuario, Model model) {
		model.addAttribute("sesion", usuarioService.getUsuario(usuario));
	}
}
