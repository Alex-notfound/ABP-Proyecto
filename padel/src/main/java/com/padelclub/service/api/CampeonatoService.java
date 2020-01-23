package com.padelclub.service.api;

import java.util.List;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Pareja;

public interface CampeonatoService extends GenericServiceAPI<Campeonato, Long> {

	void sorteo(List<Pareja> parejasByCampeonato, Campeonato campeonato);

	void CerrarTorneosFueraDePlazo();

	void playoff(Campeonato campeonato);

	void playoff2(Campeonato campeonato);

	void playoff3(Campeonato campeonato);
	
}
