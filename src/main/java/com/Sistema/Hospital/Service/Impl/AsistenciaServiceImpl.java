package com.Sistema.Hospital.Service.Impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Asistencia;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Repository.IAsistenciaRepository;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Service.IAsistenciaService;

@Service
public class AsistenciaServiceImpl extends CRUDServiceImpl<Asistencia, Integer> implements IAsistenciaService {

	@Autowired
	private IAsistenciaRepository iAsistenciaRepository;

	@Override
	protected IGENERICRepository<Asistencia, Integer> getRepo() {
		return iAsistenciaRepository;
	}

	@Override
	public boolean existsByUsuarioAndFecha(Usuario usuario) {
		LocalDate fechaActual = LocalDate.now();
		return iAsistenciaRepository.existsByUsuarioAndFecha(usuario, fechaActual);
	}

}
