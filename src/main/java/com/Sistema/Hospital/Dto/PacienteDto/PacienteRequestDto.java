package com.Sistema.Hospital.Dto.PacienteDto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequestDto {

	@NotNull
	@NotEmpty
	@Length(min = 1, max = 200)
	private String nombres;
	
	@NotNull
	@NotEmpty
	@Length(min = 1, max = 200)
	private String apellidos;
	
	@NotNull
	@NotEmpty
	@Length(min = 8, max = 8)
	private String dni;
	
	@Length(min = 10, max = 200)
	private String direccion;
	
	@Email
	@Length(min = 1,max = 100)
	private String email;
	
	@Length(min = 9,max = 10)
	private String telefono;
	
	@NotNull
	private Integer estado_atencion_id;
}
