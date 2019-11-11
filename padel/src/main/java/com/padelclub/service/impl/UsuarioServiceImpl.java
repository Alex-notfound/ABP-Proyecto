package com.padelclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.UsuarioRepo;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.UsuarioServiceAPI;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioServiceAPI {

	@Autowired
	private UsuarioRepo usuarioDaoAPI;

	@Override
	public CrudRepository<Usuario, Long> getDao() {
		return usuarioDaoAPI;
	}

}
