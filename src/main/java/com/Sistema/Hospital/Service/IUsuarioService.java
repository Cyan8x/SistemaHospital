package com.Sistema.Hospital.Service;

import com.Sistema.Hospital.Entity.Usuario;

public interface IUsuarioService extends ICRUDService<Usuario, Integer> {

	Usuario findOneByUsuario(String username);
	
	Usuario updateUsuario(Usuario usuario);
}
