package com.Sistema.Hospital.Service;

import java.util.List;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteRequestDto;
import com.Sistema.Hospital.Dto.PacienteDto.PacienteResponseDto;

public interface PacienteService {
	SuccesMessageDto createPaciente(PacienteRequestDto pacienteRequestDto);
	List<PacienteResponseDto> getAllPacientes();
	PacienteResponseDto getPacienteById(Integer paciente_id);
	SuccesMessageDto updatePacienteById(PacienteRequestDto pacienteRequestDto, Integer paciente_id);
	SuccesMessageDto deletePacienteById(Integer paciente_id);	
}
