package com.Sistema.Hospital.Service;

import java.util.List;

import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionRequestDto;
import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionResponseDto;

public interface EstadoAtencionService {
	String createEstadoAtencion(EstadoAtencionRequestDto estadoAtencionRequestDto);
	List<EstadoAtencionResponseDto> getAllEstadoAtencion();
	EstadoAtencionResponseDto getEstadoAtencionById(Integer estado_atencion_id);
	String updateEstadoAtencion(EstadoAtencionRequestDto estadoAtencionRequestDto, Integer estado_atencion_id);
	String deleteEstadoAtencionById(Integer estado_atencion_id);	
}
