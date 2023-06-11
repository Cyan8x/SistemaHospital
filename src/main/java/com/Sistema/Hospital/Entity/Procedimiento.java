package com.Sistema.Hospital.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Procedimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer procedimiento_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "FK_procedimiento_usuario"))
	@NotNull
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id", referencedColumnName = "paciente_id", nullable = false, foreignKey = @ForeignKey(name = "FK_procedimiento_paciente"))
	@NotNull
	private Paciente paciente;

	@Column(nullable = false, length = 300)
	@NotBlank(message = "El campo PROCEDIMIENTO no tiene que estar vacío.")
	@Length(min = 5, max = 300, message = "El campo PROCEDIMIENTO tiene que contener de 5 a 300 carácteres.")
	private String procedimiento;

	@Column(nullable = false)
	private Boolean es_terminado;

	@Column(nullable = false, length = 50)
	@NotBlank(message = "El campo USUARIO CREADOR no tiene que estar vacío.")
	@Length(min = 3, max = 50)
	private String usuario_creador;
	
	@Column(nullable = false)
	@NotNull
	private LocalDateTime fechaHoraInicio;
	
	@Column(nullable = false)
	@NotNull
	private LocalDateTime fechaHoraFin;
}
