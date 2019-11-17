package com.padelclub.service.api;

import java.security.Principal;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Usuario;

public interface UsuarioService extends GenericServiceAPI<Usuario, Long> {

	Usuario getUsuario(Principal usuario);

	Usuario getUsuarioByDni(String dni);

}
