package com.Sistema.Hospital.Entity;

import java.time.LocalDateTime;

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
public class Notificacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer notificacion_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuarioOrigen", referencedColumnName = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "FK_notificacion_usuario"))
	@NotNull
	private Usuario usuarioOrigen;
	
	@Column(nullable = false)
	@NotNull
	private Integer usuarioDestino;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "procedimiento_id", referencedColumnName = "procedimiento_id", nullable = false, foreignKey = @ForeignKey(name = "FK_notificacion_procedimiento"))
	@NotNull
	private Procedimiento procedimiento;
	
	@Column(nullable = false, length = 500)
	@NotBlank(message = "El campo CAUSA no debe estar vacío.")
	@Length(min = 1, max = 100, message = "El campo CAUSA debe contener entre 1 a 100 carácteres.")
	private String causa;

	@Column(nullable = false)
	@NotNull
	private LocalDateTime fechaHoraNotificacion;
}
