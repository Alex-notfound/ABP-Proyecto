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
import com.padelclub.dao.api.ReservaRepository;
import com.padelclub.model.Pista;
import com.padelclub.model.Reserva;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.ReservaDTO;
import com.padelclub.service.api.ReservaService;

@Service
public class ReservaServiceImpl extends GenericServiceImpl<Reserva, Long> implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

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
	public List<Reserva> getAllFromUser(Usuario usuario) {
		return reservaRepository.findAllByUsuario(usuario);
	}

	@Override
	public int getNumReservasByUsuario(Usuario usuario) {
		return reservaRepository.countByUsuario(usuario);
	}

}