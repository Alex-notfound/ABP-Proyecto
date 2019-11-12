package com.padelclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.model.Campeonato2;
import com.padelclub.service.api.CampeonatoService;

@Controller
@RequestMapping("/campeonatos")
public class CampeonatoController {

	@Autowired
	private CampeonatoService campeonatoService;

	@RequestMapping(value = { "", "/" })
	public String index(Model model) {
		model.addAttribute("list", campeonatoService.getAll());
		return "CampeonatosView/CampeonatosShowAll";
	}

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model) {
		if (id != null && id != 0) {
			model.addAttribute("campeonato", campeonatoService.get(id));
		} else {
			model.addAttribute("campeonato", new Campeonato2());
		}
		return "CampeonatosView/CampeonatosForm";
	}

	@PostMapping("/save")
	public String save(Campeonato2 campeonato, Model model) {
		campeonatoService.save(campeonato);
		return "redirect:/campeonatos/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		campeonatoService.delete(id);
		return "redirect:/campeonatos/";
	}
}