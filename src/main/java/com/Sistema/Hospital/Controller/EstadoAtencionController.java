package com.Sistema.Hospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionRequestDto;
import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionResponseDto;
import com.Sistema.Hospital.Service.EstadoAtencionService;

@RestController
@RequestMapping("/hospital/estadoatencion")
public class EstadoAtencionController {

	@Autowired
	EstadoAtencionService estadoAtencionService;

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createEstadoAtencion(
			@RequestBody EstadoAtencionRequestDto estadoAtencionRequestDto) {
		return new ResponseEntity<>(estadoAtencionService.createEstadoAtencion(estadoAtencionRequestDto),
				HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<EstadoAtencionResponseDto>> getAllEstadoAtencion() {
		return new ResponseEntity<>(estadoAtencionService.getAllEstadoAtencion(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstadoAtencionResponseDto> getEstadoAtencionById(
			@PathVariable(value = "id") Integer estado_atencion_id) {
		return new ResponseEntity<>(estadoAtencionService.getEstadoAtencionById(estado_atencion_id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> updateEstadoAtencionById(
			@PathVariable(value = "id") Integer estado_atencion_id,
			@RequestBody EstadoAtencionRequestDto estadoAtencionRequestDto) {
		return new ResponseEntity<>(
				estadoAtencionService.updateEstadoAtencion(estadoAtencionRequestDto, estado_atencion_id),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteEstadoAtencionById(
			@PathVariable(value = "id") Integer estado_atencion_id) {
		return new ResponseEntity<>(estadoAtencionService.deleteEstadoAtencionById(estado_atencion_id), HttpStatus.OK);
	}
}
