package com.padelclub.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padelclub.model.Usuario;
import com.padelclub.service.api.ReservaService;
import com.padelclub.service.api.ParejaCampeonatoService;
import com.padelclub.service.api.UsuarioPartidoService;
import com.padelclub.service.api.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ReservaService reservaService;
	@Autowired
	private ParejaCampeonatoService usuarioCampeonatoService;
	@Autowired
	private UsuarioPartidoService usuarioPartidoService;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);

	@RequestMapping(value = { "", "/" })
	public String index(Model model, Principal usuarioLogeado) {
		model.addAttribute("list", usuarioService.getAll());
		addUserToModel(usuarioLogeado, model);
		return "UsuariosView/UsuariosShowAll";
	}

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model, Principal usuarioLogeado) {
		if (id != null && id != 0) {
			model.addAttribute("usuario", usuarioService.get(id));
		} else {
			model.addAttribute("usuario", new Usuario());
		}
		addUserToModel(usuarioLogeado, model);
		return "UsuariosView/UsuariosForm";
	}

	@PostMapping("/save")
	public String save(Usuario usuario, Model model) {
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuarioService.save(usuario);
		return "redirect:/usuarios/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		usuarioService.delete(id);
		return "redirect:/usuarios/";
	}

	@GetMapping("/profile/{id}")
	public String myprofile(@PathVariable Long id, Principal principalUsuario, Model model, Principal usuarioLogeado) {
		Usuario usuario;
		if (id != null && id != 0) {
			usuario = usuarioService.get(id);
		} else {
			usuario = usuarioService.getUsuario(principalUsuario);
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("reservas", reservaService.findAllByUsuario(usuario));
		model.addAttribute("partidos", usuarioPartidoService.getPartidosByUsuario(usuario));
		model.addAttribute("campeonatos", usuarioCampeonatoService.findAllCampeonatosByUsuario(usuario));
		addUserToModel(usuarioLogeado, model);
		return "UsuariosView/UsuariosProfile";
	}

	public void addUserToModel(Principal usuario, Model model) {
		model.addAttribute("sesion", usuarioService.getUsuario(usuario));
	}

}
