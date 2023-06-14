package com.Sistema.Hospital.Repository;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import com.Sistema.Hospital.Entity.Asistencia;
import com.Sistema.Hospital.Entity.Usuario;

@Repository
public interface IAsistenciaRepository extends IGENERICRepository<Asistencia, Integer>{
	
	boolean existsByUsuarioAndFecha(Usuario usuario, LocalDate fecha);
}
