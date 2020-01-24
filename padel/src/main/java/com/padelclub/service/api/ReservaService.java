package com.padelclub.service.api;

import java.util.List;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;

public interface ReservaService extends GenericServiceAPI<Reserva, Long> {

	public List<Reserva> findAllByUsuario(Usuario usuario);

	public List<Reserva> getAllFromUser(Usuario usuario);

	public int getNumReservasByUsuario(Usuario usuario);

	public Reserva findReservaForToday();

	public Reserva findPistaForReserva(Reserva reserva);

	public void deleteReservasAntiguas();

	public boolean validarReserva(Reserva reserva);

	public Reserva findById(Long id);

	List<ReservaDTO> getReservasDao(Reserva reserva);

}