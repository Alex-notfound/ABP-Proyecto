package com.padelclub.dao.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.padelclub.model.Enfrentamiento;
import com.padelclub.model.Notificacion;
import com.padelclub.model.Usuario;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

	List<Notificacion> findAllByCapitanReceptor(Usuario usuario);

	@Modifying
	@Transactional
	void deleteByEnfrentamiento(Enfrentamiento enfrentamiento);

}
