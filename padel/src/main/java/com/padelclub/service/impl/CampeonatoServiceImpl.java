package com.padelclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.CampeonatoRepository;
import com.padelclub.model.Campeonato2;
import com.padelclub.service.api.CampeonatoService;

@Service
public class CampeonatoServiceImpl extends GenericServiceImpl<Campeonato2, Long> implements CampeonatoService {

	@Autowired
	private CampeonatoRepository campeonatoDao;

	@Override
	public CrudRepository<Campeonato2, Long> getDao() {
		return campeonatoDao;
	}

}
