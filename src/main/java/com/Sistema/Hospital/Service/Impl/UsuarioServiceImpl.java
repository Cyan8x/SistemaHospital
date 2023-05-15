package com.Sistema.Hospital.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IUsuarioRepository;
import com.Sistema.Hospital.Service.IUsuarioService;

@Service
public class UsuarioServiceImpl extends CRUDServiceImpl<Usuario, Integer> implements IUsuarioService{

	@Autowired
	private IUsuarioRepository iUsuarioRepository;

	@Override
	protected IGENERICRepository<Usuario, Integer> getRepo() {
		return iUsuarioRepository;
	}
}
