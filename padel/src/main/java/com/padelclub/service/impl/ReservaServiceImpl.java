package com.padelclub.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.ReservaRepository;
import com.padelclub.model.Reserva2;
import com.padelclub.service.api.ReservaService;

@Service
public class ReservaServiceImpl extends GenericServiceImpl<Reserva2, Long> implements ReservaService {

	@Autowired
	private ReservaRepository reservaDao;

	@Override
	public CrudRepository<Reserva2, Long> getDao() {
		return reservaDao;
	}

	@Override
	public List<Reserva2> findByFecha(Date fecha) {
		return reservaDao.findAllByFecha(fecha);
	}

}