package com.padelclub.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelclub.model.Partido;
import com.padelclub.model.Reserva;

public interface PartidoRepository extends JpaRepository<Partido, Long> {

	List<Partido> findAllByTipo(String string);

	Partido findByReserva(Reserva reserva);

	List<Partido> findAllByAbiertoAndTipo(boolean b, String string);
}
