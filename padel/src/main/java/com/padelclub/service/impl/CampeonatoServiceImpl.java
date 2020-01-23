package com.padelclub.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.CampeonatoRepository;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Enfrentamiento;
import com.padelclub.model.Pareja;
import com.padelclub.model.ParejaCampeonato;
import com.padelclub.model.ParejaCampeonatoId;
import com.padelclub.model.Reserva;
import com.padelclub.service.api.CampeonatoService;
import com.padelclub.service.api.EnfrentamientoService;
import com.padelclub.service.api.ParejaCampeonatoService;
import com.padelclub.service.api.ParejaService;
import com.padelclub.service.api.ReservaService;

@Service
public class CampeonatoServiceImpl extends GenericServiceImpl<Campeonato, Long> implements CampeonatoService {

	@Autowired
	private CampeonatoRepository campeonatoRepository;
	@Autowired
	private EnfrentamientoService enfrentamientoService;
	@Autowired
	private ReservaService reservaService;
	@Autowired
	private ParejaCampeonatoService parejaCampeonatoService;
	@Autowired
	private ParejaService parejaService;

	@Override
	public CrudRepository<Campeonato, Long> getDao() {
		return campeonatoRepository;
	}

	@Override
	public void sorteo(List<Pareja> parejasCampeonato, Campeonato campeonato) {
		int tamanoGrupo = -1;
		int tamanoGrupoAux = -1;

		int tamano = parejasCampeonato.size();
		int n = 8;
		while (tamanoGrupo != -1 && n <= 12) {
			if (tamano % n == 0) {
				tamanoGrupo = n;
			} else if (tamano % n == 8) {
				tamanoGrupo = n;
				tamanoGrupoAux = 8;
			} else if (tamano % n == 9) {
				tamanoGrupo = n;
				tamanoGrupoAux = 9;
			} else if (tamano % n == 10) {
				tamanoGrupo = n;
				tamanoGrupoAux = 10;
			} else if (tamano % n == 11) {
				tamanoGrupo = n;
				tamanoGrupoAux = 11;
			}
			n++;
		}
		if (tamanoGrupo == -1) {
			tamanoGrupo = 8;
			int numSobrantes = tamano % 8;
			while (numSobrantes > 0) {
				parejaCampeonatoService.delete(new ParejaCampeonatoId(campeonato, parejasCampeonato.get(tamano - 1)));
				parejaService.delete(parejasCampeonato.get(tamano - 1).getId());
				parejasCampeonato.remove(tamano - 1);
			}
		}
		int numGrupos = dividirEnGrupos(parejasCampeonato, campeonato, tamanoGrupo, tamanoGrupoAux);
		sorteoTodosContraTodos(campeonato, numGrupos);
	}

	private int dividirEnGrupos(List<Pareja> list, Campeonato campeonato, int tamGrupo, int tamGrupoAux) {
		int numGrupos = list.size() / tamGrupo;
		if (tamGrupoAux > 0) {
			numGrupos++;
		}
		int i = 1;
		int aux = tamGrupo;
		ParejaCampeonato pj;
		for (Pareja pareja : list) {
			if (aux == 0) {
				i++;
				aux = tamGrupo;
			}
			pj = parejaCampeonatoService.get(new ParejaCampeonatoId(campeonato, pareja));
			pj.setGrupo(i);
			parejaCampeonatoService.save(pj);
			aux--;
		}
		return numGrupos;
	}

	private void sorteoTodosContraTodos(Campeonato campeonato, int numGrupos) {
		for (int i = 1; i <= numGrupos; i++) {
			List<ParejaCampeonato> list = parejaCampeonatoService.findAllByCampeonatoAndGrupo(campeonato, i);
			while (list.size() > 1) {
				for (int j = 1; j < list.size(); j++) {
					Enfrentamiento enfrentamiento = new Enfrentamiento();
					enfrentamiento.setCampeonato(campeonato);
					enfrentamiento.setGrupo(i);
					enfrentamiento.setFase(1);
					enfrentamiento.setReserva(reservaService.save(new Reserva()));
					enfrentamiento.setPareja1(list.get(0).getId().getPareja());
					enfrentamiento.setPareja2(list.get(j).getId().getPareja());
					enfrentamientoService.save(enfrentamiento);
				}
				list.remove(0);
			}
		}
	}

	@Override
	public void CerrarTorneosFueraDePlazo() {
		Calendar c = Calendar.getInstance();
		Date fecha = new java.sql.Date(c.getTimeInMillis());
		List<Campeonato> campeonatos = campeonatoRepository.findAllByLimiteInscripcion(fecha);
		for (Campeonato campeonato : campeonatos) {
			campeonato.setAbierto(false);
			sorteo(parejaCampeonatoService.getParejasByCampeonato(campeonato), campeonato);
		}
	}

