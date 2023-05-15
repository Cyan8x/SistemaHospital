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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.EstadoAtencionDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.EstadoAtencion;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IEstadoAtencionService;

@RestController
@RequestMapping("/hospital/estadoatencion")
public class EstadoAtencionController extends MAPPERBetweenDtoAndEntity<EstadoAtencionDto, EstadoAtencion> {

	@Autowired
	private IEstadoAtencionService iEstadoAtencionService;

	@Override
	protected Class<EstadoAtencion> getTClass() {
		return EstadoAtencion.class;
	}

	@Override
	protected Class<EstadoAtencionDto> getDTOClass() {
		return EstadoAtencionDto.class;
	}

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createEstadoAtencion(@Valid @RequestBody EstadoAtencionDto estadoAtencionDto) throws Exception {
		iEstadoAtencionService.create(mapFromDtoRequestToEntity(estadoAtencionDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Estado Atencion creado exitosamente.").build(), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<EstadoAtencionDto>> getAllEstadoAtencion() throws Exception {
		List<EstadoAtencionDto> listaDto = iEstadoAtencionService.getAll().stream().map(estadoAtencion -> mapFromEntityToDto(estadoAtencion))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstadoAtencionDto> getEstadoAtencionById(@PathVariable(value = "id") Integer estado_atencion_id) throws Exception {
		EstadoAtencion estadoAtencion = iEstadoAtencionService.getById(estado_atencion_id);
		if (estadoAtencion == null) {
			throw new ResourceNotFound("Estado Atencion", "id", estado_atencion_id);
		}
		return new ResponseEntity<>(mapFromEntityToDto(estadoAtencion), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updateEstadoAtencion(@Valid @RequestBody EstadoAtencionDto estadoAtencionDto) throws Exception {
		EstadoAtencion estadoAtencion = iEstadoAtencionService.getById(estadoAtencionDto.getEstado_atencion_id());
		if (estadoAtencion == null) {
			throw new ResourceNotFound("Estado Atencion", "id", estadoAtencionDto.getEstado_atencion_id());
		}
		iEstadoAtencionService.update(mapFromDtoRequestToEntity(estadoAtencionDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Estado Atencion actualizado exitosamente.").build(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteEstadoAtencionById(@PathVariable(value = "id") Integer estado_atencion_id) throws Exception {
		EstadoAtencion estadoAtencion = iEstadoAtencionService.getById(estado_atencion_id);
		if (estadoAtencion == null) {
			throw new ResourceNotFound("Estado Atencion", "id", estado_atencion_id);
		}
		iEstadoAtencionService.deleteById(estado_atencion_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Estado Atencion eliminado exitosamente.").build(), HttpStatus.OK);
	}

}
