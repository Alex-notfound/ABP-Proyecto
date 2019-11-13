package com.padelclub.service.impl;

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
import com.padelclub.model.Reserva2;
import com.padelclub.model.Usuario2;
import com.padelclub.service.api.ReservaDao;
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
	public ReservaDao getReservaDao(Reserva2 reserva) {
//		String[] horas = { "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00", "19.00",
//				"20.00" };
//		Map<Integer, List<ReservaDao>> map = new LinkedHashMap<>();
//		for (int pista = 1; pista <= pistaRepository.count(); pista++) {
//			map.put((Integer) pista, new ArrayList<ReservaDao>());
//			for (int j = 0; j < horas.length; j++) {
//				map.get(pista).add(new ReservaDao(pista, reserva.getFecha(), horas[j], true));
//			}
//		}
//		List<Reserva2> list = findAllByFecha(reserva.getFecha());
//		if(list!=null && !list.isEmpty()) {
//			for (Reserva2 reserva2 : list) {
//				map.get(reserva.getPista()).add
//			}
//		}
		return null;
	}

	@Override
	public void crearReservasParaDia(Date fecha) {
		String[] horas = { "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00", "19.00",
				"20.00" };
		for (int pista = 1; pista <= pistaRepository.count(); pista++) {
			for (int i = 0; i < horas.length; i++) {
				Reserva2 reserva = new Reserva2();
				reserva.setPista(pista);
				reserva.setFecha(fecha);
				reserva.setHora(horas[i]);
				reserva.setDisponible(true);
				reservaRepository.save(reserva);
			}
		}
	}

}