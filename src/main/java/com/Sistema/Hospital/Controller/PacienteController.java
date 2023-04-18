package com.Sistema.Hospital.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.PacienteRequestDto;
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
	public ResponseEntity<String> createPaciente(@RequestBody PacienteRequestDto pacienteRequestDto) {
		return new ResponseEntity<>(pacienteService.createPaciente(pacienteRequestDto), HttpStatus.CREATED);
	}
}
