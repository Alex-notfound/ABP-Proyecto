package com.padelclub.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pista {

	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
