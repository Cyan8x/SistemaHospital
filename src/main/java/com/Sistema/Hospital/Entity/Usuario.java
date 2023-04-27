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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario" }),
		@UniqueConstraint(columnNames = { "dni" }) })

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuario_id;

	@Column(nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Length(min = 3, max = 50)
	private String usuario;

	@Column(nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Length(min = 8, max = 50)
	private String password;

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

	@Column(nullable = true, length = 100)
	@Email
	@Length(max = 100)
	private String email;

	@Column(nullable = true, length = 10)
	@Length(min = 9, max = 10)
	private String telefono;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private boolean esActivo;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "perfil_id", referencedColumnName = "perfil_id", nullable = false)
	@NotNull
	@NotEmpty
	private PerfilUsuario perilUsuario;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "horario_id", referencedColumnName = "horario_id", nullable = false)
	@NotNull
	@NotEmpty
	private HorarioUsuario horarioUsuario;
}
