package com.Sistema.Hospital.Dto.EstadoAtencionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoAtencionResponseDto {

	private Integer estado_atencion_id;
	private String nombre;
}
