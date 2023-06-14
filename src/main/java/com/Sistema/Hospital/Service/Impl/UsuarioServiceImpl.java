package com.Sistema.Hospital.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IUsuarioRepository;
import com.Sistema.Hospital.Service.IUsuarioService;

@Service
public class UsuarioServiceImpl extends CRUDServiceImpl<Usuario, Integer> implements UserDetailsService, IUsuarioService {

	@Autowired
	private IUsuarioRepository iUsuarioRepository;

	@Override
	protected IGENERICRepository<Usuario, Integer> getRepo() {
		return iUsuarioRepository;
	}
	
	@Override
	public Usuario findOneByUsuario(String username) {
		return iUsuarioRepository.findOneByUsuario(username);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = iUsuarioRepository.findOneByUsuario(username);

		if (usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}

		List<GrantedAuthority> roles = new ArrayList<>();

		usuario.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getNombreRol()));
		});

		UserDetails ud = new User(usuario.getUsuario(), usuario.getPassword(), usuario.getEsActivoUsuario(), true, true, true, roles);

		return ud;
	}
}
