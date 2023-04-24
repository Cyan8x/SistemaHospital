package com.Sistema.Hospital.Service;

import java.util.List;

import com.Sistema.Hospital.Dto.PacienteDto.PacienteRequestDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteResponseDto;

public interface PacienteService {
	void createPaciente(PacienteRequestDto pacienteRequestDto);
	List<PacienteResponseDto> getAllPacientes();
	PacienteResponseDto getPacienteById(Integer paciente_id);
	String updatePaciente(PacienteRequestDto pacienteRequestDto, Integer paciente_id);
	void deletePacienteById(Integer id);	
}
