package com.Sistema.Hospital.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.PacienteDto.PacienteRequestDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteResponseDto;
import com.Sistema.Hospital.Service.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hospital/pacientes")
public class PacienteController {
	@Autowired
	PacienteService pacienteService;

	@PostMapping
	public ResponseEntity<String> createPaciente(@RequestBody @Valid PacienteRequestDto pacienteRequestDto) {

		pacienteService.createPaciente(pacienteRequestDto);
		return new ResponseEntity<>("Se creó paciente", HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<PacienteResponseDto> getPacienteById(@PathVariable(value = "id") Integer paciente_id) {
		return ResponseEntity.ok(pacienteService.getPacienteById(paciente_id));
	}
}
