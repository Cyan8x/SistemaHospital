package com.Sistema.Hospital.Service;

import com.Sistema.Hospital.Entity.Asistencia;

public interface IAsistenciaService extends ICRUDService<Asistencia, Integer>{

	Asistencia verificarAsistenciaUsuario(Integer usuario_id);
	
	String registrarAsistenciaConValidaciones(Integer usuario_id);
}
