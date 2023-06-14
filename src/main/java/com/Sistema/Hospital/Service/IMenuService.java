package com.Sistema.Hospital.Service;

import java.util.List;

import com.Sistema.Hospital.Entity.Menu;

public interface IMenuService extends ICRUDService<Menu, Integer> {

	List<Menu> listarMenuPorUsuario(String usuario);
}
