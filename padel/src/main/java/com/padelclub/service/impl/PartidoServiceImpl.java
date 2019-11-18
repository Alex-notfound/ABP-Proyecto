package com.padelclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.PartidoRepository;
import com.padelclub.model.Partido;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.PartidoService;

@Service
public class PartidoServiceImpl extends GenericServiceImpl<Partido, Long> implements PartidoService {

	@Autowired
	private PartidoRepository partidoRepository;

	@Override
	public CrudRepository<Partido, Long> getDao() {
		return partidoRepository;
	}

	@Override
	public List<Partido> getPromocionados() {
		return partidoRepository.findAllByTipo("Promocionado");
	}

	@Override
	public List<Partido> getOfertados() {
		return partidoRepository.findAllByTipo("Ofertado");
	}

	@Override
	public List<Partido> findAllByUsuario(Usuario usuario) {
		return partidoRepository.findAllByUsuario(usuario.getId());
	}

	@Override
	public boolean existePartido(Reserva reserva) {
		return partidoRepository.findByReserva(reserva) != null;
	}

	@Override
	public void CerrarPartidosAbiertos() {
		List<Partido> partidos = partidoRepository.findAllByAbiertoAndTipo(true, "Promocionado");
		System.err.println("CERRANDO PARTIDOS: " + partidos.size());
		for (Partido partido : partidos) {
			this.delete(partido.getId());
		}
	}

	@Override
	public void CerrarPartidosAbiertos(Reserva reserva) {
		List<Partido> partidos = partidoRepository.findAllByAbiertoAndTipo(true, "Promocionado");
		for (Partido partido : partidos) {
			Reserva reservaPartido = partido.getReserva();
			if (reservaPartido.getFecha().equals(reserva.getFecha())
					&& reservaPartido.getHora().equals(reserva.getHora())) {
				this.delete(partido.getId());
			}
		}
	}

}
