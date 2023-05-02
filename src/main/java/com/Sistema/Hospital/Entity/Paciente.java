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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes", uniqueConstraints = { @UniqueConstraint(columnNames = { "dniPaciente" }),
		@UniqueConstraint(columnNames = { "emailPaciente" }), @UniqueConstraint(columnNames = { "telefonoPaciente" }) })
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paciente_id;

	@Column(nullable = false, length = 100)
	@NotBlank(message = "El campo NOMBRES no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo NOMBRES tiene que contener entre 1 a 100 carácteres.")
	private String nombresPaciente;

	@Column(nullable = false, length = 100)
	@NotBlank(message = "El campo APELLIDOS no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo APELLIDOS tiene que contener entre 1 a 100 carácteres.")
	private String apellidosPaciente;

	@Column(nullable = false, length = 8)
	@NotBlank(message = "El campo DNI no debe estar vacío.")
	@Length(min = 8, max = 8, message = "El campo DNI tiene que contener solo 8 dígitos.")
	private String dniPaciente;

	@Column(nullable = true, length = 200)
	@Length(min = 10, max = 200, message = "El campo DIRECCIÓN tiene que contener entre 10 a 200 carácteres.")
	private String direccionPaciente;

	@Column(nullable = true, length = 50)
	@Email(message = "El campo EMAIL debe contener un correo electrónico valido.")
	@Length(min = 1, max = 50, message = "El campo EMAIL tiene que contener entre 1 a 50 carácteres.")
	private String emailPaciente;

	@Column(nullable = true, length = 9)
	@Length(min = 9, max = 9, message = "El campo TELÉFONO tiene que contener solo 9 dígitos.")
	private String telefonoPaciente;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_atencion_id", referencedColumnName = "estado_atencion_id", nullable = false)
	@NotNull
	private EstadoAtencion estadoAtencion;
}
