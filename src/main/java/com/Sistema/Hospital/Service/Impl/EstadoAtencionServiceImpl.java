package com.Sistema.Hospital.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionRequestDto;
import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionResponseDto;
import com.Sistema.Hospital.Entity.EstadoAtencion;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Repository.IEstadoAtencionRepository;
import com.Sistema.Hospital.Service.IEstadoAtencionService;

@Service
public class EstadoAtencionServiceImpl extends MAPPERBetweenDtoAndEntity<EstadoAtencionRequestDto, EstadoAtencionResponseDto, EstadoAtencion>
		implements IEstadoAtencionService {

	@Autowired
	IEstadoAtencionRepository iEstadoAtencionRepository;

	@Override
	Class<EstadoAtencion> getTClass() {
		return EstadoAtencion.class;
	}

	@Override
	Class<EstadoAtencionResponseDto> getRSClass() {
		return EstadoAtencionResponseDto.class;
	}

	@Override
	public SuccesMessageDto create(EstadoAtencionRequestDto estadoAtencionRequestDto) {
		iEstadoAtencionRepository.save(mapFromDtoRequestToEntity(estadoAtencionRequestDto));
		return SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date()).message("Estado Atencion creado exitosamente.")
				.build();
	}

	@Override
	public List<EstadoAtencionResponseDto> getAll() {
		return iEstadoAtencionRepository.findAll().stream().map(estAten -> mapFromEntityToDtoResponse(estAten)).collect(Collectors.toList());
	}

	@Override
	public EstadoAtencionResponseDto getById(Integer estado_atencion_id) {
		return mapFromEntityToDtoResponse(iEstadoAtencionRepository.findById(estado_atencion_id)
				.orElseThrow(() -> new ResourceNotFound("Estado Atencion", "id", estado_atencion_id)));
	}

	@Override
	public SuccesMessageDto updateById(EstadoAtencionRequestDto estadoAtencionRequestDto) {
		EstadoAtencion estadoAtencion = mapFromDtoResponseToEntity(getById(estadoAtencionRequestDto.getEstado_atencion_id()));
		mapFromEntityToDtoRequest(estadoAtencionRequestDto, estadoAtencion);
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
