package com.Sistema.Hospital.Controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Sistema.Hospital.Dto.EstadoAsistenciaDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.EstadoAsistencia;
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
	public ResponseEntity<SuccesMessageDto> createEstadoAsistencia(@RequestBody @Valid EstadoAsistenciaDto estadoAsistenciaDto) throws Exception {
		iEstadoAsistenciaService.create(mapFromDtoRequestToEntity(estadoAsistenciaDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value())
				.message("El Estado Asistencia fue creado exitosamente.").timestamp(new Date()).build(), HttpStatus.CREATED);
	}

}
