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

import com.padelclub.model.Partido;
import com.padelclub.model.Pista;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;
import com.padelclub.model.UsuarioPartido;
import com.padelclub.model.UsuarioPartidoId;
import com.padelclub.service.api.PartidoService;
import com.padelclub.service.api.PistaService;
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
	private PistaService pistaService;
	@Autowired
	private ReservaService reservaService;
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = { "", "/" })
	public String index(Model model, Principal usuarioLogeado) {
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
	public String showSave(@PathVariable("id") Long id, Model model, Principal usuarioLogeado) {
		if (id != null && id != 0) {
			model.addAttribute("reserva", reservaService.get(id));
		} else {
			if (usuarioService.getUsuario(usuarioLogeado).isAdministrador()) {
				System.err.println("ADMIN");
				Partido partido = new Partido();
				partido.setTipo("Promocionado");
				partido.setReserva(reservaService.save(new Reserva()));
				partidoService.save(partido);
				return index(model, usuarioLogeado);
			}
			model.addAttribute("reserva", new Reserva());
		}
		model.addAttribute("partido", true);
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

		// Si es una adicion, hay que crear el partido asociandole la reserva
		if (!partidoService.existePartido(reservaGuardada)) {
			Partido partido = new Partido();
			partido.setReserva(reservaGuardada);

			if (usuario.isAdministrador()) {
				partido.setTipo("Promocionado");
			} else {
				partido.setTipo("Ofertado");
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
	public String delete(@PathVariable Long id, Principal principal, Model model) {
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
		List<Pista> pistas = pistaService.getAll();
		model.addAttribute("map", reservaService.getReservasDao(reserva, pistas));
		model.addAttribute("pistas", pistas);
		model.addAttribute("fecha", reserva.getFecha());
		model.addAttribute("partido", true);
		addUserToModel(usuarioLogeado, model);
		return "ReservasView/ReservasShowByFecha";
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
					Reserva reserva = reservaService.findReservaForToday();
					if (reserva != null) {
						reservaService.save(reserva);
						partido.setReserva(reserva);
						partidoService.save(partido);
					} else {
						partidoService.delete(idPartido);
					}
				}
			}
		}

		return "redirect:/partidos/";
	}

	public void addUserToModel(Principal usuario, Model model) {
		model.addAttribute("sesion", usuarioService.getUsuario(usuario));
	}
}
