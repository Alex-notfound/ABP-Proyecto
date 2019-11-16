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

import com.padelclub.model.Partido;
import com.padelclub.model.Pista;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.PartidoService;
import com.padelclub.service.api.PistaService;
import com.padelclub.service.api.ReservaService;
import com.padelclub.service.api.UsuarioService;

@Controller
@RequestMapping("/partidos")
public class PartidosController {

	@Autowired
	private PartidoService partidoService;
	@Autowired
	private PistaService pistaService;
	@Autowired
	private ReservaService reservaService;
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = { "", "/" })
	public String index(Model model) {
		model.addAttribute("list", partidoService.getAll());
		return "PartidosView/PartidosShowAll";
	}

	@RequestMapping("/promocionados")
	public String listPromocionados(Model model) {
		model.addAttribute("list", partidoService.getPromocionados());
		return "/PartidosView/PartidosShowAll";
	}

	@RequestMapping("/ofertados")
	public String listOfertados(Model model) {
		model.addAttribute("list", partidoService.getOfertados());
		return "/PartidosView/PartidosShowAll";
	}

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model) {
		if (id != null && id != 0) {
			model.addAttribute("reserva", reservaService.get(id));
		} else {
			model.addAttribute("reserva", new Reserva());
		}
		model.addAttribute("partido", true);
		return "ReservasView/ReservasForm";
	}

	@PostMapping("/save")
	public String save(Principal principal, Model model) {

		Partido partido = new Partido();
		Reserva reserva = new Reserva();
//		reserva.setPista(reservaDTO.getPista());
//		reserva.setFecha(reservaDTO.getFecha());
//		reserva.setHora(reservaDTO.getHora());
//		reserva.setDisponible(false);

		Usuario usuario = usuarioService.getUsuario(principal);
		reserva.setUsuario(usuario);
		reservaService.save(reserva);
		partido.setReserva(reserva);

		if (usuario.isAdministrador()) {
			partido.setTipo("promocionado");
		} else {
			partido.setTipo("ofertado");
			partido.setJugador1(usuario);
		}
		partidoService.save(partido);
		return "redirect:/partidos/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		partidoService.delete(id);
		return "redirect:/reservas/";
	}

	@PostMapping("/buscar")
	public String buscar(Reserva reserva, Model model) {
		List<Pista> pistas = pistaService.getAll();
		model.addAttribute("map", reservaService.getReservasDao(reserva, pistas));
		model.addAttribute("pistas", pistas);
		model.addAttribute("fecha", reserva.getFecha());
		model.addAttribute("partido", true);
		return "ReservasView/ReservasShowByFecha";
	}

//	@GetMapping("/inscribir/{idReserva}/{idUsuario}")
//	public String inscribir(@PathVariable("idReserva") Long idReserva, @PathVariable("idUsuario") Long idUsuario,
//			Model model) {
//		Reserva reserva = reservaService.get(idReserva);
//		reserva.setUsuario(usuarioService.get(idUsuario));
//		reserva.setDisponible(false);
//		reservaService.save(reserva);
//		return "redirect:/reservas/";
//	}
//
//	@GetMapping("/liberar/{id}")
//	public String liberar(@PathVariable Long id, Model model) {
//		Reserva reserva = reservaService.get(id);
//		reserva.setUsuario(null);
//		reserva.setDisponible(true);
//		reservaService.save(reserva);
//		return "redirect:/reservas/";
//	}
}
