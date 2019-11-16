package com.padelclub.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.padelclub.model.UsuarioCampeonato;
import com.padelclub.model.UsuarioCampeonatoId;

public interface UsuarioCampeonatoRepository extends JpaRepository<UsuarioCampeonato, UsuarioCampeonatoId> {

	@Query(value = "SELECT c.id FROM campeonato c, usuario_campeonato uc WHERE uc.campeonato_id=c.id AND uc.usuario_id = :usuario", nativeQuery = true)
	List<Long> findAllCampeonatosByUsuario(@Param("usuario") Long usuario);

}
