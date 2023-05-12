package com.Sistema.Hospital.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.Rol.RolDto;
import com.Sistema.Hospital.Entity.Rol;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Repository.IRolRepository;
import com.Sistema.Hospital.Service.IRolService;

@Service
public class RolServiceImpl extends MAPPERBetweenDtoAndEntity<RolDto, RolDto, Rol> implements IRolService {

	@Autowired
	IRolRepository iRolRepository;

	@Override
	Class<Rol> getTClass() {
		return Rol.class;
	}

	@Override
	Class<RolDto> getRSClass() {
		return RolDto.class;
	}

	@Override
	Class<RolDto> getRQClass() {
		return getRSClass();
	}

	@Override
	public SuccesMessageDto create(RolDto rq) {
		iRolRepository.save(mapFromDtoRequestToEntity(rq));
		return SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).message("Se creó exitosamente el rol.").timestamp(new Date())
				.build();
	}

	@Override
	public List<RolDto> getAll() {
		return iRolRepository.findAll().stream().map(rol -> mapFromEntityToDtoResponse(rol)).collect(Collectors.toList());
	}

	@Override
	public RolDto getById(Integer id) {
		return mapFromEntityToDtoResponse(iRolRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Rol", "id", id)));
	}

	@Override
	public SuccesMessageDto updateById(RolDto rq) {
		Rol rol = mapFromDtoRequestToEntity(getById(rq.getRol_id()));
		mapFromEntityToDtoRequest(rq, rol);
		iRolRepository.save(rol);
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).message("Se actualizó exitosamente el rol.").timestamp(new Date())
				.build();
	}

	@Override
	public SuccesMessageDto deleteById(Integer id) {
		Rol rol = mapFromDtoRequestToEntity(getById(id));
		iRolRepository.delete(rol);
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).message("Se eliminó exitosamente el rol.").timestamp(new Date()).build();
	}

}
