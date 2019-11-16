package com.padelclub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Partido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Usuario jugador1;
	@ManyToOne
	private Usuario jugador2;
	@ManyToOne
	private Usuario jugador3;
	@ManyToOne
	private Usuario jugador4;
	@OneToOne
	private Reserva reserva;
	@Column
	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getJugador1() {
		return jugador1;
	}

	public void setJugador1(Usuario jugador1) {
		this.jugador1 = jugador1;
	}

	public Usuario getJugador2() {
		return jugador2;
	}

	public void setJugador2(Usuario jugador2) {
		this.jugador2 = jugador2;
	}

	public Usuario getJugador3() {
		return jugador3;
	}

	public void setJugador3(Usuario jugador3) {
		this.jugador3 = jugador3;
	}

	public Usuario getJugador4() {
		return jugador4;
	}

	public void setJugador4(Usuario jugador4) {
		this.jugador4 = jugador4;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}