package com.Sistema.Hospital.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Sistema.Hospital.Entity.Paciente;

// @Repository
public interface IPacienteRepository extends IGENERICRepository<Paciente, Integer> {

	// SQL SQL Nativo
	// @Transactional
	@Query(value = "select p.* from pacientefavorito_usuario pu inner join pacientes p on p.paciente_id = pu.paciente_id where pu.usuario_id = :usuario_id", nativeQuery = true)
	List<Paciente> selectFavoritosPorUsuario(@Param("usuario_id") Integer usuario_id);
	
	@Query(value = "select * from pacientes where es_activo = true", nativeQuery = true)
	List<Paciente> selectPacientesActivos();

	@Query(value = "select * from pacientes where es_activo = true order by fecha_creacion_paciente desc", nativeQuery = true)
	Page<Paciente> selectPacientesActivosPagination(Pageable pageable);
	
	@Query(value = "select * from pacientes where dni_paciente = CAST(:dni AS varchar) or carne_extranjeria = CAST(:ce AS varchar)", nativeQuery = true)
	List<Paciente> validarExistenciaPacientePorDocumento(@Param("dni") String dni, @Param("ce") String ce);
	
	@Modifying
	@Query(value = "insert into pacientefavorito_usuario(usuario_id, paciente_id) values (:usuario_id,:paciente_id)", nativeQuery = true)
	Integer insertFavoritoPorUsuario(@Param("usuario_id") Integer usuario_id, @Param("paciente_id") Integer paciente_id);
	
	@Modifying
	@Query(value = "delete from pacientefavorito_usuario where paciente_id = :paciente_id and usuario_id = :usuario_id", nativeQuery = true)
	Integer deleteFavoritoPorUsuario(@Param("usuario_id") Integer usuario_id, @Param("paciente_id") Integer paciente_id);
}
