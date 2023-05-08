package com.Sistema.Hospital.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteRequestDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteResponseDto;
import com.Sistema.Hospital.Entity.EstadoAtencion;
import com.Sistema.Hospital.Entity.Paciente;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Repository.IEstadoAtencionRepository;
import com.Sistema.Hospital.Repository.IPacienteRepository;
import com.Sistema.Hospital.Service.IPacienteService;

@Service
public class PacienteServiceImpl extends MAPPERBetweenDtoAndEntity<PacienteRequestDto, PacienteResponseDto, Paciente> implements IPacienteService {

	@Autowired
	IPacienteRepository iPacienteRepository;

	@Autowired
	IEstadoAtencionRepository iEstadoAtencionRepository;

	@Override
	Class<Paciente> getTClass() {
		return Paciente.class;
	}

	@Override
	Class<PacienteResponseDto> getRSClass() {
		return PacienteResponseDto.class;
	}

	@Override
	public SuccesMessageDto create(PacienteRequestDto pacienteRequestDto) {
		// pacienteValidator.validate(pacienteRequestDto);
		EstadoAtencion estadoAtencion = iEstadoAtencionRepository.findById(pacienteRequestDto.getEstado_atencion_id())
				.orElseThrow(() -> new ResourceNotFound("Estado Atencion", "id", pacienteRequestDto.getEstado_atencion_id()));
		Paciente       paciente       = mapFromDtoRequestToEntity(pacienteRequestDto);
		paciente.setEstadoAtencion(estadoAtencion);
		iPacienteRepository.save(paciente);
		return SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date()).message("Paciente creado exitosamente.")
				.build();
	}

	@Override
	public List<PacienteResponseDto> getAll() {
		return iPacienteRepository.findAll().stream().map(paciente -> mapFromEntityToDtoResponse(paciente)).collect(Collectors.toList());
	}

	@Override
	public PacienteResponseDto getById(Integer paciente_id) {
		return mapFromEntityToDtoResponse(
				iPacienteRepository.findById(paciente_id).orElseThrow(() -> new ResourceNotFound("Paciente", "id", paciente_id)));
	}

	@Override
	public SuccesMessageDto updateById(PacienteRequestDto pacienteRequestDto) {
		Paciente       paciente       = mapFromDtoResponseToEntity(getById(pacienteRequestDto.getPaciente_id()));
		EstadoAtencion estadoAtencion = iEstadoAtencionRepository.findById(pacienteRequestDto.getEstado_atencion_id())
				.orElseThrow(() -> new ResourceNotFound("Estado Atencion", "id", pacienteRequestDto.getEstado_atencion_id()));
		mapFromEntityToDtoRequest(pacienteRequestDto, paciente);
		paciente.setEstadoAtencion(estadoAtencion);
		iPacienteRepository.save(paciente);
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date()).message("Paciente actualizado exitosamente.")
				.build();
	}

	@Override
	public SuccesMessageDto deleteById(Integer paciente_id) {
		Paciente paciente = mapFromDtoResponseToEntity(getById(paciente_id));
		iPacienteRepository.delete(paciente);
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date()).message("Paciente eliminado exitosamente.").build();
	}
}
