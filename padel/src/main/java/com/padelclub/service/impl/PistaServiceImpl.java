package com.padelclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.PistaRepository;
import com.padelclub.model.Pista;
import com.padelclub.service.api.PistaService;

@Service
public class PistaServiceImpl extends GenericServiceImpl<Pista, Long> implements PistaService {

	@Autowired
	private PistaRepository pistaRepository;

	@Override
	public CrudRepository<Pista, Long> getDao() {
		return pistaRepository;
	}
}
