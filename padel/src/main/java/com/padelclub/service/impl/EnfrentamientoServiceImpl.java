package com.padelclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.EnfrentamientoRepository;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Enfrentamiento;
import com.padelclub.model.Reserva;
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

	@Override
	public List<Enfrentamiento> getAllByCampeonato(Campeonato campeonato) {
		return enfrentamientoRepository.findAllByCampeonato(campeonato);
	}

	@Override
	public Campeonato getCampeonatoByReserva(Reserva reserva) {
		return enfrentamientoRepository.findByReserva(reserva).getCampeonato();
	}

	@Override
	public Enfrentamiento getByReserva(Reserva reserva) {
		return enfrentamientoRepository.findByReserva(reserva);
	}
}
