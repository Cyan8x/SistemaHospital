package com.Sistema.Hospital.Service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.EstadoAtencion;
import com.Sistema.Hospital.Entity.Paciente;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IPacienteRepository;
import com.Sistema.Hospital.Service.IPacienteService;

@Service
public class PacienteServiceImpl extends CRUDServiceImpl<Paciente, Integer> implements IPacienteService{

	@Autowired
	private IPacienteRepository iPacienteRepository;

	@Override
	protected IGENERICRepository<Paciente, Integer> getRepo() {
		return iPacienteRepository;
	}

	@Override
	public List<Paciente> selectFavoritosPorUsuario(Integer usuario_id) {
		return iPacienteRepository.selectFavoritosPorUsuario(usuario_id);
	}

	@Override
	public Integer insertFavoritoPorUsuario(Integer usuario_id, Integer paciente_id) {
		return iPacienteRepository.insertFavoritoPorUsuario(usuario_id, paciente_id);
	}

	@Override
	public Integer deleteFavoritoPorUsuario(Integer usuario_id, Integer paciente_id) {
		return iPacienteRepository.deleteFavoritoPorUsuario(usuario_id, paciente_id);
	}

	@Override
	public Map<String, Integer> cantidadPacientesPorEstado() {
		List<Paciente> pacientes = iPacienteRepository.findAll();
		Map<String, Integer> contadores = new HashMap<>();

	    for (Paciente paciente : pacientes) {
	        EstadoAtencion estadoAtencion = paciente.getEstadoAtencion();
	        String nombreEstadoAtencion = estadoAtencion.getNombreEstadoAtencion();

	        // Verificar si el estadoAsistencia ya está en el mapa
	        if (contadores.containsKey(nombreEstadoAtencion)) {
	            // Incrementar el contador existente
	            Integer contador = contadores.get(nombreEstadoAtencion);
	            contadores.put(nombreEstadoAtencion, contador + 1);
	        } else {
	            // Agregar el estadoAsistencia al mapa con un contador inicial de 1
	            contadores.put(nombreEstadoAtencion, 1);
	        }
	    }

	    return contadores;
	}
}
