package com.Sistema.Hospital.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.Sistema.Hospital.Entity.Comentario;
import com.Sistema.Hospital.Repository.IComentarioRepository;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Service.IComentarioService;

public class ComentarioServiceImpl extends CRUDServiceImpl<Comentario, Integer> implements IComentarioService {

	@Autowired
	private IComentarioRepository iComentarioRepository;

	@Override
	protected IGENERICRepository<Comentario, Integer> getRepo() {
		return iComentarioRepository;
	}

}
