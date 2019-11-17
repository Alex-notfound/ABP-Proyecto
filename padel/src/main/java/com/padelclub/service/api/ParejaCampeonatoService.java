package com.padelclub.service.api;

import java.util.List;

import com.padelclub.commons.GenericServiceAPI;
import com.padelclub.model.Campeonato;
import com.padelclub.model.Pareja;
import com.padelclub.model.ParejaCampeonato;
import com.padelclub.model.ParejaCampeonatoId;
import com.padelclub.model.Usuario;

public interface ParejaCampeonatoService extends GenericServiceAPI<ParejaCampeonato, ParejaCampeonatoId> {

	public List<Campeonato> findAllCampeonatosByUsuario(Usuario user);

	public int getNumParticipantesByCampeonato(Campeonato campeonato);

	List<Pareja> getParejasByCampeonato(Campeonato campeonato);

	public List<ParejaCampeonato> getClasificacion(Campeonato campeonato);

}
