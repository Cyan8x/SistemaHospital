package com.Sistema.Hospital.Service;

import java.util.List;

import com.Sistema.Hospital.Entity.Notificacion;

public interface INotificacionService extends ICRUDService<Notificacion, Integer> {
	Integer cantidadNotificacionesPorUsuario(Integer usuarioDestino);
	
	List<Notificacion> selectNotificacionesPorUsuario(Integer usuarioDestino);
}
