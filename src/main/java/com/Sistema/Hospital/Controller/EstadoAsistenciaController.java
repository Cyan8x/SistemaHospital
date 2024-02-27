package com.Sistema.Hospital.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.EstadoAsistenciaDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.EstadoAsistencia;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IEstadoAsistenciaService;

@RestController
@RequestMapping("/hospital/estadoasistencia")
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

	@GetMapping("/pagination")
	public ResponseEntity<Page<EstadoAsistenciaDto>> getAllEstadoAsistencias(@PageableDefault(sort = "nombreEstadoAsistencia")Pageable pageable) throws Exception {
		Page<EstadoAsistenciaDto> listaDto = iEstadoAsistenciaService.getAllPagination(pageable).map(estadoAsistencia -> mapFromEntityToDto(estadoAsistencia));
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
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
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
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
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
