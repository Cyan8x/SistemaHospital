package com.Sistema.Hospital.Dto;

import java.security.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaDto {

	private Integer asistencia_id;

	@NotNull
	private UsuarioDto usuario;

	@NotNull
	private Date fechaAsistencia;

	@NotNull
	private Timestamp fechaHoraMarcacion;

	@Length(min = 1, max = 1000, message = "El campo JUSTIFICACIÓN tiene que tener de 1 a 1000 carácteres.")
	private String justificacionTardanza;

	@NotNull
	private EstadoAsistenciaDto estadoAsistencia;
}
