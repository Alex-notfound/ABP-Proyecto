package com.padelclub.service.api;

import java.sql.Date;
import java.util.List;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Reserva2;

public interface ReservaService extends GenericServiceAPI<Reserva2, Long> {

	public List<Reserva2> findByFecha(Date fecha);
}