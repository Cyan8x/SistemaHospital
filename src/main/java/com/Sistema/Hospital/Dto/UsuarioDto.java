package com.Sistema.Hospital.Dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

	private Integer usuario_id;

	@NotBlank(message = "El campo USUARIO no debe estar vacío.")
	@Length(min = 3, max = 30, message = "El campo USUARIO tiene que tener contener 3 a 30 carácteres.")
	private String usuario;

	@NotBlank(message = "El campo PASSWORD no debe estar vacío.")
	private String password;

	@NotBlank(message = "El campo NOMBRE no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo NOMBRES tiene que contener de 1 a 100 carácteres.")
	private String nombresUsuario;

	@NotBlank(message = "El campo APELLIDOS no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo APELLIDOS tiene que contener de 1 a 100 carácteres")
	private String apellidosUsuario;

	@NotBlank(message = "El campo DNI no debe estar vacío.")
	@Length(min = 8, max = 8, message = "El campo DNI tiene que contener solo 8 dígitos.")
	private String dniUsuario;

	@Email(message = "El campo EMAIL tiene que contener un correo eléctronico valido.")
	@Length(min = 1, max = 50, message = "El campo EMAIL tiene que contener de 1 a 50 carácteres.")
	private String emailUsuario;

	@Length(min = 9, max = 10, message = "El campo TELEFONO tiene que contener solo 9 dígitos.")
	private String telefonoUsuario;

	@NotNull(message = "Debe completar si está activo o no el usuario.")
	private Boolean esActivoUsuario;
	
	@NotNull(message = "Debe completar la FECHA CREACION del usuario.")
	private LocalDateTime fechaCreacionUsuario;
	
	@NotNull
	private RolDto rol;

	private Boolean esActivoLunes = true;
	private Boolean esActivoMartes = true;
	private Boolean esActivoMiercoles = true;
	private Boolean esActivoJueves = true;
	private Boolean esActivoViernes = true;
	private Boolean esActivoSabado = true;
	private Boolean esActivoDomingo = true;

	private LocalTime horaInicioLunes = LocalTime.parse("00:00:00");
	private LocalTime horaFinLunes = LocalTime.parse("23:59:59");

	private LocalTime horaInicioMartes = LocalTime.parse("00:00:00");
	private LocalTime horaFinMartes = LocalTime.parse("23:59:59");

	private LocalTime horaInicioMiercoles = LocalTime.parse("00:00:00");
	private LocalTime horaFinMiercoles = LocalTime.parse("23:59:59");

	private LocalTime horaInicioJueves = LocalTime.parse("00:00:00");
	private LocalTime horaFinJueves = LocalTime.parse("23:59:59");

	private LocalTime horaInicioViernes = LocalTime.parse("00:00:00");
	private LocalTime horaFinViernes = LocalTime.parse("23:59:59");

	private LocalTime horaInicioSabado = LocalTime.parse("00:00:00");
	private LocalTime horaFinSabado = LocalTime.parse("23:59:59");

	private LocalTime horaInicioDomingo = LocalTime.parse("00:00:00");
	private LocalTime horaFinDomingo = LocalTime.parse("23:59:59");
}
