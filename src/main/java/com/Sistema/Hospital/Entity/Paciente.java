package com.Sistema.Hospital.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes", uniqueConstraints = { @UniqueConstraint(columnNames = { "dni" }),
		@UniqueConstraint(columnNames = { "email" }), @UniqueConstraint(columnNames = { "telefono" }) })
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paciente_id;

	@Column(nullable = false, length = 50)
	private String nombres;

	@Column(nullable = false, length = 50)
	private String apellidos;

	@Column(nullable = false, length = 8)
	private String dni;

	@Column(nullable = true, length = 200)
	private String direccion;

	@Column(nullable = true, length = 100)
	private String email;

	@Column(nullable = true, length = 10)
	private String telefono;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_atencion_id", referencedColumnName = "estado_atencion_id", nullable = false)
	private EstadoAtencion estadoAtencion;
}
