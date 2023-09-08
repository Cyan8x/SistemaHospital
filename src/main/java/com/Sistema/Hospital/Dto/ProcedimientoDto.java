package com.Sistema.Hospital.Dto;

import java.time.LocalDateTime;

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
	@Length(min = 5, max = 300, message = "El campo PROCEDIMIENTO tiene que contener de 5 a 300 carácteres.")
	private String procedimiento;

	private Boolean es_terminado;
	
	@NotNull
	private LocalDateTime fechaCreacionProced;

	@NotBlank(message = "El campo USUARIO CREADOR no tiene que estar vacío.")
	@Length(min = 3, max = 50)
	private String usuario_creador;
	
	@NotNull(message = "El campo FECHA INICIO no tiene que estar vacío.")
	private LocalDateTime fechaHoraInicio;
	
	@NotNull(message = "El campo FECHA FIN no tiene que estar vacío.")
	private LocalDateTime fechaHoraFin;
}
