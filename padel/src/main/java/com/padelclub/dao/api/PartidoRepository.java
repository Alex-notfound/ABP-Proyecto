package com.padelclub.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.padelclub.model.Partido;
import com.padelclub.model.Reserva;

public interface PartidoRepository extends JpaRepository<Partido, Long> {

	List<Partido> findAllByTipo(String string);

	@Query(value = "SELECT * FROM partido p WHERE p.jugador1_id like :usuario OR p.jugador2_id like :usuario OR p.jugador3_id like :usuario OR p.jugador4_id like :usuario", nativeQuery = true)
	List<Partido> findAllByUsuario(@Param("usuario") Long usuario);

	Partido findByReserva(Reserva reserva);
}
