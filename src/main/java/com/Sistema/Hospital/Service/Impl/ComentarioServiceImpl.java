package com.Sistema.Hospital.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Comentario;
import com.Sistema.Hospital.Repository.IComentarioRepository;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Service.IComentarioService;

@Service
public class ComentarioServiceImpl extends CRUDServiceImpl<Comentario, Integer> implements IComentarioService {

	@Autowired
	private IComentarioRepository iComentarioRepository;

	@Override
	protected IGENERICRepository<Comentario, Integer> getRepo() {
		return iComentarioRepository;
	}

	@Override
	public List<Comentario> selectComentariosPorPaciente(Integer idPaciente) {
		return iComentarioRepository.selectComentariosPorPaciente(idPaciente);
	}

}
