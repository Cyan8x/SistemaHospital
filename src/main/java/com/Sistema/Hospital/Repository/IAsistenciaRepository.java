package com.Sistema.Hospital.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Sistema.Hospital.Entity.Asistencia;

@Repository
public interface IAsistenciaRepository extends IGENERICRepository<Asistencia, Integer> {

	@Query(value = "select * from asistencia where usuario_id = :usuario_id and fecha_asistencia = :fecha and estado_asistencia_id <> 5", nativeQuery = true)
	Asistencia verificarAsistenciaUsuario(@Param("usuario_id") Integer usuario_id, @Param("fecha") LocalDate fecha);

	@Query(value = "select * from asistencia where usuario_id = :usuario_id", nativeQuery = true)
	List<Asistencia> asistenciasOfUsuario(@Param("usuario_id") Integer usuario_id);

	@Query(value = "SELECT a.fecha_asistencia, a.fecha_hora_asistencia, ea.nombre_estado_asistencia FROM asistencia a inner join estado_asistencia ea on ea.estado_asistencia_id = a.estado_asistencia_id where a.usuario_id = :usuario_id", nativeQuery = true)
	List<Object[]> asistenciasResumenUsuario(@Param("usuario_id") Integer usuario_id);

}
