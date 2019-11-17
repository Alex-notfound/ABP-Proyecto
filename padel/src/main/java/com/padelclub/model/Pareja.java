package com.padelclub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pareja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nombre;
	@ManyToOne
	private Usuario miembro1;
	@ManyToOne
	private Usuario miembro2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getMiembro1() {
		return miembro1;
	}

	public void setMiembro1(Usuario miembro1) {
		this.miembro1 = miembro1;
	}

	public Usuario getMiembro2() {
		return miembro2;
	}

	public void setMiembro2(Usuario miembro2) {
		this.miembro2 = miembro2;
	}

}
