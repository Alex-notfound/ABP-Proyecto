package com.padelclub.service.api;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Campeonato2;
import com.padelclub.model.Pista;
import com.padelclub.model.Reserva2;
import com.padelclub.model.Usuario2;

public interface ReservaService extends GenericServiceAPI<Reserva2, Long> {

	public List<Reserva2> findAllByFecha(Date fecha);

	public List<Reserva2> findAllByUsuario(Usuario2 usuario);

	public Map<Pista, List<ReservaDTO>> getReservasDao(Reserva2 reserva, List<Pista> pistas);

	public List<Reserva2> getAllFromUser(Usuario2 usuario);

}