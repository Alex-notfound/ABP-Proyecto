package com.padelclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.ParejaRepository;
import com.padelclub.model.Pareja;
import com.padelclub.service.api.ParejaService;

@Service
public class ParejaServiceImpl extends GenericServiceImpl<Pareja, Long> implements ParejaService {

	@Autowired
	private ParejaRepository parejaRepository;

	@Override
	public CrudRepository<Pareja, Long> getDao() {
		return parejaRepository;
	}

}
