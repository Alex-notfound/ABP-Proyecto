package com.padelclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.PartidoRepository;
import com.padelclub.model.Partido;
import com.padelclub.service.api.PartidoService;

@Service
public class PartidoServiceImpl extends GenericServiceImpl<Partido, Long> implements PartidoService {

	@Autowired
	private PartidoRepository partidoRepository;

	@Override
	public CrudRepository<Partido, Long> getDao() {
		return partidoRepository;
	}

	@Override
	public List<Partido> getPromocionados() {
		return partidoRepository.findAllByTipo("Promocionado");
	}

	@Override
	public List<Partido> getOfertados() {
		return partidoRepository.findAllByTipo("Ofertado");
	}

}
