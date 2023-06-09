package com.Sistema.Hospital.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private LocalDate fechaAsistencia;
	
	@NotNull
	private LocalDateTime fechaHoraAsistencia;

	@Length(min = 1, max = 500, message = "El campo JUSTIFICACIÓN tiene que tener de 1 a 500 carácteres.")
	private String justificacionTardanza;
	
	@Length(min = 1, max = 500, message = "El campo JUSTIFICACIÓN tiene que tener de 1 a 500 carácteres.")
	private String justificacionFalta;

	@NotNull
	private EstadoAsistenciaDto estadoAsistencia;
}
