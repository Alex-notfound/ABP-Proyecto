package com.padelclub.service.api;

import java.util.List;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Enfrentamiento;

public interface EnfrentamientoService extends GenericServiceAPI<Enfrentamiento, Long> {

	List<Enfrentamiento> getAllByCampeonato(Campeonato campeonato);

}
