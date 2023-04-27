package com.Sistema.Hospital.Entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
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
	@NotEmpty
	private boolean esActivoLunes;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private boolean esActivoMartes;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private boolean esActivoMiercoles;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private boolean esActivoJueves;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private boolean esActivoViernes;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private boolean esActivoSabado;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private boolean esActivoDomingo;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaInicioLunes;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaFinLunes;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaInicioMartes;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaFinMartes;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaInicioMiercoles;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaFinMiercoles;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaInicioJueves;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaFinJueves;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaInicioViernes;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaFinViernes;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaInicioSabado;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaFinSabado;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaInicioDomingo;
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private LocalTime horaFinDomingo;
}
