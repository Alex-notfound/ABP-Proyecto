package com.padelclub.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.model.Campeonato;
import com.padelclub.model.Usuario;
import com.padelclub.model.UsuarioCampeonato;
import com.padelclub.model.UsuarioCampeonatoId;
import com.padelclub.service.api.CampeonatoService;
import com.padelclub.service.api.UsuarioCampeonatoService;
import com.padelclub.service.api.UsuarioService;

@Controller
@RequestMapping("/campeonatos")
public class CampeonatoController {

	@Autowired
	private CampeonatoService campeonatoService;
	@Autowired
	private UsuarioCampeonatoService usuarioCampeonatoService;
	@Autowired
	private UsuarioService usuarioService;

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
			model.addAttribute("campeonato", new Campeonato());
		}
		return "CampeonatosView/CampeonatosForm";
	}

	@PostMapping("/save")
	public String save(Campeonato campeonato, Model model) {
		campeonatoService.save(campeonato);
		return "redirect:/campeonatos/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		campeonatoService.delete(id);
		return "redirect:/campeonatos/";
	}

	@GetMapping("/inscribir/{idCampeonato}")
	public String inscribir(@PathVariable("idCampeonato") Long idCampeonato, Principal usuario, Model model) {

		Campeonato campeonato = campeonatoService.get(idCampeonato);
		campeonato.setNumParticipantes(campeonato.getNumParticipantes() + 1);
		Usuario user = usuarioService.getUsuario(usuario);
		UsuarioCampeonato usuarioCampeonato = new UsuarioCampeonato();
		usuarioCampeonato.setId(new UsuarioCampeonatoId(campeonato, user));

		campeonatoService.save(campeonato);
		usuarioCampeonatoService.save(usuarioCampeonato);
		return "redirect:/campeonatos/";
	}
	
	@GetMapping("/consultar/{id}")
	public String consultar(@PathVariable Long id, Model model) {
		return "CampeonatosView/CampeonatoDetail";
	}

}