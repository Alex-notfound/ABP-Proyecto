package com.padelclub.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class ParejaCampeonato {

	@EmbeddedId
	private ParejaCampeonatoId id;
	@ColumnDefault("0")
	private int puntos;
	@Column
	private int grupo;

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public ParejaCampeonatoId getId() {
		return id;
	}

	public void setId(ParejaCampeonatoId id) {
		this.id = id;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

}