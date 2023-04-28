package com.Sistema.Hospital.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.PacienteDto.PacienteRequestDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteResponseDto;
import com.Sistema.Hospital.Entity.EstadoAtencion;
import com.Sistema.Hospital.Entity.Paciente;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Repository.EstadoAtencionRepository;
import com.Sistema.Hospital.Repository.PacienteRepository;
import com.Sistema.Hospital.Service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	PacienteRepository pacienteRepository;
	EstadoAtencionRepository estadoAtencionRepository;
	ModelMapper mapper;

	public PacienteServiceImpl(PacienteRepository pacienteRepository, ModelMapper mapper,
			EstadoAtencionRepository estadoAtencionRepository) {
		super();
		this.pacienteRepository = pacienteRepository;
		this.mapper = mapper;
		this.estadoAtencionRepository = estadoAtencionRepository;
	}

	@Override
	public void createPaciente(PacienteRequestDto pacienteRequestDto) {
		pacienteRepository.save(mapToEntitySinModelMapper(pacienteRequestDto));
	}

	@Override
	public List<PacienteResponseDto> getAllPacientes() {
		return pacienteRepository.findAll().stream().map(paciente -> mapToDto(paciente)).collect(Collectors.toList());
	}

	@Override
	public PacienteResponseDto getPacienteById(Integer paciente_id) {
		return mapToDto(pacienteRepository.findById(paciente_id)
				.orElseThrow(() -> new ResourceNotFound("Paciente", "id", paciente_id)));

	}

	@Override
	public String updatePaciente(PacienteRequestDto pacienteRequestDto, Integer paciente_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePacienteById(Integer id) {
		// TODO Auto-generated method stub
	}

	// RequestDto to Entity
	private Paciente mapToEntity(PacienteRequestDto pacienteRequestDto) {
		return mapper.map(pacienteRequestDto, Paciente.class);
	}

	private Paciente mapToEntitySinModelMapper(PacienteRequestDto pacienteRequestDto) {
		EstadoAtencion estadoAtencion = estadoAtencionRepository.findById(pacienteRequestDto.getEstado_atencion_id())
				.orElseThrow(() -> new ResourceNotFound("Estado Atencion", "id",
						pacienteRequestDto.getEstado_atencion_id()));

		Paciente paciente = new Paciente();
		paciente.setApellidos(pacienteRequestDto.getApellidos());
		paciente.setDireccion(pacienteRequestDto.getDireccion());
		paciente.setDni(pacienteRequestDto.getDni());
		paciente.setEmail(pacienteRequestDto.getEmail());
		paciente.setEstadoAtencion(estadoAtencion);
		paciente.setNombres(pacienteRequestDto.getNombres());
		// paciente.setPaciente_id(pacienteRequestDto.get());
		paciente.setTelefono(pacienteRequestDto.getTelefono());

		return paciente;
	}

	// Entity to ResponseDto
	private PacienteResponseDto mapToDto(Paciente paciente) {
		return mapper.map(paciente, PacienteResponseDto.class);
	}
}
