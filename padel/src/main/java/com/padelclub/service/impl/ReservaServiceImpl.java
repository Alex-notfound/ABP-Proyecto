package com.padelclub.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.ReservaRepository;
import com.padelclub.model.Enfrentamiento;
import com.padelclub.model.Pista;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.EnfrentamientoService;
import com.padelclub.service.api.PartidoService;
import com.padelclub.service.api.PistaService;
import com.padelclub.service.api.ReservaDTO;
import com.padelclub.service.api.ReservaService;

@Service
public class ReservaServiceImpl extends GenericServiceImpl<Reserva, Long> implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private PistaService pistaService;
	@Autowired
	private PartidoService partidoService;
	@Autowired
	private EnfrentamientoService enfrentamientoService;

	@Override
	public CrudRepository<Reserva, Long> getDao() {
		return reservaRepository;
	}

	@Override
	public List<Reserva> findAllByUsuario(Usuario usuario) {
		return reservaRepository.findAllByUsuario(usuario);
	}

	@Override
	public List<ReservaDTO> getReservasDao(Reserva reserva) {
		String[] horas = { "9.00", "10.30", "12.00", "13.30", "15.00", "16.30", "18.00", "19.30", "21.00" };
		List<ReservaDTO> list = new ArrayList<>();
		for (int j = 0; j < horas.length; j++) {
			Reserva r = reserva;
			r.setHora(horas[j]);
			r = findPistaForReserva(r);
			if (r != null) {
				list.add(new ReservaDTO(r.getPista(), reserva.getFecha(), horas[j], true));
			} else {
				list.add(new ReservaDTO(null, reserva.getFecha(), horas[j], false));
			}
		}
		return list;
	}

	@Override
	public List<Reserva> getAllFromUser(Usuario usuario) {
		return reservaRepository.findAllByUsuario(usuario);
	}

	@Override
	public int getNumReservasByUsuario(Usuario usuario) {
		return reservaRepository.countByUsuario(usuario);
	}

	@Override
	public Reserva findReservaForToday() {
		Calendar c = Calendar.getInstance();
		Date fecha = new java.sql.Date(c.getTimeInMillis());
		List<Pista> pistas = pistaService.getAll();
		String[] horas = { "9.00", "10.30", "12.00", "13.30", "15.00", "16.30", "18.00", "19.30", "21.00" };
		for (Pista pista : pistas) {
			for (int j = 0; j < horas.length; j++) {
				if (reservaRepository.findByFechaAndHoraAndPista(fecha, horas[j], pista) == null) {
					Reserva reserva = new Reserva();
					reserva.setFecha(fecha);
					reserva.setHora(horas[j]);
					reserva.setPista(pista);
					reserva.setDisponible(true);
					return reserva;
				}
			}
		}
		return null;
	}

	@Override
	public Reserva findPistaForReserva(Reserva reserva) {
		List<Pista> pistas = pistaService.getAll();
		for (Pista pista : pistas) {
			if (reservaRepository.findByFechaAndHoraAndPista(reserva.getFecha(), reserva.getHora(), pista) == null) {
				reserva.setPista(pista);
				return reserva;
			}
		}
		return null;
	}

	@Override
	public void deleteReservasAntiguas() {
		Calendar fechaActual = Calendar.getInstance();
		List<Reserva> list = reservaRepository.findOld(new java.sql.Date(fechaActual.getTimeInMillis()));
		for (Reserva reserva : list) {
			if (partidoService.existePartido(reserva)) {
				partidoService.delete(partidoService.findByReserva(reserva).getId());
			}
			if (enfrentamientoService.existeEnfrentamiento(reserva)) {
				Enfrentamiento enfrentamiento = enfrentamientoService.getByReserva(reserva);
				Reserva r = reservaRepository.save(new Reserva());
				enfrentamiento.setReserva(r);
				enfrentamientoService.save(enfrentamiento);
			}
			reservaRepository.delete(reserva);
		}
	}

	@Override
	public boolean validarReserva(Reserva reserva) {
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.add(Calendar.DAY_OF_MONTH, -1);

		Calendar fechaAfterWeek = Calendar.getInstance();
		fechaAfterWeek.add(Calendar.DAY_OF_MONTH, 7);

		Calendar fechaReserva = Calendar.getInstance();
		fechaReserva.setTime(reserva.getFecha());

		if (fechaReserva.after(fechaActual) && fechaReserva.before(fechaAfterWeek) && reserva.getHora() != null) {
			save(reserva);
			if (findPistaForReserva(reserva) == null) {
				partidoService.CerrarPartidosAbiertos(reserva);
			}
			if (findReservaForToday() == null) {
				partidoService.CerrarPartidosAbiertos();
			}
			return true;
		}
		return false;
	}

	@Override
	public Reserva findById(Long id) {
		return reservaRepository.findOneById(id);
	}

}