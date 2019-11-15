package com.padelclub.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Enfrentamiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private UsuarioCampeonatoId pareja11;
	@Column
	private UsuarioCampeonatoId  pareja12;
	@Column
	private UsuarioCampeonatoId  pareja21;
	@Column
	private UsuarioCampeonatoId  pareja22;
	@Column
	private String resultado;
	@Column
	private boolean ganador;
	@Column
	private Reserva2 reserva;
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	

	public UsuarioCampeonatoId getPareja11() {
		return pareja11;
	}

	public void setPareja11(UsuarioCampeonatoId pareja11) {
		this.pareja11 = pareja11;
	}
	
	public UsuarioCampeonatoId getPareja12() {
		return pareja12;
	}

	public void setPareja12(UsuarioCampeonatoId pareja12) {
		this.pareja12 = pareja12;
	}
	
	public UsuarioCampeonatoId getPareja21() {
		return pareja21;
	}

	public void setPareja21(UsuarioCampeonatoId pareja21) {
		this.pareja21 = pareja21;
	}
	
	public UsuarioCampeonatoId getPareja22() {
		return pareja22;
	}

	public void setPareja22(UsuarioCampeonatoId pareja22) {
		this.pareja22 = pareja22;
	}
	
	public String getResultado() {
		return this.resultado;
	}
	
	public void setResultado(String resultado) {
		this.resultado=resultado;
	}
	
	//Si es true, ganó PAREJA1 si es false, PAREJA2
	public boolean getGanador() {
		return ganador;
	}
	
	public void setGanador(boolean ganador) {
		this.ganador=ganador;
	}
	
}
