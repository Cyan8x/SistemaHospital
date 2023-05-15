package com.Sistema.Hospital.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class MAPPERBetweenDtoAndEntity<DTO, T> {

	@Autowired
	ModelMapper mapper;

	protected abstract Class<T> getTClass();
	protected abstract Class<DTO> getDTOClass();

	// DtoRequest a Entity
	protected T mapFromDtoRequestToEntity(DTO dto) {
		return mapper.map(dto, getTClass());
	}

	// Entity a DtoRequest
	protected DTO mapFromEntityToDto(T t) {
		return mapper.map(t, getDTOClass());
	}
}
