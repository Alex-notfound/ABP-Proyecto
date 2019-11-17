package com.padelclub.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelclub.model.Campeonato;
import com.padelclub.model.Enfrentamiento;

public interface EnfrentamientoRepository extends JpaRepository<Enfrentamiento, Long> {

	List<Enfrentamiento> findAllByCampeonato(Campeonato campeonato);

}
