package com.Sistema.Hospital.Dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaResumenUsuarioDto {
	
	@NotNull
	private String fecha;
	
	@NotNull
	private String hora_marcacion;
	
	@NotNull
	private String estado;
}
