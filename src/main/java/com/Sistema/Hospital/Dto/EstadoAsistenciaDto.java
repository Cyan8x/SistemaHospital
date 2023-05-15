package com.Sistema.Hospital.Dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoAsistenciaDto {

	private Integer estado_asistencia_id;

	@NotBlank(message = "El campo ESTADO ASISTENCIA no debe estar vacío.")
	@Length(min = 1, max = 50, message = "El campo ESTADO ASISTENCIA tiene que contener entre 1 a 50 carácteres.")
	private String nombreEstadoAsistencia;
}
