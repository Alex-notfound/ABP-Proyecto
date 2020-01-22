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

import com.padelclub.model.Campeonato;
import com.padelclub.model.Enfrentamiento;
import com.padelclub.model.Notificacion;
import com.padelclub.model.Pareja;
import com.padelclub.model.ParejaCampeonato;
import com.padelclub.model.ParejaCampeonatoId;
import com.padelclub.model.Pista;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.EnfrentamientoService;
import com.padelclub.service.api.NotificacionService;
import com.padelclub.service.api.ParejaCampeonatoService;
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
	@Autowired
	private ParejaCampeonatoService parejaCampeonatoService;
	@Autowired
	private NotificacionService notificacionService;

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model, Principal usuarioLogeado) {
		model.addAttribute("reserva", enfrentamientoService.get(id).getReserva());
		model.addAttribute("enfrentamiento", true);
		addUserToModel(usuarioLogeado, model);
		return "ReservasView/ReservasForm";
	}

	@PostMapping("/save")
	public String save(Reserva reserva, @RequestParam("pistaId") Long idPista, Principal usuarioLogeado, Model model) {

		Reserva reservaAConfirmar = new Reserva();
		reservaAConfirmar.setFecha(reserva.getFecha());
		reservaAConfirmar.setHora(reserva.getHora());
		reservaAConfirmar = reservaService.save(reservaAConfirmar);

		Usuario usuario = usuarioService.getUsuario(usuarioLogeado);
		reserva = reservaService.findById(reserva.getId());
		Enfrentamiento enfrentamiento = enfrentamientoService.getByReserva(reserva);
		Campeonato campeonato = enfrentamientoService.getCampeonatoByReserva(reserva);

		System.err.println("ENFRENTAMIENTO - RESERVA: " + reserva.getHora());

		Notificacion notificacion = new Notificacion();
		notificacion.setCampeonato(campeonato);
		notificacion.setReserva(reservaAConfirmar);
		notificacion.setEnfrentamiento(enfrentamiento);
		Pareja pareja1 = enfrentamiento.getPareja1();
		Pareja pareja2 = enfrentamiento.getPareja2();
		if (pareja1.getMiembro1().getId() == usuario.getId()) {
			notificacion.setEmisora(pareja1);
			notificacion.setReceptora(pareja2);
		} else {
			notificacion.setEmisora(pareja2);
			notificacion.setReceptora(pareja1);
		}
		notificacionService.save(notificacion);
		notificacionService.sendEmail(usuario);

		return "redirect:/campeonatos/consultar/" + campeonato.getId();
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

	@GetMapping("/resultado/{id}")
	public String resultado(@PathVariable("id") Long id, Model model, Principal usuarioLogeado) {
		model.addAttribute("enfrentamiento", enfrentamientoService.get(id));
		addUserToModel(usuarioLogeado, model);
		return "CampeonatosView/ResultadoForm";
	}

	@PostMapping("/resultado")
	public String resultadoSave(Enfrentamiento enfrentamiento, @RequestParam("ganoPrimero") boolean ganoPrimero,
			Model model, Principal usuarioLogeado) {

		if (ganoPrimero) {
			enfrentamiento.setGanador(enfrentamiento.getPareja1());
		} else {
			enfrentamiento.setGanador(enfrentamiento.getPareja2());
		}

		enfrentamientoService.save(enfrentamiento);
		ParejaCampeonato ganador;
		ParejaCampeonato perdedor;

		if (enfrentamiento.getGanador().equals(enfrentamiento.getPareja1())) {
			ganador = parejaCampeonatoService
					.get(new ParejaCampeonatoId(enfrentamiento.getCampeonato(), enfrentamiento.getPareja1()));
			perdedor = parejaCampeonatoService
					.get(new ParejaCampeonatoId(enfrentamiento.getCampeonato(), enfrentamiento.getPareja2()));
		} else {
			perdedor = parejaCampeonatoService
					.get(new ParejaCampeonatoId(enfrentamiento.getCampeonato(), enfrentamiento.getPareja1()));
			ganador = parejaCampeonatoService
					.get(new ParejaCampeonatoId(enfrentamiento.getCampeonato(), enfrentamiento.getPareja2()));
		}
		ganador.setPuntos(ganador.getPuntos() + 4);
		perdedor.setPuntos(perdedor.getPuntos() + 1);

		parejaCampeonatoService.save(ganador);
		parejaCampeonatoService.save(perdedor);

		return "redirect:/campeonatos/consultar/" + enfrentamiento.getCampeonato().getId();
	}

	@GetMapping("/aceptar/{id}")
	public String accept(@PathVariable("id") Long id, Model model, Principal usuarioLogeado) {
		Notificacion notificacion = notificacionService.get(id);
		Enfrentamiento enfrentamiento = notificacion.getEnfrentamiento();
		Reserva reserva = reservaService.findPistaForReserva(notificacion.getReserva());

		if (reserva != null) {
			reserva = reservaService.save(reserva);
			enfrentamiento.setReserva(reserva);
			enfrentamiento = enfrentamientoService.save(enfrentamiento);
			notificacionService.deleteForEnfrentamiento(enfrentamiento);
		} else {
			model.addAttribute("error", "Ya no hay pistas libres para esa fecha y hora");
			notificacionService.delete(id);
		}

		addUserToModel(usuarioLogeado, model);
		return "redirect:/campeonatos/consultar/" + enfrentamiento.getCampeonato().getId();
	}

	@GetMapping("/rechazar/{id}")
	public String reject(@PathVariable("id") Long id, Model model, Principal usuarioLogeado) {
		Notificacion notificacion = notificacionService.get(id);
		Enfrentamiento enfrentamiento = notificacion.getEnfrentamiento();
		notificacionService.delete(id);
		addUserToModel(usuarioLogeado, model);
		return "redirect:/campeonatos/consultar/" + enfrentamiento.getCampeonato().getId();
	}

	public void addUserToModel(Principal usuario, Model model) {
		model.addAttribute("sesion", usuarioService.getUsuario(usuario));
	}
}