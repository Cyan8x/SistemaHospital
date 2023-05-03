package com.Sistema.Hospital.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteRequestDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteResponseDto;
import com.Sistema.Hospital.Entity.EstadoAtencion;
import com.Sistema.Hospital.Entity.Paciente;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Repository.EstadoAtencionRepository;
import com.Sistema.Hospital.Repository.PacienteRepository;
import com.Sistema.Hospital.Service.PacienteService;
import com.Sistema.Hospital.Service.Impl.Util.MapperBetweenDtoAndEntity;

@Service
public class PacienteServiceImpl
		implements PacienteService, MapperBetweenDtoAndEntity<PacienteResponseDto, Paciente, PacienteRequestDto> {

	// private final ObjectValidator<PacienteRequestDto> pacienteValidator = new
	// ObjectValidator<PacienteRequestDto>();
	@Autowired
	PacienteRepository pacienteRepository;
	@Autowired
	EstadoAtencionRepository estadoAtencionRepository;
	@Autowired
	ModelMapper mapper;

	@Override
	public SuccesMessageDto createPaciente(PacienteRequestDto pacienteRequestDto) {
		// pacienteValidator.validate(pacienteRequestDto);
		EstadoAtencion estadoAtencion = estadoAtencionRepository.findById(pacienteRequestDto.getEstado_atencion_id())
				.orElseThrow(() -> new ResourceNotFound("Estado Atencion", "id",
						pacienteRequestDto.getEstado_atencion_id()));
		Paciente paciente = mapFromDtoRequestToEntity(pacienteRequestDto);
		paciente.setEstadoAtencion(estadoAtencion);
		pacienteRepository.save(paciente);
		return SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Paciente creado exitosamente.").build();
	}

	@Override
	public List<PacienteResponseDto> getAllPacientes() {
		return pacienteRepository.findAll().stream().map(paciente -> mapFromEntityToDtoResponse(paciente))
				.collect(Collectors.toList());
	}

	@Override
	public PacienteResponseDto getPacienteById(Integer paciente_id) {
		return mapFromEntityToDtoResponse(pacienteRepository.findById(paciente_id)
				.orElseThrow(() -> new ResourceNotFound("Paciente", "id", paciente_id)));
	}

	@Override
	public SuccesMessageDto updatePacienteById(PacienteRequestDto pacienteRequestDto, Integer paciente_id) {
		Paciente paciente = mapFromDtoResponseToEntity(getPacienteById(paciente_id));
		EstadoAtencion estadoAtencion = estadoAtencionRepository.findById(pacienteRequestDto.getEstado_atencion_id())
				.orElseThrow(() -> new ResourceNotFound("Estado Atencion", "id",
						pacienteRequestDto.getEstado_atencion_id()));
		mapFromEntityToDtoRequest(pacienteRequestDto, paciente);
		paciente.setEstadoAtencion(estadoAtencion);
		pacienteRepository.save(paciente);
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Paciente actualizado exitosamente.").build();
	}

	@Override
	public SuccesMessageDto deletePacienteById(Integer paciente_id) {
		Paciente paciente = mapFromDtoResponseToEntity(getPacienteById(paciente_id));
		pacienteRepository.delete(paciente);
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Paciente eliminado exitosamente.").build();
	}

	@Override
	public Paciente mapFromDtoRequestToEntity(PacienteRequestDto pacienteRequestDto) {
		return mapper.map(pacienteRequestDto, Paciente.class);
	}

	@Override
	public void mapFromEntityToDtoRequest(PacienteRequestDto pacienteRequestDto, Paciente paciente) {
		mapper.map(pacienteRequestDto, paciente);
	}

	@Override
	public PacienteResponseDto mapFromEntityToDtoResponse(Paciente paciente) {
		return mapper.map(paciente, PacienteResponseDto.class);
	}

	@Override
	public Paciente mapFromDtoResponseToEntity(PacienteResponseDto pacienteResponseDto) {
		return mapper.map(pacienteResponseDto, Paciente.class);
	}
}
