package com.Sistema.Hospital.Dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
	private Integer menu_id;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "El campo ICONO del menu no debe estar vacío.")
	private String iconoMenu;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "El campo NOMBRE del menu no debe estar vacío.")
	private String nombreMenu;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "El campo URL del menu no debe estar vacío.")
	private String urlMenu;
}
