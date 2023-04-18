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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Procedimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer procedimiento_id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
	private Usuario usuario;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id", referencedColumnName = "paciente_id", nullable = false)
	private Paciente paciente;

	@Column(nullable = false, length = 1000)
	private String procedimiento;

	@Column(nullable = false)
	private Boolean es_terminado;

	@Column(nullable = false, length = 100)
	private String usuario_creado;
}
