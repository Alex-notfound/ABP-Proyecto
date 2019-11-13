package com.padelclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.UsuarioCampeonatoRepository;
import com.padelclub.model.UsuarioCampeonato;
import com.padelclub.model.UsuarioCampeonatoId;
import com.padelclub.service.api.UsuarioCampeonatoService;

@Service
public class UsuarioCampeonatoServiceImpl extends GenericServiceImpl<UsuarioCampeonato, UsuarioCampeonatoId>
		implements UsuarioCampeonatoService {

	@Autowired
	private UsuarioCampeonatoRepository usuarioCampeonatoRepository;

	@Override
	public CrudRepository<UsuarioCampeonato, UsuarioCampeonatoId> getDao() {
		return usuarioCampeonatoRepository;
	}

}
