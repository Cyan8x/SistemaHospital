package com.Sistema.Hospital.Service;

import java.util.List;

import com.Sistema.Hospital.Entity.Paciente;

public interface IPacienteService extends ICRUDService<Paciente, Integer> {
	
	List<Paciente> selectFavoritos();

}
