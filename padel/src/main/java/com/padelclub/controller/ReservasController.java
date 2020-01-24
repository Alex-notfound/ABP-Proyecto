package com.padelclub.controller;

import java.security.Principal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.model.Reserva;
import com.padelclub.service.api.ReservaService;
import com.padelclub.service.api.UsuarioService;

@Controller
@RequestMapping("/reservas")
public class ReservasController {

	@Autowired
	private ReservaService reservaService;
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = { "", "/" })
	public String list(Model model, Principal usuarioLogeado) {
		reservaService.deleteReservasAntiguas();
		model.addAttribute("list", reservaService.getAllFromUser(usuarioService.getUsuario(usuarioLogeado)));
		addUserToModel(usuarioLogeado, model);
		return "ReservasView/ReservasShowAll";
	}

	@GetMapping("/save/{id}")
	public String mostrarForm(@PathVariable("id") Long id, Model model, Principal usuarioLogeado) {
		if (reservaService.getNumReservasByUsuario(usuarioService.getUsuario(usuarioLogeado)) <= 5) {
			if (id != null && id != 0) {
				model.addAttribute("reserva", reservaService.get(id));
			} else {
				model.addAttribute("reserva", new Reserva());
			}
		} else {
			model.addAttribute("error", "Ya tienes 5 reservas a tu nombre");
			return list(model, usuarioLogeado);
		}
		addUserToModel(usuarioLogeado, model);
		return "ReservasView/ReservasForm";
	}

	@PostMapping("/save")
	public String guardar(Reserva reserva, Principal usuarioLogeado, Model model) {
		reserva = reservaService.findPistaForReserva(reserva);
		if (reserva != null && reservaService.validarReserva(reserva)) {
			reserva.setDisponible(false);
			reserva.setUsuario(usuarioService.getUsuario(usuarioLogeado));
			reservaService.save(reserva);
		} else {
			model.addAttribute("error", "Los datos no son válidos");
			return list(model, usuarioLogeado);
		}
		return "redirect:/reservas/";
	}

	@PostMapping("/buscar")
	public String buscar(Reserva reserva, Model model, Principal usuarioLogeado) {
		model.addAttribute("list", reservaService.getReservasDao(reserva));
		model.addAttribute("fecha", reserva.getFecha());
		model.addAttribute("reserva", reserva);
		addUserToModel(usuarioLogeado, model);
		return "ReservasView/ReservasShowByFecha";
	}

	@GetMapping("/delete/{id}")
	public String borrar(@PathVariable Long id, Model model, Principal usuarioLogeado) {

		Calendar fechaActual = Calendar.getInstance();

		Calendar fechaTope = Calendar.getInstance();
		fechaTope.setTime(reservaService.get(id).getFecha());
		fechaTope.add(Calendar.DAY_OF_MONTH, -1);

		if (fechaActual.before(fechaTope)) {
			reservaService.delete(id);
		} else {
			model.addAttribute("error", "No se puede liberar una reserva con tan poca antelación");
			return list(model, usuarioLogeado);
		}
		return "redirect:/reservas/";
	}

	@GetMapping("/inscribir/{idReserva}/{idUsuario}")
	public String inscribir(@PathVariable("idReserva") Long idReserva, @PathVariable("idUsuario") Long idUsuario,
			Model model) {
		Reserva reserva = reservaService.get(idReserva);
		reserva.setUsuario(usuarioService.get(idUsuario));
		reserva.setDisponible(false);
		reservaService.save(reserva);
		return "redirect:/reservas/";
	}

	public void addUserToModel(Principal usuario, Model model) {
		model.addAttribute("sesion", usuarioService.getUsuario(usuario));
	}
}