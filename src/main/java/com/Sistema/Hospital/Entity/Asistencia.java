package com.Sistema.Hospital.Entity;

import java.security.Timestamp;
import java.util.Date;

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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
	@NotNull
	@NotEmpty
	private Usuario usuario;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private Date fecha;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private Timestamp fecha_hora_marcacion;

	@Column(nullable = true, length = 1000)
	@NotNull
	@NotEmpty
	@Length(min = 1, max = 1000)
	private String justifi_tardanza;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_asistencia_id", referencedColumnName = "estado_asistencia_id", nullable = false)
	@NotNull
	@NotEmpty
	private EstadoAsistencia estadoAsistencia;
}
