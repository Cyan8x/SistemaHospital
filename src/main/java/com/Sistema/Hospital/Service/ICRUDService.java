package com.Sistema.Hospital.Service;

import java.util.List;

import com.Sistema.Hospital.Dto.SuccesMessageDto;

public interface ICRUDService<RQ, RS, ID> {

	/*
	 * T = TIPO K = KEY V = VALUE
	 */

	// void create(T t);
	// List<T> getAll();
	// T getById(ID id);
	// void updateById(T t);
	// void deleteById(ID id);

	SuccesMessageDto create(RQ rq);

	List<RS> getAll();

	RS getById(ID id);

	SuccesMessageDto updateById(RQ rq);

	SuccesMessageDto deleteById(ID id);
}