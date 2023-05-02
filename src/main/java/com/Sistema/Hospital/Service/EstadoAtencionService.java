package com.Sistema.Hospital.Service;

import java.util.List;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionRequestDto;
import com.Sistema.Hospital.Dto.EstadoAtencionDto.EstadoAtencionResponseDto;

public interface EstadoAtencionService {
	SuccesMessageDto createEstadoAtencion(EstadoAtencionRequestDto estadoAtencionRequestDto);
	List<EstadoAtencionResponseDto> getAllEstadoAtencion();
	EstadoAtencionResponseDto getEstadoAtencionById(Integer estado_atencion_id);
	SuccesMessageDto updateEstadoAtencion(EstadoAtencionRequestDto estadoAtencionRequestDto, Integer estado_atencion_id);
	SuccesMessageDto deleteEstadoAtencionById(Integer estado_atencion_id);	
}
