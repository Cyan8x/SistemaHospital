package com.Sistema.Hospital.Service;

import com.Sistema.Hospital.Dto.PacienteRequestDto;
import com.Sistema.Hospital.Dto.PacienteResponseDto;

public interface EstadoAtencionService {
	String createEstadoAtencion(PacienteRequestDto pacienteRequestDto);
	PacienteResponseDto getAllPacientes();
	PacienteResponseDto getPacienteById(Integer paciente_id);
	String updatePaciente(PacienteRequestDto pacienteRequestDto, Integer paciente_id);
	String deletePacienteById(Integer id);	
}
