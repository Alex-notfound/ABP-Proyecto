package com.padelclub.dao.api;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelclub.model.Reserva2;

public interface ReservaRepository extends JpaRepository<Reserva2, Long> {

	List<Reserva2> findAllByFecha(Date fecha);

}