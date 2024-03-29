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
@Table(name = "menu", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombreMenu" }), @UniqueConstraint(columnNames = { "urlMenu" }) })
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer menu_id;

	@Column(nullable = false, length = 50)
	@NotBlank(message = "El campo ICONO del menu no debe estar vacío.")
	@Length(min = 1, max = 50, message = "El campo ICONO debe tener de 1 a 50 carácteres.")
	private String iconoMenu;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "El campo NOMBRE del menu no debe estar vacío.")
	@Length(min = 1, max = 50, message = "El campo NOMBRE debe tener de 1 a 50 carácteres.")
	private String nombreMenu;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "El campo URL del menu no debe estar vacío.")
	@Length(min = 1, max = 50, message = "El campo URL debe tener de 1 a 50 carácteres.")
	private String urlMenu;
}
