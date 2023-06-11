package com.Sistema.Hospital.Service;

import java.util.List;

import com.Sistema.Hospital.Entity.Comentario;

public interface IComentarioService extends ICRUDService<Comentario, Integer>{

	List<Comentario> selectComentariosPorPaciente(Integer idPaciente);
}
