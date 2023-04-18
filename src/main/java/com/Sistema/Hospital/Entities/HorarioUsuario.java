package com.Sistema.Hospital.Entities;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	private boolean esActivoLunes;
	private boolean esActivoMartes;
	private boolean esActivoMiercoles;
	private boolean esActivoJueves;
	private boolean esActivoViernes;
	private boolean esActivoSabado;
	private boolean esActivoDomingo;

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
