package com.padelclub.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.padelclub.model.Pista;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.EnfrentamientoService;
import com.padelclub.service.api.PistaService;
import com.padelclub.service.api.ReservaService;
import com.padelclub.service.api.UsuarioService;

@Controller
@RequestMapping("/enfrentamientos")
public class EnfrentamientoController {

	@Autowired
	private EnfrentamientoService enfrentamientoService;
	@Autowired
	private ReservaService reservaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PistaService pistaService;

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model, Principal usuarioLogeado) {
		model.addAttribute("reserva", enfrentamientoService.get(id).getReserva());
		model.addAttribute("enfrentamiento", true);
		addUserToModel(usuarioLogeado, model);
		return "ReservasView/ReservasForm";
	}

	@PostMapping("/save")
	public String save(Reserva reserva, @RequestParam("pistaId") Long idPista, Principal principal, Model model) {
		Usuario usuario = usuarioService.getUsuario(principal);
		reserva.setPista(pistaService.get(idPista));
		reserva.setDisponible(false);
		reserva.setUsuario(usuario);
		Reserva reservaGuardada = reservaService.save(reserva);
		enfrentamientoService.getCampeonatoByReserva(reservaGuardada);
		return "redirect:/campeonatos/consultar/"
				+ enfrentamientoService.getCampeonatoByReserva(reservaGuardada).getId();
	}

	@PostMapping("/buscar")
	public String buscar(Reserva reserva, Model model, Principal usuarioLogeado) {
		List<Pista> pistas = pistaService.getAll();
		model.addAttribute("map", reservaService.getReservasDao(reserva, pistas));
		model.addAttribute("pistas", pistas);
		model.addAttribute("fecha", reserva.getFecha());
		model.addAttribute("enfrentamiento", true);
		addUserToModel(usuarioLogeado, model);
		return "ReservasView/ReservasShowByFecha";
	}

	public void addUserToModel(Principal usuario, Model model) {
		model.addAttribute("sesion", usuarioService.getUsuario(usuario));
	}
}