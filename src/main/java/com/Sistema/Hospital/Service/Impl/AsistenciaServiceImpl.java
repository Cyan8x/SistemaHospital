package com.Sistema.Hospital.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.Sistema.Hospital.Entity.Asistencia;
import com.Sistema.Hospital.Repository.IAsistenciaRepository;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Service.IAsistenciaService;

public class AsistenciaServiceImpl extends CRUDServiceImpl<Asistencia, Integer> implements IAsistenciaService {

	@Autowired
	private IAsistenciaRepository iAsistenciaRepository;

	@Override
	protected IGENERICRepository<Asistencia, Integer> getRepo() {
		return iAsistenciaRepository;
	}

}
