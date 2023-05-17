package com.Sistema.Hospital.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedimientoDto {

	private Integer procedimiento_id;

	@NotNull
	private UsuarioDto usuario;

	@NotNull
	private PacienteDto paciente;

	@NotBlank(message = "El campo PROCEDIMIENTO no tiene que estar vacío.")
	@Length(min = 5, max = 1000, message = "El campo PROCEDIMIENTO tiene que contener de 5 a 1000 carácteres.")
	private String procedimiento;

	private Boolean es_terminado;

	@NotBlank(message = "El campo USUARIO CREADOR no tiene que estar vacío.")
	@Length(min = 3, max = 50)
	private String usuario_creador;
}
