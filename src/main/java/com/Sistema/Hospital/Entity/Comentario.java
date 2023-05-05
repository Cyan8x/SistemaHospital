package com.Sistema.Hospital.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer comentario_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "FK_comentario_usuario"))
	@NotNull
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id", referencedColumnName = "paciente_id", nullable = false, foreignKey = @ForeignKey(name = "FK_comentario_paciente"))
	@NotNull
	private Paciente paciente;

	@Column(nullable = false, length = 1000)
	@NotBlank(message = "El campo COMENTARIO no debe estar vacío.")
	@Length(min = 1, max = 1000, message = "El campo COMENTARIO debe contener entre 1 a 1000 carácteres.")
	private String comentario;
}
