package com.Sistema.Hospital.Entity;

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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes", uniqueConstraints = { @UniqueConstraint(columnNames = { "dni" }),
		@UniqueConstraint(columnNames = { "email" }), @UniqueConstraint(columnNames = { "telefono" }) })
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paciente_id;

	@Column(nullable = false, length = 200)
	@NotNull
	@NotEmpty
	@Length(min = 1, max = 200)
	private String nombres;

	@Column(nullable = false, length = 200)
	@NotNull
	@NotEmpty
	@Length(min = 1, max = 200)
	private String apellidos;

	@Column(nullable = false, length = 8)
	@NotNull
	@NotEmpty
	@Length(min = 8, max = 8)
	private String dni;

	@Column(nullable = true, length = 200)
	@Length(min = 10, max = 200)
	private String direccion;

	@Column(nullable = true, length = 100)
	@Email
	@Length(min = 1,max = 100)
	private String email;

	@Column(nullable = true, length = 10)
	@Length(min = 9,max = 10)
	private String telefono;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_atencion_id", referencedColumnName = "estado_atencion_id", nullable = false)
	@NotNull
	@NotEmpty
	private EstadoAtencion estadoAtencion;
}
