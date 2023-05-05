package com.Sistema.Hospital.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "perfil_usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombreRol" }) })
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer rol_id;

	@Column(nullable = false, length = 50)
	@NotBlank(message = "El campo NOMBRE DEL ROL no debe ser vacío.")
	@Length(min = 4, max = 50, message = "El campo NOMBRE DEL ROL tiene que tener de 4 a 50 carácteres.")
	private String nombreRol;

	@Column(nullable = true, length = 200)
	@Length(min = 4, max = 200, message = "El campo DESCRIPCION DEL ROL tiene que tener de 4 a 200 carácteres.")
	private String descripcionRol;

}
