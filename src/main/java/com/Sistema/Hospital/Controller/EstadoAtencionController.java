package com.Sistema.Hospital.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionRequestDto;
import com.Sistema.Hospital.Service.EstadoAtencionService;

@RestController
@RequestMapping("/hospital/estadoatencion")
public class EstadoAtencionController {

	EstadoAtencionService estadoAtencionService;

	public EstadoAtencionController(EstadoAtencionService estadoAtencionService) {
		super();
		this.estadoAtencionService = estadoAtencionService;
	}
	
	@PostMapping
	public ResponseEntity<String> createEstadoAtencion(@RequestBody EstadoAtencionRequestDto estadoAtencionRequestDto) {
		return new ResponseEntity<>(estadoAtencionService.createEstadoAtencion(estadoAtencionRequestDto), HttpStatus.CREATED);
	}
}
