package com.Sistema.Hospital.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteRequestDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteResponseDto;
import com.Sistema.Hospital.Service.PacienteService;


@RestController
@RequestMapping("/hospital/pacientes")
public class PacienteController {
	@Autowired
	PacienteService pacienteService;

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createPaciente(@RequestBody @Valid PacienteRequestDto pacienteRequestDto) {
		return new ResponseEntity<>(pacienteService.createPaciente(pacienteRequestDto), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<PacienteResponseDto>> getAllPacientes() {
		return new ResponseEntity<>(pacienteService.getAllPacientes(), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<PacienteResponseDto> getPacienteById(@PathVariable(value = "id") Integer paciente_id) {
		return new ResponseEntity<>(pacienteService.getPacienteById(paciente_id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> updatePacienteById(@PathVariable(value = "id") Integer paciente_id,
			@RequestBody PacienteRequestDto pacienteRequestDto) {
		return new ResponseEntity<>(pacienteService.updatePacienteById(pacienteRequestDto, paciente_id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deletePacienteById(@PathVariable(value = "id") Integer paciente_id) {
		return new ResponseEntity<>(pacienteService.deletePacienteById(paciente_id), HttpStatus.OK);
	}

}
