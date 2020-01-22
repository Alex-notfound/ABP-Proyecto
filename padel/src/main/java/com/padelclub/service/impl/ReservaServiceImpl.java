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
import com.padelclub.model.Pista;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;
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

	@Override
	public CrudRepository<Reserva, Long> getDao() {
		return reservaRepository;
	}

	@Override
	public List<Reserva> findAllByFecha(Date fecha) {
		return reservaRepository.findAllByFechaOrderByPistaAscHoraAsc(fecha);
	}

	@Override
	public List<Reserva> findAllByUsuario(Usuario usuario) {
		return reservaRepository.findAllByUsuario(usuario);
	}

	@Override
	public Map<Pista, List<ReservaDTO>> getReservasDao(Reserva reserva, List<Pista> pistas) {
		String[] horas = { "9.00", "10.30", "12.00", "13.30", "15.00", "16.30", "18.00", "19.30", "21.00" };
		Map<Pista, List<ReservaDTO>> map = new LinkedHashMap<>();
		for (Pista pista : pistas) {
			map.put(pista, new ArrayList<>());
			for (int j = 0; j < horas.length; j++) {
				if (reservaRepository.findByFechaAndHoraAndPista(reserva.getFecha(), horas[j], pista) != null
						&& !reservaRepository.findByFechaAndHoraAndPista(reserva.getFecha(), horas[j], pista)
								.isDisponible()) {
					map.get(pista).add(new ReservaDTO(pista, reserva.getFecha(), horas[j], false));
				} else {
					map.get(pista).add(new ReservaDTO(pista, reserva.getFecha(), horas[j], true));
				}
			}
		}
		return map;
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
				reserva.setDisponible(false);
				return reserva;
			}
		}
		return null;
	}

	@Override
	public void deleteReservasAntiguas() {
		Calendar fechaActual = Calendar.getInstance();
//		fechaActual.add(Calendar.DAY_OF_MONTH);
		List<Reserva> list = reservaRepository.findOld(new java.sql.Date(fechaActual.getTimeInMillis()));
		for (Reserva reserva : list) {
			if (partidoService.existePartido(reserva)) {
				partidoService.delete(partidoService.findByReserva(reserva).getId());
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