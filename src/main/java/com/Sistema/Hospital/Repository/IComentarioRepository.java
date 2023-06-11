package com.Sistema.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Sistema.Hospital.Entity.Comentario;

public interface IComentarioRepository extends IGENERICRepository<Comentario, Integer>{

	@Query(value = "SELECT * FROM comentario where paciente_id = :idPaciente", nativeQuery = true)
	List<Comentario> selectComentariosPorPaciente(@Param("idPaciente") Integer idPaciente);
}
