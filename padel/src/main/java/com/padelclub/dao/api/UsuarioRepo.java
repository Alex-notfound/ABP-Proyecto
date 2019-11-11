package com.padelclub.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelclub.model.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

}
