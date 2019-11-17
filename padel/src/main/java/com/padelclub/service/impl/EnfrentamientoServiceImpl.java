package com.padelclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.EnfrentamientoRepository;
import com.padelclub.model.Enfrentamiento;
import com.padelclub.service.api.EnfrentamientoService;

@Service
public class EnfrentamientoServiceImpl extends GenericServiceImpl<Enfrentamiento, Long>
		implements EnfrentamientoService {

	@Autowired
	private EnfrentamientoRepository enfrentamientoRepository;

	@Override
	public CrudRepository<Enfrentamiento, Long> getDao() {
		return enfrentamientoRepository;
	}
}
