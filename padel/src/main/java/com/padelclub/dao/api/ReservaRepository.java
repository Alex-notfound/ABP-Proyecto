package com.padelclub.dao.api;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.padelclub.model.Pista;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	Reserva findOneById(Long id);

	List<Reserva> findAllByUsuario(Usuario usuario);

	Reserva findByFechaAndHoraAndPista(Date fecha, String hora, Pista pista);

	int countByUsuario(Usuario usuario);

	@Query(value = "SELECT * FROM reserva r WHERE r.fecha < :fechaActual", nativeQuery = true)
	List<Reserva> findOld(@Param("fechaActual") Date fechaActual);

}