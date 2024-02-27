package com.Sistema.Hospital.Service;

import com.Sistema.Hospital.Entity.Paciente;
import com.Sistema.Hospital.Entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsuarioService extends ICRUDService<Usuario, Integer> {

	Usuario findOneByUsuario(String username);
	
	Usuario updateUsuario(Usuario usuario);

	List<Usuario> findAllUsersExceptAdminOrderByFechaDesc();
}
