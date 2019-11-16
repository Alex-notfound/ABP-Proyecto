package com.padelclub.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.PartidoRepository;
import com.padelclub.dao.api.UsuarioPartidoRepository;
import com.padelclub.dao.api.UsuarioRepository;
import com.padelclub.model.Partido;
import com.padelclub.model.Usuario;
import com.padelclub.model.UsuarioPartido;
import com.padelclub.model.UsuarioPartidoId;
import com.padelclub.service.api.UsuarioPartidoService;

@Service
public class UsuarioPartidoServiceImpl extends GenericServiceImpl<UsuarioPartido, UsuarioPartidoId>
		implements UsuarioPartidoService {

	@Autowired
	private UsuarioPartidoRepository usuarioPartidoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PartidoRepository partidoRepository;

	@Override
	public CrudRepository<UsuarioPartido, UsuarioPartidoId> getDao() {
		return usuarioPartidoRepository;
	}

	@Override
	public void deleteUserFromPartido(Partido partido, Usuario usuario) {
		usuarioPartidoRepository.deleteByPartidoUsuario(partido.getId(), usuario.getId());
	}

	@Override
	public int getNumJugadoresPartido(Partido partido) {
		return usuarioPartidoRepository.countAllByPartidoId(partido.getId());
	}

	@Override
	public Map<Partido, List<Usuario>> getListado(List<Partido> all) {
		Map<Partido, List<Usuario>> map = new LinkedHashMap<>();
		for (Partido partido : all) {
			if (usuarioPartidoRepository.countAllByPartidoId(partido.getId()) > 0) {
				map.put(partido, usuarioRepository
						.findAllById(usuarioPartidoRepository.findAllUsuariosByPartidoId(partido.getId())));
			} else {
				map.put(partido, new ArrayList<>());
			}
		}
		return map;
	}

	@Override
	public List<Partido> getPartidosByUsuario(Usuario usuario) {
		return partidoRepository.findAllById(usuarioPartidoRepository.findAllByUsuario(usuario.getId()));
	}

}
