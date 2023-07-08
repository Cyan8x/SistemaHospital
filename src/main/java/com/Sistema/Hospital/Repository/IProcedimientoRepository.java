package com.Sistema.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Sistema.Hospital.Entity.Procedimiento;

public interface IProcedimientoRepository extends IGENERICRepository<Procedimiento, Integer> {

	@Query(value = "SELECT * FROM procedimiento where paciente_id = :idPaciente and es_terminado = false", nativeQuery = true)
	List<Procedimiento> selectProcedimientosPendientesPorPaciente(@Param("idPaciente") Integer idPaciente);

	@Query(value = "SELECT * FROM procedimiento where paciente_id = :idPaciente", nativeQuery = true)
	List<Procedimiento> selectProcedimientosPorPaciente(@Param("idPaciente") Integer idPaciente);

	@Query(value = "SELECT * FROM procedimiento where paciente_id = :idPaciente and es_terminado = true", nativeQuery = true)
	List<Procedimiento> selectProcedimientosTerminadosPorPaciente(@Param("idPaciente") Integer idPaciente);
	
	@Query(value = "SELECT * FROM procedimiento where es_terminado = true and usuario_id = :usuario_id", nativeQuery = true)
	List<Procedimiento> selectProcedimientosTerminadosPorUsuario(@Param("usuario_id") Integer usuario_id);

	@Query(value = "select * from procedimiento where es_terminado = false and usuario_id = :usuario_id", nativeQuery = true)
	List<Procedimiento> selectProcedimientosPendientesPorUsuario(@Param("usuario_id") Integer usuario_id);
}
