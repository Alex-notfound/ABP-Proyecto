package com.padelclub.service.api;

import java.security.Principal;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Usuario2;

public interface UsuarioService extends GenericServiceAPI<Usuario2, Long> {

	Usuario2 getUsuario(Principal usuario);

}
