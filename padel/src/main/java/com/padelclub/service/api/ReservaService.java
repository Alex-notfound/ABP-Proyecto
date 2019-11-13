package com.padelclub.service.api;

import java.sql.Date;
import java.util.List;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Reserva2;
import com.padelclub.model.Usuario2;

public interface ReservaService extends GenericServiceAPI<Reserva2, Long> {

	public List<Reserva2> findAllByFecha(Date fecha);

	public List<Reserva2> findAllByUsuario(Usuario2 usuario);

	public ReservaDao getReservaDao(Reserva2 reserva);

	public void crearReservasParaDia(Date fecha);

}