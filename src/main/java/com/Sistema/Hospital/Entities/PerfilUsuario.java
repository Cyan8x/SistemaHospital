package com.Sistema.Hospital.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfiles_usuario",uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"}) })
public class PerfilUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer perfil_id;
	
	@Column(nullable = false, length = 100)
	private String nombre;
	
	private boolean es_activo;
	
	@Column(nullable = true, length = 100)
	private String color;
	
}
