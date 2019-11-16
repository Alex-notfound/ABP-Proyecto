package com.padelclub.service.api;

import java.util.List;
import java.util.Map;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Partido;
import com.padelclub.model.Usuario;
import com.padelclub.model.UsuarioPartido;
import com.padelclub.model.UsuarioPartidoId;

public interface UsuarioPartidoService extends GenericServiceAPI<UsuarioPartido, UsuarioPartidoId> {

	public void deleteUserFromPartido(Partido partido, Usuario usuario);

	public int getNumJugadoresPartido(Partido partido);

	public Map<Partido, List<Usuario>> getListado(List<Partido> all);

	public List<Partido> getPartidosByUsuario(Usuario usuario);
}
