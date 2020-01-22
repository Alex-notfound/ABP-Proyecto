package com.padelclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.NotificacionRepository;
import com.padelclub.model.Enfrentamiento;
import com.padelclub.model.Notificacion;
import com.padelclub.model.Usuario;
import com.padelclub.service.api.NotificacionService;

@Service
public class NotificacionServiceImpl extends GenericServiceImpl<Notificacion, Long> implements NotificacionService {

	@Autowired
	private NotificacionRepository notificacionRepository;

	@Override
	public CrudRepository<Notificacion, Long> getDao() {
		return notificacionRepository;
	}

	@Override
	public List<Notificacion> getAllByCapitanReceptor(Usuario usuario) {
		return notificacionRepository.findAllByCapitanReceptor(usuario);
	}

	@Override
	public void deleteForEnfrentamiento(Enfrentamiento enfrentamiento) {
		notificacionRepository.deleteByEnfrentamiento(enfrentamiento);
	}

}
