package com.Sistema.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Sistema.Hospital.Entity.Menu;

public interface IMenuRepository extends IGENERICRepository<Menu, Integer> {

	@Query(value = "select m.* from usuario_menu um inner join menu m on m.menu_id = um.menu_id where usuario_id = :usuario_id", nativeQuery = true)
	List<Menu> listarMenuPorUsuario(@Param("usuario_id") Integer usuario_id);
}
