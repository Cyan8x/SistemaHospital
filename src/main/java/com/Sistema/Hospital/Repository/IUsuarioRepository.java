package com.Sistema.Hospital.Repository;

import com.Sistema.Hospital.Entity.Usuario;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUsuarioRepository extends IGENERICRepository<Usuario, Integer>{

	Usuario findOneByUsuario(String username);

	@Query(value = "select * from usuarios where usuario_id <> 1 order by fecha_creacion_usuario desc", nativeQuery = true)
	List<Usuario> findAllUsersExceptAdminOrderByFechaDesc();
	
}
