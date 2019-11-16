package com.padelclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.CampeonatoRepository;
import com.padelclub.dao.api.UsuarioCampeonatoRepository;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Usuario;
import com.padelclub.model.UsuarioCampeonato;
import com.padelclub.model.UsuarioCampeonatoId;
import com.padelclub.service.api.UsuarioCampeonatoService;

@Service
public class UsuarioCampeonatoServiceImpl extends GenericServiceImpl<UsuarioCampeonato, UsuarioCampeonatoId>
		implements UsuarioCampeonatoService {

	@Autowired
	private UsuarioCampeonatoRepository usuarioCampeonatoRepository;
	@Autowired
	private CampeonatoRepository campeonatoRepository;

	@Override
	public CrudRepository<UsuarioCampeonato, UsuarioCampeonatoId> getDao() {
		return usuarioCampeonatoRepository;
	}

	@Override
	public List<Campeonato> findAllCampeonatosByUsuario(Usuario user) {
		return campeonatoRepository.findAllById(usuarioCampeonatoRepository.findAllCampeonatosByUsuario(user.getId()));
	}
}
