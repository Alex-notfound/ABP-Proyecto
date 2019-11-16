package com.padelclub.service.api;

import java.util.List;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Usuario;
import com.padelclub.model.UsuarioCampeonato;
import com.padelclub.model.UsuarioCampeonatoId;

public interface UsuarioCampeonatoService extends GenericServiceAPI<UsuarioCampeonato, UsuarioCampeonatoId> {

	public List<Campeonato> findAllCampeonatosByUsuario(Usuario user);

}
