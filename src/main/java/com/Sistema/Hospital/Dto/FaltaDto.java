package com.Sistema.Hospital.Dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaltaDto {

	private Integer falta_id;

	@NotNull(message = "El USUARIO no debe estar vacío.")
	private UsuarioDto usuario;

	@NotNull(message = "La FECHA no debe estar vacío.")
	private Date fechaFalta;

	@Length(min = 1, max = 500, message = "El campo JUSTIFICACIÓN tiene que tener de 1 a 500 carácteres.")
	private String justificacionFalta;
}
