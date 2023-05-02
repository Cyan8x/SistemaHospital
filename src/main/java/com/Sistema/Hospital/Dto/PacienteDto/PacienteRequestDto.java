package com.Sistema.Hospital.Dto.PacienteDto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequestDto {

	@NotBlank(message = "El campo NOMBRES no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo NOMBRES tiene que contener entre 1 a 100 carácteres.")
	private String nombres;

	@NotBlank(message = "El campo APELLIDOS no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo APELLIDOS tiene que contener entre 1 a 100 carácteres.")
	private String apellidos;

	@NotBlank(message = "El campo DNI no debe estar vacío.")
	@Length(min = 8, max = 8, message = "El campo DNI tiene que contener 8 carácteres.")
	private String dni;

	@Length(min = 10, max = 200, message = "El campo DIRECCIÓN tiene que contener entre 10 a 200 carácteres.")
	private String direccion;

	@Email(message = "El campo EMAIL debe contener un correo electrónico valido.")
	@Length(min = 1, max = 50, message = "El campo EMAIL debe contener entre 1 a 50 carácteres.")
	private String email;

	@Length(min = 9, max = 9, message = "El campo TELÉFONO tiene que contener solo 9 digitos.")
	private String telefono;

	@NotNull
	private Integer estado_atencion_id;
}
