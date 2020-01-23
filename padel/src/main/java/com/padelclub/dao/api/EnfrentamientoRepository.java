package com.padelclub.dao.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.padelclub.model.Campeonato;
import com.padelclub.model.Enfrentamiento;
import com.padelclub.model.Reserva;

public interface EnfrentamientoRepository extends JpaRepository<Enfrentamiento, Long> {

	List<Enfrentamiento> findAllByCampeonato(Campeonato campeonato);

	Enfrentamiento findByReserva(Reserva reserva);

	List<Enfrentamiento> findAllByCampeonatoOrderByGrupo(Campeonato campeonato);

	List<Enfrentamiento> findByCampeonatoAndFase(Campeonato campeonato, int fase);

	Enfrentamiento findTopByCampeonatoOrderByFaseDesc(Campeonato campeonato);

	@Modifying
	@Transactional
	void deleteAllByCampeonato(Campeonato campeonato);
}
