package com.Sistema.Hospital.Dto.UsuarioDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class UsuarioRequestDto {

	@NotBlank(message = "El campo USUARIO no debe estar vacío.")
	@Length(min = 3, max = 30, message = "El campo USUARIO tiene que tener contener 3 a 30 carácteres.")
	private String usuario;

	@NotBlank(message = "El campo PASSWORD no debe estar vacío.")
	@Length(min = 8, max = 30, message = "El campo PASSWORD tiene que contener 8 a 30 carácteres.")
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

	@NotNull(message = "El campo PERFIL no debe estar vacío.")
	private Integer perfilUsuarioId;

	@NotNull(message = "El campo HORARIO no debe estar vacío.")
	private Integer horarioUsaurioId;
}
