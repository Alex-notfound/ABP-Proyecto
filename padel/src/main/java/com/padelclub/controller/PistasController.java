package com.padelclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.model.Pista;
import com.padelclub.service.api.PistaService;

@Controller
@RequestMapping("/pistas")
public class PistasController {

	@Autowired
	private PistaService pistaService;

	@RequestMapping({ "", "/" })
	public String index(Model model) {
		model.addAttribute(new Pista());
		return "PistasView/PistasShowAll";
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
}
