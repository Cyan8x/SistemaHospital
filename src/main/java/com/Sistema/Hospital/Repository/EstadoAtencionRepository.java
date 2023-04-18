package com.Sistema.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sistema.Hospital.Entity.EstadoAtencion;

@Repository
public interface EstadoAtencionRepository extends JpaRepository<EstadoAtencion, Integer>{

}
