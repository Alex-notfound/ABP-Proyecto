package com.padelclub.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ParejaCampeonato {

	@EmbeddedId
	private ParejaCampeonatoId id;

	public ParejaCampeonatoId getId() {
		return id;
	}

	public void setId(ParejaCampeonatoId id) {
		this.id = id;
	}

}