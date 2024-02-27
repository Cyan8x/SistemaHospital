package com.Sistema.Hospital.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

	Page<T> getAllPagination(Pageable pageable) throws Exception;
	T getById(ID id) throws Exception;

	T update(T t) throws Exception;

	void deleteById(ID id) throws Exception;
}