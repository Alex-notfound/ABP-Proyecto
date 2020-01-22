package com.padelclub.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Notificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Pareja emisora;

	@ManyToOne
	private Pareja receptora;

	@ManyToOne
	private Usuario capitanReceptor;

	@OneToOne
	private Enfrentamiento enfrentamiento;

	@OneToOne
	private Reserva reserva;

	@ManyToOne
	private Campeonato campeonato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Pareja getEmisora() {
		return emisora;
	}

	public void setEmisora(Pareja emisora) {
		this.emisora = emisora;
	}

	public Pareja getReceptora() {
		return receptora;
	}

	public void setReceptora(Pareja receptora) {
		this.receptora = receptora;
		this.capitanReceptor = receptora.getMiembro1();
	}

	public Usuario getCapitanReceptor() {
		return capitanReceptor;
	}

	public void setCapitanReceptor(Usuario capitanReceptor) {
		this.capitanReceptor = capitanReceptor;
	}

	public Enfrentamiento getEnfrentamiento() {
		return enfrentamiento;
	}

	public void setEnfrentamiento(Enfrentamiento enfrentamiento) {
		this.enfrentamiento = enfrentamiento;
	}

}
