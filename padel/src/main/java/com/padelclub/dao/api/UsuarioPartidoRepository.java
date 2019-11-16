package com.padelclub.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.padelclub.model.UsuarioPartido;
import com.padelclub.model.UsuarioPartidoId;

public interface UsuarioPartidoRepository extends JpaRepository<UsuarioPartido, UsuarioPartidoId> {

	@Query(value = "DELETE FROM usuario_partido WHERE partido_id = :partido AND usuario_id = :usuario ", nativeQuery = true)
	void deleteByPartidoUsuario(@Param("partido") Long partido, @Param("usuario") Long usuario);

	@Query(value = "SELECT COUNT(*) FROM usuario_partido WHERE partido_id = :partido", nativeQuery = true)
	int countAllByPartidoId(@Param("partido") Long partido);

	@Query(value = "SELECT u.id FROM usuario u, usuario_partido up WHERE up.partido_id = :partido AND up.usuario_id = u.id", nativeQuery = true)
	List<Long> findAllUsuariosByPartidoId(@Param("partido") Long id);

	@Query(value = "SELECT p.id FROM partido p, usuario_partido up WHERE up.usuario_id = :usuario AND up.partido_id = p.id", nativeQuery = true)
	List<Long> findAllByUsuario(@Param("usuario") Long id);

}
