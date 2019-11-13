package com.padelclub.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelclub.model.UsuarioCampeonato;
import com.padelclub.model.UsuarioCampeonatoId;

public interface UsuarioCampeonatoRepository extends JpaRepository<UsuarioCampeonato, UsuarioCampeonatoId> {

}
