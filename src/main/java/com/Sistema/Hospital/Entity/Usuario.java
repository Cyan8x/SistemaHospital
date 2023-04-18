package com.Sistema.Hospital.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
	private String usuario;

	@Column(nullable = false, length = 50)
	private String password;

	@Column(nullable = false, length = 200)
	private String nombres;

	@Column(nullable = false, length = 200)
	private String apellidos;

	@Column(nullable = false, length = 8)
	private String dni;

	@Column(nullable = true, length = 100)
	private String email;

	@Column(nullable = true, length = 10)
	private String telefono;

	private boolean esActivo;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "perfil_id", referencedColumnName = "perfil_id", nullable = false)
	private PerfilUsuario perilUsuario;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "horario_id", referencedColumnName = "horario_id", nullable = false)
	private HorarioUsuario horarioUsuario;
}
