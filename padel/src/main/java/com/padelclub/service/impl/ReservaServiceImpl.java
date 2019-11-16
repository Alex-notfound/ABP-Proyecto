package com.padelclub.service.impl;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.PistaRepository;
import com.padelclub.dao.api.ReservaRepository;
import com.padelclub.model.Pista;
import com.padelclub.model.Reserva2;
import com.padelclub.model.Usuario2;
import com.padelclub.service.api.ReservaDTO;
import com.padelclub.service.api.ReservaService;

@Service
public class ReservaServiceImpl extends GenericServiceImpl<Reserva2, Long> implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private PistaRepository pistaRepository;

	@Override
	public CrudRepository<Reserva2, Long> getDao() {
		return reservaRepository;
	}

	@Override
	public List<Reserva2> findAllByFecha(Date fecha) {
		return reservaRepository.findAllByFechaOrderByPistaAscHoraAsc(fecha);
	}

	@Override
	public List<Reserva2> findAllByUsuario(Usuario2 usuario) {
		return reservaRepository.findAllByUsuario(usuario);
	}

	@Override
	public Map<Pista, List<ReservaDTO>> getReservasDao(Reserva2 reserva, List<Pista> pistas) {
		String[] horas = { "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00", "19.00",
				"20.00" };
		Map<Pista, List<ReservaDTO>> map = new LinkedHashMap<>();
		for (Pista pista : pistas) {
			map.put(pista, new ArrayList<>());
			for (int j = 0; j < horas.length; j++) {
				if (reservaRepository.findByFechaAndHoraAndPista(reserva.getFecha(), horas[j], pista) != null) {
					map.get(pista).add(new ReservaDTO(pista, reserva.getFecha(), horas[j], false));
				} else {
					map.get(pista).add(new ReservaDTO(pista, reserva.getFecha(), horas[j], true));
				}
			}
		}
		return map;
	}

	@Override
	public List<Reserva2> getAllFromUser(Usuario2 usuario) {
		return reservaRepository.findAllByUsuario(usuario);
	}

}