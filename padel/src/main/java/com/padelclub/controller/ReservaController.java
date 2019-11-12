package com.padelclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.model.Reserva2;
import com.padelclub.service.api.ReservaService;
import com.padelclub.service.api.UsuarioService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = { "", "/" })
	public String index(Model model) {
		model.addAttribute("list", reservaService.getAll());
		return "ReservasView/ReservasShowAll";
	}

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model) {
		if (id != null && id != 0) {
			model.addAttribute("reserva", reservaService.get(id));
		} else {
			model.addAttribute("reserva", new Reserva2());
		}
		return "ReservasView/ReservasForm";
	}

	@PostMapping("/save")
	public String save(Reserva2 reserva, Model model) {
		reservaService.save(reserva);
		return "redirect:/reservas/";
	}

	@PostMapping("/buscar")
	public String buscar(Reserva2 reserva, Model model) {
		model.addAttribute("fecha", reserva.getFecha());
		model.addAttribute("list", reservaService.findAllByFecha(reserva.getFecha()));
		return "ReservasView/ReservasShowByFecha";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		reservaService.delete(id);
		return "redirect:/reservas/";
	}

	@GetMapping("/inscribir/{idReserva}/{idUsuario}")
	public String inscribir(@PathVariable("idReserva") Long idReserva, @PathVariable("idUsuario") Long idUsuario,
			Model model) {
		Reserva2 reserva = reservaService.get(idReserva);
		reserva.setUsuario(usuarioService.get(idUsuario));
		reserva.setDisponible(false);
		reservaService.save(reserva);
		return "redirect:/reservas/";
	}
}