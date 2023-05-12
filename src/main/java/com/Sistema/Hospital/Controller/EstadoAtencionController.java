package com.Sistema.Hospital.Controller;

import java.util.List;

import javax.validation.Valid;

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
import com.Sistema.Hospital.Dto.EstadoAtencion.EstadoAtencionDto;
import com.Sistema.Hospital.Service.IEstadoAtencionService;

@RestController
@RequestMapping("/hospital/estadoatencion")
public class EstadoAtencionController {

	@Autowired
	IEstadoAtencionService iEstadoAtencionService;

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createEstadoAtencion(@Valid @RequestBody EstadoAtencionDto estadoAtencionDto) {
		return new ResponseEntity<>(iEstadoAtencionService.create(estadoAtencionDto), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<EstadoAtencionDto>> getAllEstadoAtencion() {
		return new ResponseEntity<>(iEstadoAtencionService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstadoAtencionDto> getEstadoAtencionById(@PathVariable(value = "id") Integer estado_atencion_id) {
		return new ResponseEntity<>(iEstadoAtencionService.getById(estado_atencion_id), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updateEstadoAtencionById(@Valid @RequestBody EstadoAtencionDto estadoAtencionDto) {
		return new ResponseEntity<>(iEstadoAtencionService.updateById(estadoAtencionDto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteEstadoAtencionById(@PathVariable(value = "id") Integer estado_atencion_id) {
		return new ResponseEntity<>(iEstadoAtencionService.deleteById(estado_atencion_id), HttpStatus.OK);
	}
}
