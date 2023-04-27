package com.Sistema.Hospital.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.PacienteDto.PacienteRequestDto;
import com.Sistema.Hospital.Service.PacienteService;

@RestController
@RequestMapping("/hospital/pacientes")
public class PacienteController {
	PacienteService pacienteService;

	public PacienteController(PacienteService pacienteService) {
		super();
		this.pacienteService = pacienteService;
	}

	@PostMapping
	public ResponseEntity<Object> createPaciente(@RequestBody PacienteRequestDto pacienteRequestDto) {

		Map<String, String> respuesta = new HashMap<>();

		try {
			pacienteService.createPaciente(pacienteRequestDto);
			respuesta.put("Message", "Usuario creado exitosamente.");
			respuesta.put("Status", HttpStatus.CREATED.toString());
		} catch (Exception e) {
			respuesta.put("Message", "Ha sucedido un error.");
			respuesta.put("Status", HttpStatus.BAD_REQUEST.toString());
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
}
