package com.padelclub.service.api;

import java.sql.Date;

import com.padelclub.model.Usuario2;

public class ReservaDao {

	private Long id;
	private Usuario2 usuario;
	private int pista;
	private Date fecha;
	private String hora;
	private boolean disponible;

	public ReservaDao(int pista, Date fecha, String hora, boolean disponible) {
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

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

}
