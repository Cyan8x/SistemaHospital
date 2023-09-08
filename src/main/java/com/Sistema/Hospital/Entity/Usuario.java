package com.Sistema.Hospital.Entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario" }), @UniqueConstraint(columnNames = { "dniUsuario" }) })

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer usuario_id;

	@Column(nullable = false, length = 30)
	@NotBlank(message = "El campo USUARIO no debe estar vacío.")
	@Length(min = 3, max = 30, message = "El campo USUARIO tiene que tener contener 3 a 30 carácteres.")
	private String usuario;

	@Column(nullable = false, length = 300)
	@NotBlank(message = "El campo PASSWORD no debe estar vacío.")
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

	@Column(nullable = true, length = 9)
	@Length(min = 9, max = 9, message = "El campo TELEFONO tiene que contener solo 9 dígitos.")
	private String telefonoUsuario;

	@Column(nullable = false)
	@NotNull(message = "Debe completar si está activo o no el usuario.")
	private Boolean esActivoUsuario;

	@Column(nullable = false)
	@NotNull(message = "Debe completar la FECHA CREACION del usuario.")
	private LocalDateTime fechaCreacionUsuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rol_id", referencedColumnName = "rol_id", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_rol"))
	@NotNull
	private Rol rol;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_menu", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "menu_id"))
	private List<Menu> menus;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "usuariosFavoritos")
	private List<Paciente> pacientesFavoritos;

	@Column(nullable = false)
	private Boolean esActivoLunes;
	@Column(nullable = false)
	private Boolean esActivoMartes;
	@Column(nullable = false)
	private Boolean esActivoMiercoles;
	@Column(nullable = false)
	private Boolean esActivoJueves;
	@Column(nullable = false)
	private Boolean esActivoViernes;
	@Column(nullable = false)
	private Boolean esActivoSabado;
	@Column(nullable = false)
	private Boolean esActivoDomingo;

	@Column(nullable = false)
	private LocalTime horaInicioLunes;
	@Column(nullable = false)
	private LocalTime horaFinLunes;

	@Column(nullable = false)
	private LocalTime horaInicioMartes;
	@Column(nullable = false)
	private LocalTime horaFinMartes;

	@Column(nullable = false)
	private LocalTime horaInicioMiercoles;
	@Column(nullable = false)
	private LocalTime horaFinMiercoles;

	@Column(nullable = false)
	private LocalTime horaInicioJueves;
	@Column(nullable = false)
	private LocalTime horaFinJueves;

	@Column(nullable = false)
	private LocalTime horaInicioViernes;
	@Column(nullable = false)
	private LocalTime horaFinViernes;

	@Column(nullable = false)
	private LocalTime horaInicioSabado;
	@Column(nullable = false)
	private LocalTime horaFinSabado;

	@Column(nullable = false)
	private LocalTime horaInicioDomingo;
	@Column(nullable = false)
	private LocalTime horaFinDomingo;

}
