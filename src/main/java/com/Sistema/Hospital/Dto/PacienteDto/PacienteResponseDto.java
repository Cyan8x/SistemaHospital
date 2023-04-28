package com.Sistema.Hospital.Dto.PacienteDto;

import org.hibernate.validator.constraints.Length;

import com.Sistema.Hospital.Entity.EstadoAtencion;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponseDto {

	@NotNull
	@NotEmpty
	private Integer paciente_id;
	
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
	
	@NotNull
	@NotEmpty
	private EstadoAtencion estadoAtencion;
}
