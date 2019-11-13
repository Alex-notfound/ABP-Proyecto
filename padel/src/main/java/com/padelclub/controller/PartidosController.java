package com.padelclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partidos")
public class PartidosController {

//	@Autowired
//	private PartidoService partidoService;

	@RequestMapping("/promocionados")
	public String listPromocionados(Model model) {
		return "/PartidosView/PromocionadosShowAll";
	}

	@RequestMapping("/ofertados")
	public String listOfertados(Model model) {
		return "/PartidosView/OfertadosShowAll";
	}
}
