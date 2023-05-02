package com.Sistema.Hospital.Entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "perfil_usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombrePerfilUsuario" }) })
public class PerfilUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer perfil_id;

	@Column(nullable = false, length = 50)
	@NotBlank(message = "El campo NOMBRE no debe ser vacío.")
	@Length(min = 4, max = 50, message = "El campo NOMBRE tiene que tener de 4 a 50 carácteres.")
	private String nombrePerfilUsuario;

	@Column(nullable = true, length = 200)
	@Length(min = 4, max = 200, message = "El campo DESCRIPCION tiene que tener de 4 a 200 carácteres.")
	private String descripcion;

}
