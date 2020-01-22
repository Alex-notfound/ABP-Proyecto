package com.padelclub.service.api;

import java.util.List;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Partido;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;

public interface PartidoService extends GenericServiceAPI<Partido, Long> {

	List<Partido> getPromocionados();

	List<Partido> getOfertados();

	List<Partido> findAllByUsuario(Usuario usuario);

	boolean existePartido(Reserva reservaGuardada);

	void CerrarPartidosAbiertos();

	void CerrarPartidosAbiertos(Reserva reserva);

	Partido findByReserva(Reserva reserva);
}
