package com.Sistema.Hospital.Entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estado_asistencia", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre" }) })
public class EstadoAsistencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer estado_asistencia_id;

	@Column(nullable = false, length = 200)
	private String nombre;
}
