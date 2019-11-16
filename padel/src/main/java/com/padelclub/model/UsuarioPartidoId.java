package com.padelclub.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioPartidoId implements Serializable {

	@ManyToOne
	private Partido partido;
	@ManyToOne
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioPartidoId() {
	}

	public UsuarioPartidoId(Partido partido, Usuario usuario) {
		this.partido = partido;
		this.usuario = usuario;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UsuarioPartidoId))
			return false;
		UsuarioPartidoId that = (UsuarioPartidoId) o;
		return Objects.equals(getPartido(), that.getPartido()) && Objects.equals(getUsuario(), that.getUsuario());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPartido(), getUsuario());
	}
}
