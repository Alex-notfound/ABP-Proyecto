package com.padelclub.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.padelclub.model.ParejaCampeonato;
import com.padelclub.model.ParejaCampeonatoId;

public interface ParejaCampeonatoRepository extends JpaRepository<ParejaCampeonato, ParejaCampeonatoId> {

	@Query(value = "SELECT c.id FROM campeonato c, pareja_campeonato pc WHERE pc.campeonato_id=c.id AND pc.pareja_id = :pareja", nativeQuery = true)
	List<Long> findAllCampeonatosByUsuario(@Param("pareja") Long pareja);

	@Query(value = "SELECT COUNT(*) FROM pareja_campeonato WHERE campeonato_id = :campeonato", nativeQuery = true)
	int countAllByCampeonatoId(@Param("campeonato") Long campeonato);

	@Query(value = "SELECT p.id FROM pareja p, pareja_campeonato pc WHERE pc.campeonato_id= :campeonato AND pc.pareja_id = p.id", nativeQuery = true)
	List<Long> findAllParejasByCampeonato(@Param("campeonato") Long campeonato);

	@Query(value = "SELECT * FROM pareja_campeonato WHERE campeonato_id= :campeonato ORDER BY puntos DESC", nativeQuery = true)
	List<ParejaCampeonato> findAllByCampeonatoOrderByPuntos(@Param("campeonato") Long campeonato);
}
