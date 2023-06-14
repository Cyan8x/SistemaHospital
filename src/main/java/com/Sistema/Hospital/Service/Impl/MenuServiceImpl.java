package com.Sistema.Hospital.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Menu;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IMenuRepository;
import com.Sistema.Hospital.Service.IMenuService;

@Service
public class MenuServiceImpl extends CRUDServiceImpl<Menu, Integer> implements IMenuService{

	@Autowired
	private IMenuRepository iMenuRepository;
	
	@Override
	protected IGENERICRepository<Menu, Integer> getRepo() {
		return iMenuRepository;
	}
	
	@Override
	public List<Menu> listarMenuPorUsuario(String usuario) {
		return iMenuRepository.listarMenuPorUsuario(usuario);
	}

	

}
