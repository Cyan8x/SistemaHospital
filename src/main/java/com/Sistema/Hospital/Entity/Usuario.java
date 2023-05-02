package com.Sistema.Hospital.Entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario" }),
		@UniqueConstraint(columnNames = { "dniUsuario" }) })

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuario_id;

	@Column(nullable = false, length = 25)
	@NotBlank(message = "El campo USUARIO no debe estar vacío.")
	@Length(min = 3, max = 30, message = "El campo USUARIO tiene que tener contener 3 a 30 carácteres.")
	private String usuario;

	@Column(nullable = false, length = 30)
	@NotBlank(message = "El campo PASSWORD no debe estar vacío.")
	@Length(min = 8, max = 30, message = "El campo PASSWORD tiene que contener 8 a 30 carácteres.")
	private String password;

	@Column(nullable = false, length = 100)
	@NotBlank(message = "El campo NOMBRE no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo NOMBRES tiene que contener de 1 a 100 carácteres.")
	private String nombresUsuario;

	@Column(nullable = false, length = 100)
	@NotBlank(message = "El campo APELLIDOS no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo APELLIDOS tiene que contener de 1 a 100 carácteres")
	private String apellidosUsuario;

	@Column(nullable = false, length = 8)
	@NotBlank(message = "El campo DNI no debe estar vacío.")
	@Length(min = 8, max = 8, message = "El campo DNI tiene que contener solo 8 dígitos.")
	private String dniUsuario;

	@Column(nullable = true, length = 50)
	@Email(message = "El campo EMAIL tiene que contener un correo eléctronico valido.")
	@Length(min = 1, max = 50, message = "El campo EMAIL tiene que contener de 1 a 50 carácteres.")
	private String emailUsuario;

	@Column(nullable = true, length = 10)
	@Length(min = 9, max = 10, message = "El campo TELEFONO tiene que contener solo 9 dígitos.")
	private String telefonoUsuario;

	@Column(nullable = false)
	@NotNull
	private boolean esActivoUsuario;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "perfil_id", referencedColumnName = "perfil_id", nullable = false)
	@NotNull
	private PerfilUsuario perilUsuario;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "horario_id", referencedColumnName = "horario_id", nullable = false)
	@NotNull
	private HorarioUsuario horarioUsuario;
}
