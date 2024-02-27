package com.Sistema.Hospital.Service;

import java.util.List;
import java.util.Map;

import com.Sistema.Hospital.Entity.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPacienteService extends ICRUDService<Paciente, Integer> {

	List<Paciente> selectFavoritosPorUsuario(Integer usuario_id);

	Integer insertFavoritoPorUsuario(Integer usuario_id, Integer paciente_id);

	Integer deleteFavoritoPorUsuario(Integer usuario_id, Integer paciente_id);

	Map<String, Integer> cantidadPacientesPorEstado();
	
	List<Paciente> selectPacientesActivos();

	Page<Paciente> selectPacientesActivosPagination(Pageable pageable);
	
	List<Paciente> validarExistenciaPacientePorDocumento(String dni, String ce);
}
