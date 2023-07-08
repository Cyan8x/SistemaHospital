package com.Sistema.Hospital.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JustificacionTardanzaDto {
	@NotNull(message = "El ID del usuario no debe ser nulo.")
	private Integer usuario_id;
	@NotBlank(message = "Tu justificacion no debe estar vacio.")
	private String justificacion;
}
