package com.padelclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.model.Reserva2;
import com.padelclub.service.api.ReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

		@Autowired
		private ReservaService reservaService;

		@RequestMapping(value = { "", "/" })
		public String index(Model model) {
			model.addAttribute("list", reservaService.getAll());
			return "ReservasView/reservas";
		}

		@GetMapping("/save/{id}")
		public String showSave(@PathVariable("id") Long id, Model model) {
			if (id != null && id != 0) {
				model.addAttribute("reserva", reservaService.get(id));
			} else {
				model.addAttribute("reserva", new Reserva2());
			}
			return "ReservasView/formularioCampeonato";
		}

		@PostMapping("/save")
		public String save(Reserva2 reserva, Model model) {
			reservaService.save(reserva);
			return "redirect:/reservas/";
		}

		@GetMapping("/delete/{id}")
		public String delete(@PathVariable Long id, Model model) {
			reservaService.delete(id);
			return "redirect:/reservas/";
		}
}
