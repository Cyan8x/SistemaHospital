package com.Sistema.Hospital.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Paciente;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IPacienteRepository;
import com.Sistema.Hospital.Service.IPacienteService;

@Service
public class PacienteServiceImpl extends CRUDServiceImpl<Paciente, Integer> implements IPacienteService{

	@Autowired
	private IPacienteRepository iPacienteRepository;

	@Override
	protected IGENERICRepository<Paciente, Integer> getRepo() {
		return iPacienteRepository;
	}

	@Override
	public List<Paciente> selectFavoritosPorUsuario(Integer usuario_id) {
		return iPacienteRepository.selectFavoritosPorUsuario(usuario_id);
	}

	@Override
	public Integer insertFavoritoPorUsuario(Integer usuario_id, Integer paciente_id) {
		return iPacienteRepository.insertFavoritoPorUsuario(usuario_id, paciente_id);
	}

	@Override
	public Integer deleteFavoritoPorUsuario(Integer usuario_id, Integer paciente_id) {
		return iPacienteRepository.deleteFavoritoPorUsuario(usuario_id, paciente_id);
	}
}
