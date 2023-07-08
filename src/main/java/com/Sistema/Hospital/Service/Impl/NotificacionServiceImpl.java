package com.Sistema.Hospital.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Notificacion;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.INotificacionRepository;
import com.Sistema.Hospital.Service.INotificacionService;

@Service
public class NotificacionServiceImpl extends CRUDServiceImpl<Notificacion, Integer> implements INotificacionService {

	@Autowired
	INotificacionRepository iNotificacionRepository;
	
	@Override
	protected IGENERICRepository<Notificacion, Integer> getRepo() {
		return iNotificacionRepository;
	}

	@Override
	public Integer cantidadNotificacionesPorUsuario(Integer usuarioDestino) {
		return iNotificacionRepository.cantidadNotificacionesPorUsuario(usuarioDestino);
	}

	@Override
	public List<Notificacion> selectNotificacionesPorUsuario(Integer usuarioDestino) {
		return iNotificacionRepository.selectNotificacionesPorUsuario(usuarioDestino);
	}

}
