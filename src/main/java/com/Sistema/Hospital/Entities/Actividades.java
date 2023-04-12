package com.Sistema.Hospital.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actividades")
public class Actividades {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer actividad_id;

	private String titulo;
	
	private String descripcion;
	
	private String estado;

}
