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
@Table(name = "estado_atencion", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombreEstadoAtencion" }) })
public class EstadoAtencion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer estado_atencion_id;

	@Column(nullable = false, length = 50)
	@NotBlank(message = "El campo NOMBRE no debe estar vacío.")
	@Length(min = 1, max = 50, message = "El campo NOMBRE tiene que contener entre 1 a 50 carácteres.")
	private String nombreEstadoAtencion;
}
