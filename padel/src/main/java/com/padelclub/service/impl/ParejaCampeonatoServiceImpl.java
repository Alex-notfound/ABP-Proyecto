package com.padelclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.CampeonatoRepository;
import com.padelclub.dao.api.ParejaCampeonatoRepository;
import com.padelclub.dao.api.ParejaRepository;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Pareja;
import com.padelclub.model.ParejaCampeonato;
import com.padelclub.model.ParejaCampeonatoId;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.ParejaCampeonatoService;

@Service
public class ParejaCampeonatoServiceImpl extends GenericServiceImpl<ParejaCampeonato, ParejaCampeonatoId>
		implements ParejaCampeonatoService {

	@Autowired
	private ParejaCampeonatoRepository parejaCampeonatoRepository;
	@Autowired
	private CampeonatoRepository campeonatoRepository;
	@Autowired
	private ParejaRepository parejaRepository;

	@Override
	public CrudRepository<ParejaCampeonato, ParejaCampeonatoId> getDao() {
		return parejaCampeonatoRepository;
	}

	@Override
	public List<Campeonato> findAllCampeonatosByUsuario(Usuario user) {
		return campeonatoRepository.findAllById(parejaCampeonatoRepository.findAllCampeonatosByUsuario(user.getId()));
	}

	@Override
	public int getNumParticipantesByCampeonato(Campeonato campeonato) {
		return parejaCampeonatoRepository.countAllByCampeonatoId(campeonato.getId());
	}

	@Override
	public List<Pareja> getParejasByCampeonato(Campeonato campeonato) {
		return parejaRepository.findAllById(parejaCampeonatoRepository.findAllParejasByCampeonato(campeonato.getId()));
	}
}
