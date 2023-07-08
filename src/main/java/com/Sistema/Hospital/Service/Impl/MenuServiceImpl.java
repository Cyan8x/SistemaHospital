package com.Sistema.Hospital.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Menu;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IMenuRepository;
import com.Sistema.Hospital.Repository.IUsuarioRepository;
import com.Sistema.Hospital.Service.IMenuService;

@Service
public class MenuServiceImpl extends CRUDServiceImpl<Menu, Integer> implements IMenuService{

	@Autowired
	private IMenuRepository iMenuRepository;
	
	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	@Override
	protected IGENERICRepository<Menu, Integer> getRepo() {
		return iMenuRepository;
	}
	
	@Override
	public List<Menu> listarMenuPorUsuario(String username) {
		Usuario usuario = iUsuarioRepository.findOneByUsuario(username);		
		return iMenuRepository.listarMenuPorUsuario(usuario.getUsuario_id());
	}

	@Override
	public void asignarMenusUsuario(Integer usuario_id, List<Menu> menus) {
		Usuario usuario = iUsuarioRepository.findById(usuario_id).orElse(null);
		usuario.setMenus(menus);
	}

	

}
