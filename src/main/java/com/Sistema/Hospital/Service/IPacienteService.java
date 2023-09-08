package com.Sistema.Hospital.Service;

import java.util.List;
import java.util.Map;

import com.Sistema.Hospital.Entity.Paciente;

public interface IPacienteService extends ICRUDService<Paciente, Integer> {

	List<Paciente> selectFavoritosPorUsuario(Integer usuario_id);

	Integer insertFavoritoPorUsuario(Integer usuario_id, Integer paciente_id);

	Integer deleteFavoritoPorUsuario(Integer usuario_id, Integer paciente_id);

	Map<String, Integer> cantidadPacientesPorEstado();
	
	List<Paciente> selectPacientesActivos();
	
	List<Paciente> validarExistenciaPacientePorDocumento(String dni, String ce);
}
