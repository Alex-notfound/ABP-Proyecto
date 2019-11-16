package com.padelclub.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UsuarioPartido {

	@EmbeddedId
	private UsuarioPartidoId id;

	public UsuarioPartidoId getId() {
		return id;
	}

	public void setId(UsuarioPartidoId id) {
		this.id = id;
	}

}