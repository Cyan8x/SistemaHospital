package com.Sistema.Hospital.Dto.Rol;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDto {

	private Integer rol_id;

	@NotBlank(message = "El campo NOMBRE DEL ROL no debe ser vacío.")
	@Length(min = 4, max = 50, message = "El campo NOMBRE DEL ROL tiene que tener de 4 a 50 carácteres.")
	private String nombreRol;

	@Length(min = 4, max = 200, message = "El campo DESCRIPCION DEL ROL tiene que tener de 4 a 200 carácteres.")
	private String descripcionRol;
}
