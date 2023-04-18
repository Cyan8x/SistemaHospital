package com.Sistema.Hospital.Dto;

import com.Sistema.Hospital.Entity.EstadoAtencion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponseDto {

	private Integer paciente_id;
	private String nombres;
	private String apellidos;
	private String dni;
	private EstadoAtencion estadoAtencion;
}
