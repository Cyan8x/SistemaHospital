package com.Sistema.Hospital.Entity;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

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

	@Column(nullable = true, length = 9)
	@Length(min = 9, max = 9, message = "El campo TELEFONO tiene que contener solo 9 dígitos.")
	private String telefonoUsuario;

	@Column(nullable = false)
	@Value("${app.defaultvalue.boolean-value}")
	private Boolean esActivoUsuario;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "rol_id"))
	private List<Rol> roles;

	@Column(nullable = false)
	@Value("${app.defaultvalue.boolean-value}")
	private Boolean esActivoLunes;
	@Column(nullable = false)
	@Value("${app.defaultvalue.boolean-value}")
	private Boolean esActivoMartes;
	@Column(nullable = false)
	@Value("${app.defaultvalue.boolean-value}")
	private Boolean esActivoMiercoles;
	@Column(nullable = false)
	@Value("${app.defaultvalue.boolean-value}")
	private Boolean esActivoJueves;
	@Column(nullable = false)
	@Value("${app.defaultvalue.boolean-value}")
	private Boolean esActivoViernes;
	@Column(nullable = false)
	@Value("${app.defaultvalue.boolean-value}")
	private Boolean esActivoSabado;
	@Column(nullable = false)
	@Value("${app.defaultvalue.boolean-value}")
	private Boolean esActivoDomingo;

	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-iniciodia-value}')}")
	private LocalTime horaInicioLunes;
	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-findia-value}')}")
	private LocalTime horaFinLunes;

	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-iniciodia-value}')}")
	private LocalTime horaInicioMartes;
	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-findia-value}')}")
	private LocalTime horaFinMartes;

	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-iniciodia-value}')}")
	private LocalTime horaInicioMiercoles;
	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-findia-value}')}")
	private LocalTime horaFinMiercoles;

	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-iniciodia-value}')}")
	private LocalTime horaInicioJueves;
	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-findia-value}')}")
	private LocalTime horaFinJueves;

	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-iniciodia-value}')}")
	private LocalTime horaInicioViernes;
	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-findia-value}')}")
	private LocalTime horaFinViernes;

	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-iniciodia-value}')}")
	private LocalTime horaInicioSabado;
	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-findia-value}')}")
	private LocalTime horaFinSabado;

	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-iniciodia-value}')}")
	private LocalTime horaInicioDomingo;
	@Column(nullable = false)
	@Value("#{ T(java.time.LocalTime).parse('${app.defaultvalue.localtime-findia-value}')}")
	private LocalTime horaFinDomingo;

}
