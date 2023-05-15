package com.Sistema.Hospital.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Falta;
import com.Sistema.Hospital.Repository.IFaltaRepository;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Service.IFaltaService;

@Service
public class FaltaServiceImpl extends CRUDServiceImpl<Falta, Integer> implements IFaltaService{

	@Autowired
	private IFaltaRepository iFaltaRepository;

	@Override
	protected IGENERICRepository<Falta, Integer> getRepo() {
		return iFaltaRepository;
	}
}
