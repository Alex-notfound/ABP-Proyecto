package com.padelclub.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioCampeonatoId implements Serializable {

	@ManyToOne
	private Campeonato2 campeonato;
	@ManyToOne
	private Usuario2 usuario;

	public Campeonato2 getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato2 campeonato) {
		this.campeonato = campeonato;
	}

	public Usuario2 getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario2 usuario) {
		this.usuario = usuario;
	}

	public UsuarioCampeonatoId() {
	}

	public UsuarioCampeonatoId(Campeonato2 campeonato, Usuario2 usuario) {
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
