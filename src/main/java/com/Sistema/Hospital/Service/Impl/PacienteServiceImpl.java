package com.Sistema.Hospital.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.PacienteRequestDto;
import com.Sistema.Hospital.Dto.PacienteResponseDto;
import com.Sistema.Hospital.Entity.Paciente;
import com.Sistema.Hospital.Repository.PacienteRepository;
import com.Sistema.Hospital.Service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	PacienteRepository pacienteRepository;
	ModelMapper mapper;

	public PacienteServiceImpl(PacienteRepository pacienteRepository, ModelMapper mapper) {
		super();
		this.pacienteRepository = pacienteRepository;
		this.mapper = mapper;
	}

	@Override
	public String createPaciente(PacienteRequestDto pacienteRequestDto) {
		try {
			pacienteRepository.save(mapToEntity(pacienteRequestDto));
			return "Paciente creado exitosamente";
		} catch (Exception e) {
			return "Ha sucedido un error: " + e.getMessage();
		}
	}

	@Override
	public PacienteResponseDto getAllPacientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PacienteResponseDto getPacienteById(Integer paciente_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updatePaciente(PacienteRequestDto pacienteRequestDto, Integer paciente_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePacienteById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	// RequestDto to Entity
	private Paciente mapToEntity(PacienteRequestDto pacienteRequestDto) {
		return mapper.map(pacienteRequestDto, Paciente.class);
	}

}
