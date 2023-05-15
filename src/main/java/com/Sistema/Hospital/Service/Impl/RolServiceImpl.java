package com.Sistema.Hospital.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Rol;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IRolRepository;
import com.Sistema.Hospital.Service.IRolService;

@Service
public class RolServiceImpl extends CRUDServiceImpl<Rol, Integer> implements IRolService{

	@Autowired
	private IRolRepository iRolRepository;

	@Override
	protected IGENERICRepository<Rol, Integer> getRepo() {
		return iRolRepository;
	}
}
