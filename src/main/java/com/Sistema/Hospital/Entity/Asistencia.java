package com.Sistema.Hospital.Entity;

import java.security.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asistencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer asistencia_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "FK_asistencia_usuario"))
	@NotNull
	private Usuario usuario;

	@Column(nullable = false)
	@NotNull
	private Date fechaAsistencia;

	@Column(nullable = false)
	@NotNull
	private Timestamp fechaHoraMarcacion;

	@Column(nullable = true, length = 1000)
	@Length(min = 1, max = 1000, message = "El campo JUSTIFICACIÓN tiene que tener de 1 a 1000 carácteres.")
	private String justificacionTardanza;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_asistencia_id", referencedColumnName = "estado_asistencia_id", nullable = false, foreignKey = @ForeignKey(name = "FK_asistencia_estadoasistencia"))
	@NotNull
	private EstadoAsistencia estadoAsistencia;
}
