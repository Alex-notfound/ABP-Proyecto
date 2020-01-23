package com.padelclub.service.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

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
				tamanoGrupoAux = 0;
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
		int numGrupos = dividirEnGrupos(parejasCampeonato, campeonato, tamanoGrupo, tamanoGrupoAux);
		sorteoTodosContraTodos(campeonato, numGrupos);
	}

	private int dividirEnGrupos(List<Pareja> list, Campeonato campeonato, int tamGrupo, int tamGrupoAux) {
		int numGrupos = list.size() / tamGrupo;
		if (tamGrupoAux != -1) {
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
		for (int i = 1; i < numGrupos; i++) {
			List<ParejaCampeonato> list = parejaCampeonatoService.findAllByCampeonatoAndGrupo(campeonato, i);
			while (list.size() > 1) {
				for (int j = 1; j < list.size(); j++) {
					Enfrentamiento enfrentamiento = new Enfrentamiento();
					enfrentamiento.setCampeonato(campeonato);
					enfrentamiento.setGrupo(i);
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

//	private void sorteoLiga(List<Pareja> parejasCampeonato, Campeonato campeonato) {
//		while (!parejasCampeonato.isEmpty()) {
//			Enfrentamiento enfrentamiento = new Enfrentamiento();
//			enfrentamiento.setCampeonato(campeonato);
//			int numAleatorio = (int) (Math.random() * (parejasCampeonato.size()) + 1);
//
//			enfrentamiento.setPareja1(parejasCampeonato.get(0));
//			enfrentamiento.setPareja2(parejasCampeonato.get(numAleatorio));
//			enfrentamientoService.save(enfrentamiento);
//
//			parejasCampeonato.remove(numAleatorio);
//			parejasCampeonato.remove(0);
//		}
//	}
//	
//	public void sorteoLiga2(List<Pareja> parejasCampeonato) {
//
//		int numParejas = parejasCampeonato.size();
//		int rondas = numParejas - numParejas % 2;
//		int partidos = numParejas / 2;
//
//		List<Enfrentamiento> enfrentamientos = new ArrayList<>();
//		List<Integer> numSorteo = new ArrayList<>();
//
//		boolean esta = false;
//		for (int i = 0; i < numParejas; i++) {
//			do {
//				int aux = (int) (numParejas * Math.random());
//				if (numSorteo.contains(aux)) {
//					esta = true;
//				} else {
//					numSorteo.add(aux);
//				}
//			} while (esta);
//		}
//
//	}
//
//	private List<Integer> siguienteJornada(List<Integer> numSorteo) {
//
//		int aux = numSorteo.remove(numSorteo.size() - 1);
//		numSorteo.add(0, aux);
//		return numSorteo;
//	}
//
//	private List<Integer> colocarEnfrentamiento(List<Integer> numSorteo, int partidos) {
//		int numPartido[][] = new int[partidos][2];
//		int j = 0;
//		for (int i = 0; i < partidos; i++) {
//			numPartido[i][0] = numSorteo.get(j);
//			j++;
//			numPartido[i][1] = numSorteo.get(j);
//			j++;
//		}
//		return numSorteo;
//	}
}
