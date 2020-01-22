package com.padelclub.service.api;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Pista;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;

public interface ReservaService extends GenericServiceAPI<Reserva, Long> {

	public List<Reserva> findAllByFecha(Date fecha);

	public List<Reserva> findAllByUsuario(Usuario usuario);

	public Map<Pista, List<ReservaDTO>> getReservasDao(Reserva reserva, List<Pista> pistas);

	public List<Reserva> getAllFromUser(Usuario usuario);

	public int getNumReservasByUsuario(Usuario usuario);

	public Reserva findReservaForToday();

	public Reserva findPistaForReserva(Reserva reserva);

	public void deleteReservasAntiguas();

	public boolean validarReserva(Reserva reserva);

	public Reserva findById(Long id);

}