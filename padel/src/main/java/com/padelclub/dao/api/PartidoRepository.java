package com.padelclub.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelclub.model.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Long> {

	List<Partido> findAllByTipo(String string);

}
