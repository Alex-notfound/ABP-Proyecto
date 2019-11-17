package com.padelclub.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ParejaCampeonatoId implements Serializable {

	@ManyToOne
	private Campeonato campeonato;
	@ManyToOne
	private Pareja pareja;

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Pareja getPareja() {
		return pareja;
	}

	public void setPareja(Pareja pareja) {
		this.pareja = pareja;
	}

	public ParejaCampeonatoId() {
	}

	public ParejaCampeonatoId(Campeonato campeonato, Pareja pareja) {
		this.campeonato = campeonato;
		this.pareja = pareja;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ParejaCampeonatoId))
			return false;
		ParejaCampeonatoId that = (ParejaCampeonatoId) o;
		return Objects.equals(getCampeonato(), that.getCampeonato()) && Objects.equals(getPareja(), that.getPareja());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCampeonato(), getPareja());
	}
}
