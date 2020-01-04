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
	private Pareja pareja1;

	@ManyToOne
	private Pareja pareja2;

	@OneToOne
	private Reserva reserva;

	@ManyToOne
	private Campeonato campeonato;

}
