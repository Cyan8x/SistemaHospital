package com.Sistema.Hospital.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Procedimiento;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IProcedimientoRepository;
import com.Sistema.Hospital.Service.IProcedimientoService;

@Service
public class ProcedimientoServiceImpl extends CRUDServiceImpl<Procedimiento, Integer> implements IProcedimientoService {

	@Autowired
	private IProcedimientoRepository iProcedimientoRepository;

	@Override
	protected IGENERICRepository<Procedimiento, Integer> getRepo() {
		return iProcedimientoRepository;
	}

}
