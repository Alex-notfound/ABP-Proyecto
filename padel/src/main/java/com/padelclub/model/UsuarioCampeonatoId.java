package com.padelclub.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioCampeonatoId implements Serializable {

	@ManyToOne
	private Campeonato campeonato;
	@ManyToOne
	private Usuario usuario;

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioCampeonatoId() {
	}

	public UsuarioCampeonatoId(Campeonato campeonato, Usuario usuario) {
		this.campeonato = campeonato;
		this.usuario = usuario;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UsuarioCampeonatoId))
			return false;
		UsuarioCampeonatoId that = (UsuarioCampeonatoId) o;
		return Objects.equals(getCampeonato(), that.getCampeonato()) && Objects.equals(getUsuario(), that.getUsuario());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCampeonato(), getUsuario());
	}
}
