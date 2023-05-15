package com.Sistema.Hospital.Service;

import java.util.List;

public interface ICRUDService<T, ID> {

	/*
	 * T = TIPO K = KEY V = VALUE
	 */

	// void create(T t);
	// List<T> getAll();
	// T getById(ID id);
	// void updateById(T t);
	// void deleteById(ID id);

	T create(T t) throws Exception;

	List<T> getAll() throws Exception;

	T getById(ID id) throws Exception;

	T update(T t) throws Exception;

	void deleteById(ID id) throws Exception;
}