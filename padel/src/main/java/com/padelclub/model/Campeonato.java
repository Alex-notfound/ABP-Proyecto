package com.padelclub.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Campeonato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String categoria;
	@Column
	private String nivel;
	@Column
	private int maxNumParticipantes;
	@Column
	private double precioInscripcion;
	@Column
	private Date limiteInscripcion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public int getMaxNumParticipantes() {
		return maxNumParticipantes;
	}

	public void setMaxNumParticipantes(int maxNumParticipantes) {
		this.maxNumParticipantes = maxNumParticipantes;
	}

	public double getPrecioInscripcion() {
		return precioInscripcion;
	}

	public void setPrecioInscripcion(double precioInscripcion) {
		this.precioInscripcion = precioInscripcion;
	}

	public Date getLimiteInscripcion() {
		return limiteInscripcion;
	}

	public void setLimiteInscripcion(Date limiteInscripcion) {
		this.limiteInscripcion = limiteInscripcion;
	}

}
