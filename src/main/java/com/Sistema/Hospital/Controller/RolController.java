package com.Sistema.Hospital.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.RolDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.Rol;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IRolService;

@RestController
@RequestMapping("/hospital/rol")
public class RolController extends MAPPERBetweenDtoAndEntity<RolDto, Rol> {

	@Autowired
	private IRolService iRolService;

	@Override
	protected Class<Rol> getTClass() {
		return Rol.class;
	}

	@Override
	protected Class<RolDto> getDTOClass() {
		return RolDto.class;
	}

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createRol(@RequestBody @Valid RolDto rolDto) throws Exception {
		iRolService.create(mapFromDtoRequestToEntity(rolDto));
		return new ResponseEntity<>(
				SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date()).message("Rol creado exitosamente.").build(),
				HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<RolDto>> getRolAll() throws Exception {
		List<RolDto> listaDto = iRolService.getAll().stream().map(rol -> mapFromEntityToDto(rol)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RolDto> getRolById(@PathVariable(value = "id") Integer rol_id) throws Exception {
		Rol rol = iRolService.getById(rol_id);
		if (rol == null) {
			throw new ResourceNotFound("Rol", "id", rol_id);
		}
		return new ResponseEntity<>(mapFromEntityToDto(rol), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updateById(@RequestBody @Valid RolDto rolDto) throws Exception {
		Rol rol = iRolService.getById(rolDto.getRol_id());
		if (rol == null) {
			throw new ResourceNotFound("Rol", "id", rolDto.getRol_id());
		}

		iRolService.update(mapFromDtoRequestToEntity(rolDto));
		return new ResponseEntity<>(
				SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date()).message("Rol actualizado exitosamente.").build(),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteById(@PathVariable(value = "id") Integer rol_id) throws Exception {
		Rol rol = iRolService.getById(rol_id);
		if (rol == null) {
			throw new ResourceNotFound("Rol", "id", rol_id);
		}
		iRolService.deleteById(rol_id);
		return new ResponseEntity<>(
				SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date()).message("Rol eliminado exitosamente.").build(),
				HttpStatus.OK);
	}
}
