package com.Sistema.Hospital.Service;

import java.util.List;

public interface CRUDService<T,ID> {
	
	/*
	 * T = TIPO
	 * K = KEY
	 * V = VALUE
	*/
	
	T createPaciente(T t);
	List<T> getAllPacientes();
	T getPacienteById(ID id);
	T updatePacienteById(T t);
	void deletePacienteById(ID id);	
}
