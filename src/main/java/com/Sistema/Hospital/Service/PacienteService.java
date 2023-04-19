package com.Sistema.Hospital.Service;

import java.util.List;

import com.Sistema.Hospital.Dto.PacienteDto.PacienteRequestDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteResponseDto;

public interface PacienteService {
	String createPaciente(PacienteRequestDto pacienteRequestDto);
	List<PacienteResponseDto> getAllPacientes();
	PacienteResponseDto getPacienteById(Integer paciente_id);
	String updatePaciente(PacienteRequestDto pacienteRequestDto, Integer paciente_id);
	String deletePacienteById(Integer id);	
}
