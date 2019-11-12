package com.padelclub.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reserva2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Usuario2 usuario;
	@Column
	private int pista;
	@Column
	private Date fecha;
	@Column
	private int hora;
	@Column
	private boolean disponible;

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario2 getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario2 usuario) {
		this.usuario = usuario;
	}

	public int getPista() {
		return pista;
	}

	public void setPista(int pista) {
		this.pista = pista;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

}