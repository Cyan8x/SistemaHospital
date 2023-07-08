package com.Sistema.Hospital.Entity;

import java.time.LocalDate;
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
	private LocalDate fechaAsistencia;

	@Column(nullable = false)
	@NotNull
	private LocalDateTime fechaHoraAsistencia;

	@Column(nullable = true, length = 500)
	@Length(min = 1, max = 500, message = "El campo JUSTIFICACIÓN tiene que tener de 1 a 500 carácteres.")
	private String justificacionTardanza;
	
	@Column(nullable = true, length = 500)
	@Length(min = 1, max = 500, message = "El campo JUSTIFICACIÓN tiene que tener de 1 a 500 carácteres.")
	private String justificacionFalta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_asistencia_id", referencedColumnName = "estado_asistencia_id", nullable = false, foreignKey = @ForeignKey(name = "FK_asistencia_estadoasistencia"))
	@NotNull
	private EstadoAsistencia estadoAsistencia;
}
