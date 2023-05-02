package com.Sistema.Hospital.Dto.EstadoAtencionDto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoAtencionRequestDto {

	@NotBlank(message = "El campo NOMBRE no debe estar vacío.")
	@Length(min = 1, max = 50, message = "El campo NOMBRE tiene que contener entre 1 a 50 carácteres.")
	private String nombreEstadoAtencion;
}