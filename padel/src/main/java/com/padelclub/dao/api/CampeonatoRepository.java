package com.padelclub.dao.api;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelclub.model.Campeonato;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {

	List<Campeonato> findAllByLimiteInscripcion(Date fecha);

}
