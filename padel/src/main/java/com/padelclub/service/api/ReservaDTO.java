package com.padelclub.service.api;

import java.io.Serializable;
import java.sql.Date;

import com.padelclub.model.Pista;

public class ReservaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pista pista;
	private Date fecha;
	private String hora;
	private boolean disponible;

	public ReservaDTO(Pista pista, Date fecha, String hora, boolean disponible) {
		this.pista = pista;
		this.fecha = fecha;
		this.hora = hora;
		this.disponible = disponible;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Pista getPista() {
		return pista;
	}

	public void setPista(Pista pista) {
		this.pista = pista;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

}
