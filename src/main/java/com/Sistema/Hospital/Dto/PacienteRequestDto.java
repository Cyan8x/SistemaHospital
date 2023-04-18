package com.Sistema.Hospital.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequestDto {

	private String nombres;
	private String apellidos;
	private String dni;
	private String direccion;
	private String email;
	private String telefono;
	private Integer estado_atencion_id;
}
