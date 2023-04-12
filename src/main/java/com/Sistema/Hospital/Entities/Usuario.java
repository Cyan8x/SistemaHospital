package com.Sistema.Hospital.Entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios",uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario"}) })
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuario_id;

	@Column(nullable = false, length = 50)
	private String usuario;

	@Column(nullable = false,length = 20)
	private String password;

	@Column(nullable = false,length = 100)
	private String nombres;

	@Column(nullable = false,length = 100)
	private String apellidos;

	private boolean esActivo;

	@ManyToOne
	@JoinColumn(name = "perfil_id", nullable = false)
	private PerfilUsuario perilUsuario;

	private boolean accesoDirecto;

	@Column(nullable = true)
	private LocalDateTime ultimoIngreso;

	@Column(nullable = true, length = 20)
	private String ultimoIp;

	@Column(nullable = true)
	private LocalDateTime anterior_ingreso;

	@Column(nullable = true,length = 50)
	private String color;

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
