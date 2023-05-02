package com.Sistema.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sistema.Hospital.Entity.Paciente;

//@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
	
}
