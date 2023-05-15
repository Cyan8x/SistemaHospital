package com.Sistema.Hospital.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

import com.Sistema.Hospital.Dto.EstadoAsistenciaDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.EstadoAsistencia;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IEstadoAsistenciaService;

public class EstadoAsistenciaController extends MAPPERBetweenDtoAndEntity<EstadoAsistenciaDto, EstadoAsistencia> {

	@Autowired
	private IEstadoAsistenciaService iEstadoAsistenciaService;

	@Override
	protected Class<EstadoAsistencia> getTClass() {
		return EstadoAsistencia.class;
	}

	@Override
	protected Class<EstadoAsistenciaDto> getDTOClass() {
		return EstadoAsistenciaDto.class;
	}

	@PostMapping()
	public ResponseEntity<SuccesMessageDto> createEstadoAsistencia(
			@RequestBody @Valid EstadoAsistenciaDto estadoAsistenciaDto) throws Exception {
		iEstadoAsistenciaService.create(mapFromDtoRequestToEntity(estadoAsistenciaDto));
		return new ResponseEntity<>(
				SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value())
						.message("El Estado Asistencia fue creado exitosamente.").timestamp(new Date()).build(),
				HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<EstadoAsistenciaDto>> getAllEstadoAsistencias() throws Exception {
		List<EstadoAsistenciaDto> listaDto = iEstadoAsistenciaService.getAll().stream()
				.map(estadoAsistencia -> mapFromEntityToDto(estadoAsistencia)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstadoAsistenciaDto> getEstadoAsistenciaById(
			@PathVariable(value = "id") Integer estado_asistencia_id) throws Exception {
		EstadoAsistencia estadoAsistencia = iEstadoAsistenciaService.getById(estado_asistencia_id);
		if (estadoAsistencia == null) {
			throw new ResourceNotFound("EstadoAsistencia", "id", estado_asistencia_id);
		}
		return new ResponseEntity<>(mapFromEntityToDto(estadoAsistencia), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updateEstadoAsistenciaById(
			@RequestBody @Valid EstadoAsistenciaDto estadoAsistenciaDto) throws Exception {
		EstadoAsistencia estadoAsistencia = iEstadoAsistenciaService
				.getById(estadoAsistenciaDto.getEstado_asistencia_id());
		if (estadoAsistencia == null) {
			throw new ResourceNotFound("EstadoAsistencia", "id", estadoAsistenciaDto.getEstado_asistencia_id());
		}

		iEstadoAsistenciaService.update(mapFromDtoRequestToEntity(estadoAsistenciaDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("EstadoAsistencia actualizado exitosamente.").build(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteEstadoAsistenciaById(
			@PathVariable(value = "id") Integer estado_asistencia_id) throws Exception {
		EstadoAsistencia estadoAsistencia = iEstadoAsistenciaService.getById(estado_asistencia_id);
		if (estadoAsistencia == null) {
			throw new ResourceNotFound("EstadoAsistencia", "id", estado_asistencia_id);
		}
		iEstadoAsistenciaService.deleteById(estado_asistencia_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("EstadoAsistencia eliminado exitosamente.").build(), HttpStatus.OK);
	}

}
