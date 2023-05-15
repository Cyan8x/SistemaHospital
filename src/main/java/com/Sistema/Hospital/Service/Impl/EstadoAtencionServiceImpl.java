package com.Sistema.Hospital.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.EstadoAtencion;
import com.Sistema.Hospital.Repository.IEstadoAtencionRepository;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Service.IEstadoAtencionService;

@Service
public class EstadoAtencionServiceImpl extends CRUDServiceImpl<EstadoAtencion, Integer> implements IEstadoAtencionService{

	@Autowired
	private IEstadoAtencionRepository iEstadoAtencionRepository;

	@Override
	protected IGENERICRepository<EstadoAtencion, Integer> getRepo() {
		return iEstadoAtencionRepository;
	}
}
