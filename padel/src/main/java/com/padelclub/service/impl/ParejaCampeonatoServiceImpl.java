package com.padelclub.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.CampeonatoRepository;
import com.padelclub.dao.api.ParejaCampeonatoRepository;
import com.padelclub.dao.api.ParejaRepository;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Enfrentamiento;
import com.padelclub.model.Pareja;
import com.padelclub.model.ParejaCampeonato;
import com.padelclub.model.ParejaCampeonatoId;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.EnfrentamientoService;
import com.padelclub.service.api.ParejaCampeonatoService;

@Service
public class ParejaCampeonatoServiceImpl extends GenericServiceImpl<ParejaCampeonato, ParejaCampeonatoId>
		implements ParejaCampeonatoService {

	@Autowired
	private EnfrentamientoService enfretamientoService;
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

	@Override
	public List<ParejaCampeonato> getClasificacion(Campeonato campeonato) {
		return parejaCampeonatoRepository.findAllByCampeonatoOrderByPuntos(campeonato.getId());
	}

	@Override
	public List<ParejaCampeonato> findAllByCampeonatoAndGrupo(Campeonato campeonato, int grupo) {
		return parejaCampeonatoRepository.findAllByCampeonatoAndGrupo(campeonato.getId(), grupo);
	}

	@Override
	public boolean validarInscripcion(Usuario miembro1, Usuario miembro2, Campeonato campeonato) {
		List<Pareja> parejasInscritas = getParejasByCampeonato(campeonato);
		for (Pareja pareja : parejasInscritas) {
			Usuario inscrito1 = pareja.getMiembro1();
			Usuario inscrito2 = pareja.getMiembro2();
			if (inscrito1.equals(miembro1) || inscrito1.equals(miembro2) || inscrito2.equals(miembro1)
					|| inscrito2.equals(miembro2)) {
				return false;
			}
		}
		if (campeonato.getCategoria().equals("Mixto")) {
			return true;
		} else if (campeonato.getCategoria().equals("Masculino")) {
			return miembro1.getSexo().equals("Masculino") && miembro2.getSexo().equals("Masculino");
		} else if (campeonato.getCategoria().equals("Femenino")) {
			return miembro1.getSexo().equals("Femenino") && miembro2.getSexo().equals("Femenino");
		} else {
			return false;
		}

	}

	@Override
	public Map<Integer, List<ParejaCampeonato>> getClasificacionAgrupada(Campeonato campeonato) {
		List<ParejaCampeonato> list = parejaCampeonatoRepository
				.findAllByCampeonatoOrderByGrupoAscPuntosDesc(campeonato.getId());
		Map<Integer, List<ParejaCampeonato>> map = new LinkedHashMap<>();
		List<ParejaCampeonato> listToPut = new ArrayList<>();
		int grupoActual = 1;
		for (ParejaCampeonato parejaCampeonato : list) {
			if (grupoActual != parejaCampeonato.getGrupo()) {
				map.put(grupoActual, listToPut);
				listToPut = new ArrayList<>();
				grupoActual++;
			}
			listToPut.add(parejaCampeonato);
		}
		map.put(grupoActual, listToPut);
		return map;
	}

	@Override
	public Map<Integer, List<ParejaCampeonato>> getClasificacionPlayoffAgrupada(Campeonato campeonato) {

		Map<Integer, List<ParejaCampeonato>> toret = new LinkedHashMap<>();
		Map<Integer, List<Enfrentamiento>> map;

		for (int i = 4; i > 1; i--) {
			map = enfretamientoService.getEnfrentamientosByFaseAgrupados(campeonato, i);
			if (map != null) {
				toret = clasificarPorFase(map, toret);
			}
		}
		return toret;
	}

	private Map<Integer, List<ParejaCampeonato>> clasificarPorFase(Map<Integer, List<Enfrentamiento>> map,
			Map<Integer, List<ParejaCampeonato>> toret) {
		for (Entry<Integer, List<Enfrentamiento>> entry : map.entrySet()) {
			List<ParejaCampeonato> list = new ArrayList<>();
			for (Enfrentamiento enfrentamiento : entry.getValue()) {
				if (enfrentamiento.getGanador() != null) {
					list.add(parejaCampeonatoRepository.getOne(
							new ParejaCampeonatoId(enfrentamiento.getCampeonato(), enfrentamiento.getGanador())));
					if (enfrentamiento.getGanador().equals(enfrentamiento.getPareja1())) {
						list.add(parejaCampeonatoRepository.getOne(
								new ParejaCampeonatoId(enfrentamiento.getCampeonato(), enfrentamiento.getPareja2())));
					} else {
						list.add(parejaCampeonatoRepository.getOne(
								new ParejaCampeonatoId(enfrentamiento.getCampeonato(), enfrentamiento.getPareja1())));
					}
				}
			}
			toret.put(entry.getKey(), list);
		}
		return toret;
	}

	@Override
	public ParejaCampeonato getFirstOfEachGroup(Campeonato campeonato) {
		// TODO Auto-generated method stub
		return null;
	}

}
