package com.padelclub.service.api;

import java.util.List;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Enfrentamiento;
import com.padelclub.model.Notificacion;
import com.padelclub.model.Usuario;

public interface NotificacionService extends GenericServiceAPI<Notificacion, Long> {

	List<Notificacion> getAllByCapitanReceptor(Usuario usuario);

	void deleteForEnfrentamiento(Enfrentamiento enfrentamiento);

}
