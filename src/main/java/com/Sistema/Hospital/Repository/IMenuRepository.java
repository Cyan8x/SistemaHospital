package com.Sistema.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Sistema.Hospital.Entity.Menu;

public interface IMenuRepository extends IGENERICRepository<Menu, Integer> {

	@Query(value = "select m.* from menu_rol mr inner join usuario_rol ur on ur.rol_id = mr.rol_id inner join menu m on m.menu_id = mr.menu_id inner join usuarios u on  u.usuario_id = ur.usuario_id where u.usuario = :usuario", nativeQuery = true)
	List<Menu> listarMenuPorUsuario(@Param("usuario") String usuario);
}
