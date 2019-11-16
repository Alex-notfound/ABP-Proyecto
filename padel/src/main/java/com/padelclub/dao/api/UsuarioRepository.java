package com.padelclub.dao.api;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padelclub.model.Usuario2;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario2, Long> {
	
	public Optional<Usuario2> findByDni(String username);

}
