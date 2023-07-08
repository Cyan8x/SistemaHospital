package com.Sistema.Hospital.Repository;

import com.Sistema.Hospital.Entity.Usuario;

public interface IUsuarioRepository extends IGENERICRepository<Usuario, Integer>{

	Usuario findOneByUsuario(String username);
	
}
