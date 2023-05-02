package com.Sistema.Hospital.Entity;

import java.util.Date;

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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Falta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer falta_id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
	@NotNull
	private Usuario usuario;

	@Column(nullable = false)
	@NotNull
	private Date fechaFalta;

	@Column(length = 1000, nullable = true)
	@Length(min = 1, max = 1000, message = "El campo JUSTIFICACIÓN tiene que tener de 1 a 1000 carácteres.")
	private String justificacionFalta;
}
