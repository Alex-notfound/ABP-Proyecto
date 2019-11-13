package com.padelclub.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelclub.model.Pista;

public interface PistaRepository extends JpaRepository<Pista, Long> {

}
