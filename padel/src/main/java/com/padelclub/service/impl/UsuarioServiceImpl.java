package com.padelclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.UsuarioRepository;
import com.padelclub.model.Usuario2;
import com.padelclub.service.api.UsuarioServiceAPI;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario2, Long> implements UsuarioServiceAPI {

	@Autowired
	private UsuarioRepository usuarioDaoAPI;

	@Override
	public CrudRepository<Usuario2, Long> getDao() {
		return usuarioDaoAPI;
	}

}
