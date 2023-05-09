package com.Sistema.Hospital.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class MAPPERBetweenDtoAndEntity<RQ, RS, T> {

	@Autowired
	ModelMapper mapper;

	abstract Class<T> getTClass();

	abstract Class<RS> getRSClass();

	abstract Class<RQ> getRQClass();

	// DtoRequest a Entity
	protected T mapFromDtoRequestToEntity(RQ rq) {
		return mapper.map(rq, getTClass());
	}

	// Entity a DtoRequest
	protected void mapFromEntityToDtoRequest(RQ rq, T t) {
		mapper.map(rq, t);
	}

	// Entity a DtoResponse
	protected RS mapFromEntityToDtoResponse(T t) {
		return mapper.map(t, getRSClass());
	}

	// DtoResponse a Entity
	protected T mapFromDtoResponseToEntity(RS rs) {
		return mapper.map(rs, getTClass());
	}

//	// Entity a DtoRequest
//	protected RQ mapFromEntityToDtoReRequest(T t) {
//		return mapper.map(t, getRQClass());
//	}
}
