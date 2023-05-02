package com.Sistema.Hospital.Entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HorarioUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer horario_id;

	@Column(nullable = false)
	@NotNull
	private Boolean esActivoLunes;
	@Column(nullable = false)
	@NotNull
	private Boolean esActivoMartes;
	@Column(nullable = false)
	@NotNull
	private Boolean esActivoMiercoles;
	@Column(nullable = false)
	@NotNull
	private Boolean esActivoJueves;
	@Column(nullable = false)
	@NotNull
	private Boolean esActivoViernes;
	@Column(nullable = false)
	@NotNull
	private Boolean esActivoSabado;
	@Column(nullable = false)
	@NotNull
	private Boolean esActivoDomingo;

	@Column(nullable = false)
	@NotNull
	private LocalTime horaInicioLunes;
	@Column(nullable = false)
	@NotNull
	private LocalTime horaFinLunes;

	@Column(nullable = false)
	@NotNull
	private LocalTime horaInicioMartes;
	@Column(nullable = false)
	@NotNull
	private LocalTime horaFinMartes;

	@Column(nullable = false)
	@NotNull
	private LocalTime horaInicioMiercoles;
	@Column(nullable = false)
	@NotNull
	private LocalTime horaFinMiercoles;

	@Column(nullable = false)
	@NotNull
	private LocalTime horaInicioJueves;
	@Column(nullable = false)
	@NotNull
	private LocalTime horaFinJueves;

	@Column(nullable = false)
	@NotNull
	private LocalTime horaInicioViernes;
	@Column(nullable = false)
	@NotNull
	private LocalTime horaFinViernes;

	@Column(nullable = false)
	@NotNull
	private LocalTime horaInicioSabado;
	@Column(nullable = false)
	@NotNull
	private LocalTime horaFinSabado;

	@Column(nullable = false)
	@NotNull
	private LocalTime horaInicioDomingo;
	@Column(nullable = false)
	@NotNull
	private LocalTime horaFinDomingo;
}
