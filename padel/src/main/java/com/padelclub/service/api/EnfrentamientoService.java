package com.padelclub.service.api;

import java.util.List;
import java.util.Map;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Enfrentamiento;
import com.padelclub.model.Reserva;

public interface EnfrentamientoService extends GenericServiceAPI<Enfrentamiento, Long> {

	List<Enfrentamiento> getAllByCampeonato(Campeonato campeonato);

	Campeonato getCampeonatoByReserva(Reserva reservaGuardada);

	Enfrentamiento getByReserva(Reserva reserva);

	List<Enfrentamiento> getAllByCampeonatoOrdererByGroup(Campeonato campeonato);

	Map<Integer, List<Enfrentamiento>> getEnfrentamientosByFaseAgrupados(Campeonato campeonato, int fase);

	boolean faseRecienFinalizada(Campeonato campeonato, int fase);

	List<Enfrentamiento> getEnfrentamientosByFase(Campeonato campeonato, int i);


}
