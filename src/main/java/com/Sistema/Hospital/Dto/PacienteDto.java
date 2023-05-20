package com.Sistema.Hospital.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDto {
	
	private Integer paciente_id;

	@NotBlank(message = "El campo NOMBRES no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo NOMBRES tiene que contener entre 1 a 100 carácteres.")
	private String nombresPaciente;

	@NotBlank(message = "El campo APELLIDOS no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo APELLIDOS tiene que contener entre 1 a 100 carácteres.")
	private String apellidosPaciente;

	@NotBlank(message = "El campo DNI no debe estar vacío.")
	@Length(min = 8, max = 8, message = "El campo DNI tiene que contener solo 8 dígitos.")
	private String dniPaciente;

	@Length(min = 10, max = 200, message = "El campo DIRECCIÓN tiene que contener entre 10 a 200 carácteres.")
	private String direccionPaciente;

	@Email(message = "El campo EMAIL debe contener un correo electrónico valido.")
	@Length(min = 1, max = 50, message = "El campo EMAIL debe contener entre 1 a 50 carácteres.")
	private String emailPaciente;

	@Length(min = 9, max = 9, message = "El campo TELÉFONO tiene que contener solo 9 dígitos.")
	private String telefonoPaciente;
	
	@NotNull(message = "Debe completar si está ACTIVO o no el paciente.")
	private Boolean esActivo;

	@NotNull(message = "Debe completar si es FAVORITO o no el paciente.")
	private Boolean esFavorito;

	@NotNull(message = "El campo ESTADO ATENCION del paciente no debe estar vacío.")
	private EstadoAtencionDto estadoAtencion;
}
