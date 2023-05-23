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

import com.Sistema.Hospital.Dto.AsistenciaDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.Asistencia;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IAsistenciaService;

@RestController
@RequestMapping("/hospital/asistencias")
public class AsistenciaController extends MAPPERBetweenDtoAndEntity<AsistenciaDto, Asistencia>{

	@Autowired
	private IAsistenciaService iAsistenciaService;
	
	@Override
	protected Class<Asistencia> getTClass() {
		return Asistencia.class;
	}

	@Override
	protected Class<AsistenciaDto> getDTOClass() {
		return AsistenciaDto.class;
	}

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createAsistencia(@RequestBody @Valid AsistenciaDto asistenciaDto) throws Exception {
		iAsistenciaService.create(mapFromDtoRequestToEntity(asistenciaDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Asistencia creado exitosamente.").build(), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<AsistenciaDto>> getAllAsistencias() throws Exception {
		List<AsistenciaDto> listaDto = iAsistenciaService.getAll().stream().map(paciente -> mapFromEntityToDto(paciente)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AsistenciaDto> getAsistenciaById(@PathVariable(value = "id") Integer asistencia_id) throws Exception {
		Asistencia paciente = iAsistenciaService.getById(asistencia_id);
		if (paciente == null) {
			throw new ResourceNotFound("Asistencia", "id", asistencia_id);
		}
		return new ResponseEntity<>(mapFromEntityToDto(paciente), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updateAsistenciaById(@RequestBody @Valid AsistenciaDto asistenciaDto) throws Exception {
		Asistencia paciente = iAsistenciaService.getById(asistenciaDto.getAsistencia_id());
		if (paciente == null) {
			throw new ResourceNotFound("Asistencia", "id", asistenciaDto.getAsistencia_id());
		}

		iAsistenciaService.update(mapFromDtoRequestToEntity(asistenciaDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Asistencia actualizado exitosamente.").build(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteAsistenciaById(@PathVariable(value = "id") Integer asistencia_id) throws Exception {
		Asistencia paciente = iAsistenciaService.getById(asistencia_id);
		if (paciente == null) {
			throw new ResourceNotFound("Asistencia", "id", asistencia_id);
		}
		iAsistenciaService.deleteById(asistencia_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Asistencia eliminado exitosamente.").build(), HttpStatus.OK);
	}
	
}
