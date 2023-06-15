package com.Sistema.Hospital.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Sistema.Hospital.Entity.Asistencia;

@Repository
public interface IAsistenciaRepository extends IGENERICRepository<Asistencia, Integer> {

	@Query(value = "select * from asistencia where usuario_id = :usuario_id and fecha_asistencia = :fecha ", nativeQuery = true)
	Asistencia verificarAsistenciaUsuario(@Param("usuario_id") Integer usuario_id,@Param("fecha") LocalDate fecha);
}
