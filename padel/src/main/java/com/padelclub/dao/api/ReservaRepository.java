package com.padelclub.dao.api;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelclub.model.Pista;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	List<Reserva> findAllByFechaOrderByPistaAscHoraAsc(Date fecha);

	List<Reserva> findAllByUsuario(Usuario usuario);

	Reserva findByFechaAndHoraAndPista(Date fecha, String hora, Pista pista);

}