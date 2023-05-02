package com.Sistema.Hospital.Service.Impl.Util;

import org.modelmapper.ModelMapper;

public interface MapperBetweenDtoAndEntity<DS, E, DQ> {

	public static final ModelMapper mapper = new ModelMapper();

	// DtoRequest a Entity
	E mapFromDtoRequestToEntity(DQ dq);

	// Entity a DtoRequest
	void mapFromEntityToDtoRequest(DQ dq, E e);

	// Entity a DtoResponse
	DS mapFromEntityToDtoResponse(E e);

	// DtoResponse a Entity
	E mapFromDtoResponseToEntity(DS ds);
}
