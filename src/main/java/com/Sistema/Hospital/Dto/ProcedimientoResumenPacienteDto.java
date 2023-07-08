package com.Sistema.Hospital.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedimientoResumenPacienteDto {

	private String usuario;

	private String procedimiento;

	private String es_terminado;
	
	private String fecha_inicio;
	
	private String fecha_fin;
}
