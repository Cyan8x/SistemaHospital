package com.Sistema.Hospital.Service;

import com.Sistema.Hospital.Entity.Asistencia;
import com.Sistema.Hospital.Entity.Usuario;

public interface IAsistenciaService extends ICRUDService<Asistencia, Integer>{

	boolean existsByUsuarioAndFecha(Usuario usuario);
}
