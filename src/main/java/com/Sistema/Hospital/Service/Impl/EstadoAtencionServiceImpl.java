package com.Sistema.Hospital.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionRequestDto;
import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionResponseDto;
import com.Sistema.Hospital.Entity.EstadoAtencion;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Repository.EstadoAtencionRepository;
import com.Sistema.Hospital.Service.EstadoAtencionService;
import com.Sistema.Hospital.Service.Impl.Util.MapperBetweenDtoAndEntity;

@Service
public class EstadoAtencionServiceImpl implements EstadoAtencionService,
		MapperBetweenDtoAndEntity<EstadoAtencionResponseDto, EstadoAtencion, EstadoAtencionRequestDto> {

	@Autowired
	EstadoAtencionRepository estadoAtencionRepository;
	@Autowired
	ModelMapper mapper;

	@Override
	public SuccesMessageDto createEstadoAtencion(EstadoAtencionRequestDto estadoAtencionRequestDto) {
		estadoAtencionRepository.save(mapFromDtoRequestToEntity(estadoAtencionRequestDto));
		return SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Estado Atencion creado exitosamente.").build();
	}

	@Override
	public List<EstadoAtencionResponseDto> getAllEstadoAtencion() {
		return estadoAtencionRepository.findAll().stream().map(estAten -> mapFromEntityToDtoResponse(estAten))
				.collect(Collectors.toList());
	}

	@Override
	public EstadoAtencionResponseDto getEstadoAtencionById(Integer estado_atencion_id) {
		return mapFromEntityToDtoResponse(estadoAtencionRepository.findById(estado_atencion_id)
				.orElseThrow(() -> new ResourceNotFound("Estado Atencion", "id", estado_atencion_id)));
	}

	@Override
	public SuccesMessageDto updateEstadoAtencion(EstadoAtencionRequestDto estadoAtencionRequestDto,
			Integer estado_atencion_id) {
		EstadoAtencion estadoAtencion = mapFromDtoResponseToEntity(getEstadoAtencionById(estado_atencion_id));
		mapFromEntityToDtoRequest(estadoAtencionRequestDto, estadoAtencion);
		estadoAtencionRepository.save(estadoAtencion);
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Estado Atencion actualizado exitosamente.").build();
	}

	@Override
	public SuccesMessageDto deleteEstadoAtencionById(Integer estado_atencion_id) {
		EstadoAtencion estadoAtencion = mapFromDtoResponseToEntity(getEstadoAtencionById(estado_atencion_id));
		estadoAtencionRepository.delete(estadoAtencion);
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Estado Atencion eliminado exitosamente.").build();
	}

	// DtoRequest a Entity
	@Override
	public EstadoAtencion mapFromDtoRequestToEntity(EstadoAtencionRequestDto estadoAtencionRequestDto) {
		return mapper.map(estadoAtencionRequestDto, EstadoAtencion.class);
	}

	// Entity a DtoRequest
	@Override
	public void mapFromEntityToDtoRequest(EstadoAtencionRequestDto estadoAtencionRequestDto, EstadoAtencion estadoAtencion) {
		mapper.map(estadoAtencionRequestDto, estadoAtencion);
	}

	// Entity a DtoResponse
	@Override
	public EstadoAtencionResponseDto mapFromEntityToDtoResponse(EstadoAtencion estadoAtencion) {
		return mapper.map(estadoAtencion, EstadoAtencionResponseDto.class);
	}

	// DtoResponse a Entity
	@Override
	public EstadoAtencion mapFromDtoResponseToEntity(EstadoAtencionResponseDto estadoAtencionResponseDto) {
		return mapper.map(estadoAtencionResponseDto, EstadoAtencion.class);
	}

}
