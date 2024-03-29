package com.Sistema.Hospital.Dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionDto {
	private Integer notificacion_id;

	@NotNull
	private UsuarioDto usuarioOrigen;

	@NotNull
	private Integer usuarioDestino;

	@NotNull
	private ProcedimientoDto procedimiento;

	@NotBlank(message = "El campo CAUSA no debe estar vacío.")
	@Length(min = 1, max = 500, message = "El campo CAUSA debe contener entre 1 a 500 carácteres.")
	private String causa;

	@NotNull
	private LocalDateTime fechaHoraNotificacion;
}
