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
@Table(name = "estado_atencion", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre" }) })
public class EstadoAtencion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer estado_atencion_id;

	@Column(nullable = false, length = 100)
	@NotNull
	@NotEmpty
	@Length(min = 1, max = 100)
	private String nombre;
}
