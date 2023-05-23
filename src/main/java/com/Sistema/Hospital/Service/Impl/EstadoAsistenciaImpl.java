package com.Sistema.Hospital.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.EstadoAsistencia;
import com.Sistema.Hospital.Repository.IEstadoAsistenciaRepository;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Service.IEstadoAsistenciaService;

@Service
public class EstadoAsistenciaImpl extends CRUDServiceImpl<EstadoAsistencia, Integer> implements IEstadoAsistenciaService {

	@Autowired
	private IEstadoAsistenciaRepository iEstadoAsistenciaRepository;

	@Override
	protected IGENERICRepository<EstadoAsistencia, Integer> getRepo() {
		return iEstadoAsistenciaRepository;
	}

}
