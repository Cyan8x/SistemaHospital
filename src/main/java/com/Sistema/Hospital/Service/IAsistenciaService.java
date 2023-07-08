package com.Sistema.Hospital.Service;

import java.util.List;
import java.util.Map;

import com.Sistema.Hospital.Dto.JustificacionTardanzaDto;
import com.Sistema.Hospital.Entity.Asistencia;
import com.Sistema.Hospital.Entity.Usuario;

public interface IAsistenciaService extends ICRUDService<Asistencia, Integer>{

	Asistencia verificarAsistenciaUsuario(Integer usuario_id);
	
	String registrarAsistenciaConValidaciones(Integer usuario_id);
	
	String justificarTardanza(JustificacionTardanzaDto justificacionTardanzaDto);
	
	List<Asistencia> asistenciasOfUsuario(Integer usuario_id);
		
	void verificarAsistenciaDespuesHorario(Integer usuario_id);
	
	Map<String, Integer> cantidadAsistenciasPorEstado(Integer usuario_id);
	
	byte[] generarReporteAsistenciaUsuario(Usuario usuario);
}
