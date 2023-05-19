package com.Sistema.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.Sistema.Hospital.Entity.Paciente;

// @Repository
public interface IPacienteRepository extends IGENERICRepository<Paciente, Integer> {

	// SQL SQL Nativo
	// @Transactional
	@Query(value = "SELECT * FROM pacientes where es_activo = true and es_favorito = true", nativeQuery = true)
	List<Paciente> selectFavoritos();
}
