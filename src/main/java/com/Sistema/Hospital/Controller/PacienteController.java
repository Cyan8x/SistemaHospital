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
import com.Sistema.Hospital.Dto.Paciente.PacienteRequestDto;
import com.Sistema.Hospital.Dto.Paciente.PacienteResponseDto;
import com.Sistema.Hospital.Service.IPacienteService;


@RestController
@RequestMapping("/hospital/pacientes")
public class PacienteController {
	@Autowired
	IPacienteService iPacienteService;

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createPaciente(@RequestBody @Valid PacienteRequestDto pacienteRequestDto) {
		return new ResponseEntity<>(iPacienteService.create(pacienteRequestDto), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<PacienteResponseDto>> getAllPacientes() {
		return new ResponseEntity<>(iPacienteService.getAll(), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<PacienteResponseDto> getPacienteById(@PathVariable(value = "id") Integer paciente_id) {
		return new ResponseEntity<>(iPacienteService.getById(paciente_id), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updatePacienteById(@RequestBody @Valid PacienteRequestDto pacienteRequestDto) {
		return new ResponseEntity<>(iPacienteService.updateById(pacienteRequestDto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deletePacienteById(@PathVariable(value = "id") Integer paciente_id) {
		return new ResponseEntity<>(iPacienteService.deleteById(paciente_id), HttpStatus.OK);
	}

}