	@Override
	public void playoff(Campeonato campeonato) {
		Map<Integer, List<ParejaCampeonato>> map = parejaCampeonatoService.getClasificacionAgrupada(campeonato);
		for (Entry<Integer, List<ParejaCampeonato>> entry : map.entrySet()) {
			List<ParejaCampeonato> list = entry.getValue();
			for (int i = 0; i < 4; i++) {
				Enfrentamiento enfrentamiento = new Enfrentamiento();
				enfrentamiento.setCampeonato(campeonato);
				enfrentamiento.setGrupo(entry.getKey());
				enfrentamiento.setFase(2);
				enfrentamiento.setReserva(reservaService.save(new Reserva()));
				enfrentamiento.setPareja1(list.get(i).getId().getPareja());
				enfrentamiento.setPareja2(list.get(7 - i).getId().getPareja());
				enfrentamientoService.save(enfrentamiento);
			}
		}
	}

	@Override
	public void playoff2(Campeonato campeonato) {
		Map<Integer, List<Enfrentamiento>> map = enfrentamientoService.getEnfrentamientosByFaseAgrupados(campeonato, 2);
		List<Pareja> listParejas;
		for (Entry<Integer, List<Enfrentamiento>> entry : map.entrySet()) {
			listParejas = new ArrayList<>();
			for (Enfrentamiento enfrentamiento : entry.getValue()) {
				listParejas.add(enfrentamiento.getGanador());
			}
			sorteoSemifinal(listParejas, campeonato, entry.getKey());
		}
	}

	private void sorteoSemifinal(List<Pareja> list, Campeonato campeonato, int grupo) {

		int n = (int) (Math.random() * 3) + 1;
		Enfrentamiento enfrentamiento = new Enfrentamiento();
		enfrentamiento.setCampeonato(campeonato);
		enfrentamiento.setGrupo(grupo);
		enfrentamiento.setFase(3);
		enfrentamiento.setReserva(reservaService.save(new Reserva()));
		enfrentamiento.setPareja1(list.get(0));
		enfrentamiento.setPareja2(list.get(n));
		enfrentamientoService.save(enfrentamiento);

		list.remove(n);
		list.remove(0);

		enfrentamiento = new Enfrentamiento();
		enfrentamiento.setCampeonato(campeonato);
		enfrentamiento.setGrupo(grupo);
		enfrentamiento.setFase(3);
		enfrentamiento.setReserva(reservaService.save(new Reserva()));
		enfrentamiento.setPareja1(list.get(0));
		enfrentamiento.setPareja2(list.get(1));
		enfrentamientoService.save(enfrentamiento);
	}

	@Override
	public void playoff3(Campeonato campeonato) {
		Map<Integer, List<Enfrentamiento>> map = enfrentamientoService.getEnfrentamientosByFaseAgrupados(campeonato, 3);
		List<Pareja> listParejas;
		for (Entry<Integer, List<Enfrentamiento>> entry : map.entrySet()) {
			listParejas = new ArrayList<>();
			for (Enfrentamiento enfrentamiento : entry.getValue()) {
				listParejas.add(enfrentamiento.getGanador());
			}
			Enfrentamiento enfrentamiento = new Enfrentamiento();
			enfrentamiento.setCampeonato(campeonato);
			enfrentamiento.setGrupo(entry.getKey());
			enfrentamiento.setFase(4);
			enfrentamiento.setReserva(reservaService.save(new Reserva()));
			enfrentamiento.setPareja1(listParejas.get(0));
			enfrentamiento.setPareja2(listParejas.get(1));
			enfrentamientoService.save(enfrentamiento);
		}
	}

	@Override
	public boolean finalCampeonato(Campeonato campeonato) {
		List<Enfrentamiento> list = enfrentamientoService.getEnfrentamientosByFase(campeonato, 4);
		if (list != null) {
			List<Pareja> listParejas = new ArrayList<>();
			for (Enfrentamiento enfrentamiento : list) {
				listParejas.add(enfrentamiento.getGanador());
			}
			if (listParejas.size() > 1) {
				// enfrentamientoService.deleteAllByCampeonato(campeonato);
				while (listParejas.size() > 1) {
					Enfrentamiento enfrentamiento = new Enfrentamiento();
					enfrentamiento.setCampeonato(campeonato);
					enfrentamiento.setFase(5);
					enfrentamiento.setGrupo(1);
					enfrentamiento.setReserva(reservaService.save(new Reserva()));
					enfrentamiento.setPareja1(listParejas.get(0));
					enfrentamiento.setPareja2(listParejas.get(1));
					enfrentamientoService.save(enfrentamiento);

					listParejas.remove(1);
					listParejas.remove(0);
				}
			}
			return true;
		}
		return false;
	}
}
