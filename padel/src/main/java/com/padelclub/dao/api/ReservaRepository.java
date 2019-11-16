package com.padelclub.dao.api;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelclub.model.Pista;
import com.padelclub.model.Reserva2;
import com.padelclub.model.Usuario2;

public interface ReservaRepository extends JpaRepository<Reserva2, Long> {

	List<Reserva2> findAllByFechaOrderByPistaAscHoraAsc(Date fecha);

	List<Reserva2> findAllByUsuario(Usuario2 usuario);

	Reserva2 findByFechaAndHoraAndPista(Date fecha, String hora, Pista pista);

}