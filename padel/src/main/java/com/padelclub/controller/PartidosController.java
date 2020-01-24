package com.padelclub.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.model.Partido;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;
import com.padelclub.model.UsuarioPartido;
import com.padelclub.model.UsuarioPartidoId;
import com.padelclub.service.api.PartidoService;
import com.padelclub.service.api.ReservaService;
import com.padelclub.service.api.UsuarioPartidoService;
import com.padelclub.service.api.UsuarioService;

@Controller
@RequestMapping("/partidos")
public class PartidosController {

	@Autowired
	private PartidoService partidoService;
	@Autowired
	private UsuarioPartidoService usuarioPartidoService;
	@Autowired
	private ReservaService reservaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ReservasController reservasController;

	@RequestMapping(value = { "", "/" })
	public String listAll(Model model, Principal usuarioLogeado) {
		model.addAttribute("map", usuarioPartidoService.getListado(partidoService.getAll()));
		addUserToModel(usuarioLogeado, model);
		return "PartidosView/PartidosShowAll";
	}

	@RequestMapping("/promocionados")
	public String listPromocionados(Model model, Principal usuarioLogeado) {
		model.addAttribute("map", usuarioPartidoService.getListado(partidoService.getPromocionados()));
		addUserToModel(usuarioLogeado, model);
		return "/PartidosView/PartidosShowAll";
	}

	@RequestMapping("/ofertados")
	public String listOfertados(Model model, Principal usuarioLogeado) {
		model.addAttribute("map", usuarioPartidoService.getListado(partidoService.getOfertados()));
		addUserToModel(usuarioLogeado, model);
		return "/PartidosView/PartidosShowAll";
	}

	@GetMapping("/save/{id}")
	public String mostrarForm(@PathVariable("id") Long id, Model model, Principal usuarioLogeado) {
		if (id != null && id != 0) {
			model.addAttribute("reserva", reservaService.get(id));
		} else {
			model.addAttribute("reserva", new Reserva());
		}
		model.addAttribute("partido", true);

		addUserToModel(usuarioLogeado, model);
		return "ReservasView/ReservasForm";
	}

	@PostMapping("/save")
	public String guardar(Reserva reserva, Principal usuarioLogeado, Model model) {

		reserva = reservaService.findPistaForReserva(reserva);

		if (reserva != null && reservaService.validarReserva(reserva)) {
			reserva.setDisponible(true);
		} else {
			model.addAttribute("error", "Los datos no son válidos");
			return listAll(model, usuarioLogeado);
		}

		Usuario usuario = usuarioService.getUsuario(usuarioLogeado);
		reserva.setUsuario(usuario);
		Reserva reservaGuardada = reservaService.save(reserva);

		// Si es una adicion, hay que crear el partido asociandole la reserva
		if (!partidoService.existePartido(reservaGuardada)) {
			Partido partido = new Partido();
			partido.setAbierto(true);
			if (usuario.isAdministrador()) {
				partido.setTipo("Promocionado");
				reservaGuardada.setPista(null);
				reservaService.save(reservaGuardada);
				partido.setReserva(reservaGuardada);
			} else {
				partido.setTipo("Ofertado");
				reservaGuardada.setDisponible(false);
				partido.setReserva(reservaService.save(reservaGuardada));
			}
			Partido partidoGuardado = partidoService.save(partido);
			if (!usuario.isAdministrador()) {
				UsuarioPartido usuarioPartido = new UsuarioPartido();
				usuarioPartido.setId(new UsuarioPartidoId(partidoGuardado, usuario));
				usuarioPartidoService.save(usuarioPartido);
			}
		}
		return "redirect:/partidos/";
	}

	@GetMapping("/delete/{id}")
	public String borrar(@PathVariable Long id, Principal principal, Model model) {
		Usuario usuario = usuarioService.getUsuario(principal);
		Partido partido = partidoService.get(id);
		if (usuario.isAdministrador()
				|| (partido.getTipo().equals("Ofertado") && partido.getReserva().getUsuario().equals(usuario))) {
			partidoService.delete(id);
		} else {
			usuarioPartidoService.deleteUserFromPartido(partido, usuario);
		}
		return "redirect:/partidos/";
	}

	@PostMapping("/buscar")
	public String buscar(Reserva reserva, Model model, Principal usuarioLogeado) {
		model.addAttribute("partido", true);
		return reservasController.buscar(reserva, model, usuarioLogeado);
	}

	@GetMapping("/inscribir/{id}")
	public String inscribir(@PathVariable("id") Long idPartido, Principal principal, Model model) {
		Partido partido = partidoService.get(idPartido);
		Usuario usuario = usuarioService.getUsuario(principal);

		if (usuarioPartidoService.getNumJugadoresPartido(partido) < 4) {
			UsuarioPartido usuarioPartido = new UsuarioPartido();
			usuarioPartido.setId(new UsuarioPartidoId(partido, usuario));
			usuarioPartidoService.save(usuarioPartido);
			if (usuarioPartidoService.getNumJugadoresPartido(partido) == 4) {
				partido.setAbierto(false);
				if (partido.getTipo().equals("Promocionado")) {
					Reserva reserva = reservaService.findPistaForReserva(partido.getReserva());
					if (reserva != null) {
						reserva.setDisponible(false);
						reservaService.save(reserva);
						partidoService.save(partido);
					} else {
						partidoService.delete(idPartido);
					}
				} else {
					partidoService.save(partido);
				}
			}
		}

		return "redirect:/partidos/";

	}

	public void addUserToModel(Principal usuario, Model model) {
		model.addAttribute("sesion", usuarioService.getUsuario(usuario));
	}
}
