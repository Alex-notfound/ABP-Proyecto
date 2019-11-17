package com.padelclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.CampeonatoRepository;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Enfrentamiento;
import com.padelclub.model.Pareja;
import com.padelclub.model.Reserva;
import com.padelclub.service.api.CampeonatoService;
import com.padelclub.service.api.EnfrentamientoService;
import com.padelclub.service.api.ReservaService;

@Service
public class CampeonatoServiceImpl extends GenericServiceImpl<Campeonato, Long> implements CampeonatoService {

	@Autowired
	private CampeonatoRepository campeonatoRepository;
	@Autowired
	private EnfrentamientoService enfrentamientoService;
	@Autowired
	private ReservaService reservaService;

	@Override
	public CrudRepository<Campeonato, Long> getDao() {
		return campeonatoRepository;
	}

	@Override
	public void sorteo(List<Pareja> parejasCampeonato, Campeonato campeonato) {
		sorteoTodosContraTodos(parejasCampeonato, campeonato);
//		sorteoLiga(parejasCampeonato, campeonato);
	}

	private void sorteoLiga(List<Pareja> parejasCampeonato, Campeonato campeonato) {
		while (!parejasCampeonato.isEmpty()) {
			Enfrentamiento enfrentamiento = new Enfrentamiento();
			enfrentamiento.setCampeonato(campeonato);
			int numAleatorio = (int) (Math.random() * (parejasCampeonato.size()) + 1);

			enfrentamiento.setPareja1(parejasCampeonato.get(0));
			enfrentamiento.setPareja2(parejasCampeonato.get(numAleatorio));
			enfrentamientoService.save(enfrentamiento);

			parejasCampeonato.remove(numAleatorio);
			parejasCampeonato.remove(0);
		}
	}

	private void sorteoTodosContraTodos(List<Pareja> parejasCampeonato, Campeonato campeonato) {
		while (parejasCampeonato.size() > 1) {
			for (int i = 1; i < parejasCampeonato.size(); i++) {
				Enfrentamiento enfrentamiento = new Enfrentamiento();
				enfrentamiento.setCampeonato(campeonato);
				enfrentamiento.setReserva(reservaService.save(new Reserva()));
				enfrentamiento.setPareja1(parejasCampeonato.get(0));
				enfrentamiento.setPareja2(parejasCampeonato.get(i));
				enfrentamientoService.save(enfrentamiento);
			}
			parejasCampeonato.remove(0);
		}
	}

	public void sorteoLiga2(List<Pareja> parejasCampeonato) {

		int numParejas = parejasCampeonato.size();
		int rondas = numParejas - numParejas % 2;
		int partidos = numParejas / 2;

		List<Enfrentamiento> enfrentamientos = new ArrayList<>();
		List<Integer> numSorteo = new ArrayList<>();

		boolean esta = false;
		for (int i = 0; i < numParejas; i++) {
			do {
				int aux = (int) (numParejas * Math.random());
				if (numSorteo.contains(aux)) {
					esta = true;
				} else {
					numSorteo.add(aux);
				}
			} while (esta);
		}

	}

	private List<Integer> siguienteJornada(List<Integer> numSorteo) {

		int aux = numSorteo.remove(numSorteo.size() - 1);
		numSorteo.add(0, aux);
		return numSorteo;
	}

	private List<Integer> colocarEnfrentamiento(List<Integer> numSorteo, int partidos) {
		int numPartido[][] = new int[partidos][2];
		int j = 0;
		for (int i = 0; i < partidos; i++) {
			numPartido[i][0] = numSorteo.get(j);
			j++;
			numPartido[i][1] = numSorteo.get(j);
			j++;
		}
		return numSorteo;
	}
}
