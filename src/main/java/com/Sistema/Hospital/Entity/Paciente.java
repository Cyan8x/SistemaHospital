package com.Sistema.Hospital.Entity;

import java.time.LocalDateTime;
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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes", uniqueConstraints = { @UniqueConstraint(columnNames = { "dniPaciente" }),
		@UniqueConstraint(columnNames = { "emailPaciente" }), @UniqueConstraint(columnNames = { "telefonoPaciente" }),
		@UniqueConstraint(columnNames = { "carneExtranjeria" }) })
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paciente_id;

	@Column(nullable = false, length = 100) // insertable = fasle, updatable = false evitar inserccion o actualizacion de este dato
	@NotBlank(message = "El campo NOMBRES no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo NOMBRES tiene que contener entre 1 a 100 carácteres.")
	private String nombresPaciente;

	@Column(nullable = false, length = 100)
	@NotBlank(message = "El campo APELLIDOS no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo APELLIDOS tiene que contener entre 1 a 100 carácteres.")
	private String apellidosPaciente;

	@Column(nullable = false, length = 50)
	@NotBlank(message = "El campo TIPO DOCUMENTO no debe estar vacío.")
	@Length(min = 1, max = 50, message = "El campo TIPO DOCUMENTO tiene que contener entre 1 a 50 carácteres.")
	private String tipoDocumento;

	@Column(nullable = true, length = 8)
	@Length(min = 8, max = 8, message = "El campo DNI tiene que contener solo 8 dígitos.")
	private String dniPaciente;

	@Column(nullable = true, length = 9)
	@Length(min = 9, max = 9, message = "El campo CARNÉ EXTRANJERPÍA tiene que contener solo 9 dígitos.")
	private String carneExtranjeria;

	@Column(nullable = true, length = 200)
	@Length(min = 10, max = 200, message = "El campo DIRECCIÓN tiene que contener entre 10 a 200 carácteres.")
	private String direccionPaciente;

	@Column(nullable = true, length = 50)
	@Email(message = "El campo EMAIL debe contener un correo electrónico valido.")
	@Length(min = 1, max = 50, message = "El campo EMAIL tiene que contener entre 1 a 50 carácteres.")
	private String emailPaciente;

	@Column(nullable = true, length = 9)
	@Length(min = 9, max = 9, message = "El campo TELÉFONO tiene que contener solo 9 dígitos.")
	private String telefonoPaciente;

	@Column(nullable = false)
	@NotNull(message = "Debe completar si está ACTIVO o no el paciente.")
	private Boolean esActivo;

	@Column(nullable = true)
	// @NotNull(message = "Debe completar la FECHA CREACION del paciente.")
	private LocalDateTime fechaCreacionPaciente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "FK_paciente_usuario"))
	@NotNull
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_atencion_id", referencedColumnName = "estado_atencion_id", nullable = false, foreignKey = @ForeignKey(name = "FK_paciente_estadoatencion"))
	@NotNull
	private EstadoAtencion estadoAtencion;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "pacientefavorito_usuario", joinColumns = @JoinColumn(name = "paciente_id", referencedColumnName = "paciente_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id"))
	private List<Usuario> usuariosFavoritos;
	
	@AssertTrue(message = "Debe proporcionar DNI o Carné de Extranjería")
    public boolean isDniOrCarneExtranjeriaProvided() {
        return dniPaciente != null || carneExtranjeria != null;
    }
}
