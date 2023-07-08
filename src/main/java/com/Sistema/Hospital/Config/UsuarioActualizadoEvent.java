package com.Sistema.Hospital.Config;

import org.springframework.context.ApplicationEvent;

import com.Sistema.Hospital.Entity.Usuario;

public class UsuarioActualizadoEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

	public UsuarioActualizadoEvent(Object source, Usuario usuario) {
		super(source);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}