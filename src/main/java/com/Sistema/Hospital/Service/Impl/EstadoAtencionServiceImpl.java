package com.Sistema.Hospital.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.EstadoAtencion.EstadoAtencionDto;
import com.Sistema.Hospital.Entity.EstadoAtencion;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Repository.IEstadoAtencionRepository;
import com.Sistema.Hospital.Service.IEstadoAtencionService;

@Service
public class EstadoAtencionServiceImpl extends MAPPERBetweenDtoAndEntity<EstadoAtencionDto, EstadoAtencionDto, EstadoAtencion>
		implements IEstadoAtencionService {

	@Autowired
	IEstadoAtencionRepository iEstadoAtencionRepository;

	@Override
	Class<EstadoAtencion> getTClass() {
		return EstadoAtencion.class;
	}

	@Override
	Class<EstadoAtencionDto> getRSClass() {
		return EstadoAtencionDto.class;
	}
	
	@Override
	Class<EstadoAtencionDto> getRQClass() {
		return getRSClass();
	}

	@Override
	public SuccesMessageDto create(EstadoAtencionDto estadoAtencionDto) {
		iEstadoAtencionRepository.save(mapFromDtoRequestToEntity(estadoAtencionDto));
		return SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date()).message("Estado Atencion creado exitosamente.")
				.build();
	}

	@Override
	public List<EstadoAtencionDto> getAll() {
		return iEstadoAtencionRepository.findAll().stream().map(estAten -> mapFromEntityToDtoResponse(estAten)).collect(Collectors.toList());
	}

	@Override
	public EstadoAtencionDto getById(Integer estado_atencion_id) {
		return mapFromEntityToDtoResponse(iEstadoAtencionRepository.findById(estado_atencion_id)
				.orElseThrow(() -> new ResourceNotFound("Estado Atencion", "id", estado_atencion_id)));
	}

	@Override
	public SuccesMessageDto updateById(EstadoAtencionDto estadoAtencionDto) {
		EstadoAtencion estadoAtencion = mapFromDtoResponseToEntity(getById(estadoAtencionDto.getEstado_atencion_id()));
		mapFromEntityToDtoRequest(estadoAtencionDto, estadoAtencion);
		iEstadoAtencionRepository.save(estadoAtencion);
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date()).message("Estado Atencion actualizado exitosamente.")
				.build();
	}

	@Override
	public SuccesMessageDto deleteById(Integer estado_atencion_id) {
		EstadoAtencion estadoAtencion = mapFromDtoResponseToEntity(getById(estado_atencion_id));
		iEstadoAtencionRepository.delete(estadoAtencion);
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date()).message("Estado Atencion eliminado exitosamente.")
				.build();
	}
}
