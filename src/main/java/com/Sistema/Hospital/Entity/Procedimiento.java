package com.Sistema.Hospital.Entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotNull
	private Usuario usuario;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id", referencedColumnName = "paciente_id", nullable = false)
	@NotNull
	private Paciente paciente;

	@Column(nullable = false, length = 1000)
	@NotBlank(message = "El campo PROCEDIMIENTO no tiene que estar vaío.")
	@Length(min = 5, max = 1000, message = "El campo PROCEDIMIENTO tiene que contener de 5 a 1000 carácteres.")
	private String procedimiento;

	@Column(nullable = false)
	@NotNull
	private Boolean es_terminado;

	@Column(nullable = false, length = 50)
	@NotBlank
	@Length(min = 3, max = 50)
	private String usuario_creado;
}
