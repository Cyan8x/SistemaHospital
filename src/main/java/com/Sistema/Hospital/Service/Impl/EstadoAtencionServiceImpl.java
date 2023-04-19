package com.Sistema.Hospital.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionRequestDto;
import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionResponseDto;
import com.Sistema.Hospital.Entity.EstadoAtencion;
import com.Sistema.Hospital.Repository.EstadoAtencionRepository;
import com.Sistema.Hospital.Service.EstadoAtencionService;

@Service
public class EstadoAtencionServiceImpl implements EstadoAtencionService {

	EstadoAtencionRepository estadoAtencionRepository;
	ModelMapper mapper;

	public EstadoAtencionServiceImpl(EstadoAtencionRepository estadoAtencionRepository, ModelMapper mapper) {
		super();
		this.estadoAtencionRepository = estadoAtencionRepository;
		this.mapper = mapper;
	}

	@Override
	public String createEstadoAtencion(EstadoAtencionRequestDto estadoAtencionRequestDto) {
		try {
			estadoAtencionRepository.save(mapToEntity(estadoAtencionRequestDto));
			return "El estado de atencion fue creado exitosamente.";
		} catch (Exception e) {
			return "Ha sucedido un error: " + e.getMessage();
		}
	}

	@Override
	public List<EstadoAtencionResponseDto> getAllEstadoAtencion() {
		return estadoAtencionRepository.findAll().stream().map(estAten -> mapToDto(estAten))
				.collect(Collectors.toList());
	}

	@Override
	public EstadoAtencionResponseDto getEstadoAtencionById(Integer estado_atencion_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateEstadoAtencion(EstadoAtencionRequestDto estadoAtencionRequestDto, Integer estado_atencion_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteEstadoAtencionById(Integer estado_atencion_id) {
		// TODO Auto-generated method stub
		return null;
	}

	// DtoRequest a Entity
	private EstadoAtencion mapToEntity(EstadoAtencionRequestDto estadoAtencionRequestDto) {
		return mapper.map(estadoAtencionRequestDto, EstadoAtencion.class);
	}

	// Entity a DtoResponse
	private EstadoAtencionResponseDto mapToDto(EstadoAtencion estadoAtencion) {
		return mapper.map(estadoAtencion, EstadoAtencionResponseDto.class);
	}

}
