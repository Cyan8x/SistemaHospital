package com.Sistema.Hospital.Entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "perfil_usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre" }) })
public class PerfilUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer perfil_id;

	@Column(nullable = false, length = 100)
	@NotNull
	@NotEmpty
	@Length(min = 4, max = 100)
	private String nombre;

	@Column(nullable = true, length = 200)
	@Length(min = 4, max = 200)
	private String descripcion;

}
