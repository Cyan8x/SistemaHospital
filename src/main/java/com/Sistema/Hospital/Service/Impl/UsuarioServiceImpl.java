package com.Sistema.Hospital.Service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.Usuario.UsuarioRequestDto;
import com.Sistema.Hospital.Dto.Usuario.UsuarioResponseDto;
import com.Sistema.Hospital.Entity.Rol;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Repository.IRolRepository;
import com.Sistema.Hospital.Repository.IUsuarioRepository;
import com.Sistema.Hospital.Service.IUsuarioService;

@Service
public class UsuarioServiceImpl extends MAPPERBetweenDtoAndEntity<UsuarioRequestDto, UsuarioResponseDto, Usuario>
		implements IUsuarioService{

	@Autowired
	IUsuarioRepository iUsuarioRepository;

	@Autowired
	IRolRepository iRolRepository;

	@Override
	Class<Usuario> getTClass() {
		return Usuario.class;
	}

	@Override
	Class<UsuarioResponseDto> getRSClass() {
		return UsuarioResponseDto.class;
	}

	@Override
	Class<UsuarioRequestDto> getRQClass() {
		return UsuarioRequestDto.class;
	}

	@Override
	public SuccesMessageDto create(UsuarioRequestDto rq) {
		List<Integer> rolesEnInteger = new ArrayList<Integer>();
		rolesEnInteger = Arrays.asList(rq.getRoles());
		List<Rol> rolesEntity = rolesEnInteger.stream()
				.map(rol -> iRolRepository.findById(rol).orElseThrow(() -> new ResourceNotFound("Rol", "id", rol))).collect(Collectors.toList());
		Usuario   usuario     = mapFromDtoRequestToEntity(rq);
		usuario.setRoles(rolesEntity);
		iUsuarioRepository.save(usuario);
		return SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).message("Se creó exitosamente el usuario.").timestamp(new Date())
				.build();
	}

	@Override
	public List<UsuarioResponseDto> getAll() {
		return iUsuarioRepository.findAll().stream().map(usuario -> mapFromEntityToDtoResponse(usuario)).collect(Collectors.toList());
	}

	@Override
	public UsuarioResponseDto getById(Integer id) {
		return mapFromEntityToDtoResponse(iUsuarioRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Usuario", "id", id)));
	}

	@Override
	public SuccesMessageDto updateById(UsuarioRequestDto rq) {
		Usuario usuario = iUsuarioRepository.findById(rq.getUsuario_id()).orElseThrow(()-> new ResourceNotFound("Usuario", "id", rq.getUsuario_id()));
		List<Integer> rolesEnInteger = new ArrayList<Integer>();
		rolesEnInteger = Arrays.asList(rq.getRoles());
		List<Rol> rolesEntity = rolesEnInteger.stream()
				.map(rol -> iRolRepository.findById(rol).orElseThrow(() -> new ResourceNotFound("Rol", "id", rol))).collect(Collectors.toList());
		mapFromEntityToDtoRequest(rq, usuario);
		usuario.setRoles(rolesEntity);
		iUsuarioRepository.save(usuario);
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).message("Se actualizó exitosamente el usuario.").timestamp(new Date())
				.build();
	}

	@Override
	public SuccesMessageDto deleteById(Integer id) {
		iUsuarioRepository.delete(iUsuarioRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Usuario", "id", id)));
		return SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).message("Se eliminó exitosamente el usuario.").timestamp(new Date())
				.build();
	}

}
