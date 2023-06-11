package com.Sistema.Hospital.Dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDto {

	private Integer comentario_id;

	@NotNull
	private UsuarioDto usuario;

	@NotNull
	private PacienteDto paciente;

	@NotBlank(message = "El campo COMENTARIO no debe estar vacío.")
	@Length(min = 1, max = 500, message = "El campo COMENTARIO debe contener entre 1 a 500 carácteres.")
	private String comentario;
	
	@NotNull
	private LocalDateTime fechaHoraComentario;
}
