package com.padelclub.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UsuarioCampeonato {

	@EmbeddedId
	private UsuarioCampeonatoId id;

	public UsuarioCampeonatoId getId() {
		return id;
	}

	public void setId(UsuarioCampeonatoId id) {
		this.id = id;
	}

}