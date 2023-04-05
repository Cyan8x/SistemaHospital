package com.Sistema.Hospital.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer perfil_id;
	
	@Column(nullable = false, unique = true, length = 100)
	private String nombre;
	
	private boolean es_activo;
	
	@Column(nullable = true, length = 100)
	private String color;
	
}
