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

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.UsuarioDto;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IUsuarioService;

@RestController
@RequestMapping("/hospital/usuario")
public class UsuarioController extends MAPPERBetweenDtoAndEntity<UsuarioDto, Usuario> {

	@Autowired
	private IUsuarioService iUsuarioService;

	@Override
	protected Class<Usuario> getTClass() {
		return Usuario.class;
	}

	@Override
	protected Class<UsuarioDto> getDTOClass() {
		return UsuarioDto.class;
	}

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createUsuario(@RequestBody @Valid UsuarioDto usuarioDto) throws Exception {
		iUsuarioService.create(mapFromDtoRequestToEntity(usuarioDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Usuario creado exitosamente.").build(), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<UsuarioDto>> getAllUsuarios() throws Exception {
		List<UsuarioDto> listaDto = iUsuarioService.getAll().stream().map(usuario -> mapFromEntityToDto(usuario)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable(value = "id") Integer usuario_id) throws Exception {
		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}
		return new ResponseEntity<>(mapFromEntityToDto(usuario), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updateUsuarioById(@RequestBody @Valid UsuarioDto usuarioDto) throws Exception {
		Usuario usuario = iUsuarioService.getById(usuarioDto.getUsuario_id());
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuarioDto.getUsuario_id());
		}
		iUsuarioService.update(mapFromDtoRequestToEntity(usuarioDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Usuario actualizado exitosamente.").build(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteUsuarioById(@PathVariable(value = "id") Integer usuario_id) throws Exception {
		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}
		iUsuarioService.deleteById(usuario_id);
		return new ResponseEntity<>(
				SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date()).message("Usuario eliminado exitosamente.").build(),
				HttpStatus.OK);
	}
}
