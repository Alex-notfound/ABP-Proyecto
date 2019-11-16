package com.padelclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.CampeonatoRepository;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.CampeonatoService;

@Service
public class CampeonatoServiceImpl extends GenericServiceImpl<Campeonato, Long> implements CampeonatoService {

	@Autowired
	private CampeonatoRepository campeonatoRepository;

	@Override
	public CrudRepository<Campeonato, Long> getDao() {
		return campeonatoRepository;
	}

}
