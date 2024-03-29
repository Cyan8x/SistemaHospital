package com.Sistema.Hospital.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	ModelMapper mapper;

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
		String passwordEncriptada = passwordEncoder.encode(usuarioDto.getPassword());
		usuarioDto.setPassword(passwordEncriptada);
		iUsuarioService.create(mapFromDtoRequestToEntity(usuarioDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Usuario creado exitosamente.").build(), HttpStatus.CREATED);
	}

	@GetMapping("/pagination")
	public ResponseEntity<Page<UsuarioDto>> getAllUsuariosPagination(@PageableDefault(sort = "fechaCreacionUsuario", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
		Page<UsuarioDto> listaDto = iUsuarioService.getAllPagination(pageable).map(usuario -> mapFromEntityToDto(usuario));
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
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
	
//	@GetMapping("/roles/{id}")
//	public ResponseEntity<List<RolDto>> getRolesFromUsuario(@PathVariable(value = "id") Integer usuario_id) throws Exception {
//		Usuario usuario = iUsuarioService.getById(usuario_id);
//		if (usuario == null) {
//			throw new ResourceNotFound("Usuario", "id", usuario_id);
//		}
//		List<Rol> rolesUsuario = iUsuarioService.getRolesFromUsuario(usuario_id);
//		return new ResponseEntity<>(rolesUsuario.stream().map(rol-> mapper.map(rol, RolDto.class)).collect(Collectors.toList()), HttpStatus.OK);
//	}
//	
//	@PostMapping("/agregarRoles/{id}")
//	public ResponseEntity<SuccesMessageDto> asignarRolesUsuario(@PathVariable(value = "id") Integer usuario_id, @RequestBody @Valid List<RolDto> rolesDto) throws Exception {
//		Usuario usuario = iUsuarioService.getById(usuario_id);
//		if (usuario == null) {
//			throw new ResourceNotFound("Usuario", "id", usuario_id);
//		}
//		List<Rol> rolesUsuario = rolesDto.stream().map(rolDto -> mapper.map(rolDto, Rol.class)).collect(Collectors.toList());
//		iUsuarioService.agregarRolesAUsuario(usuario_id,rolesUsuario);
//		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
//				.message("Se asgignaron correctamente los roles.").build(), HttpStatus.CREATED);
//	}
	
	@GetMapping("/porUsername/{username}")
	public ResponseEntity<UsuarioDto> getUsuarioByUserName(@PathVariable(value = "username") String username) throws Exception {
		Usuario usuario = iUsuarioService.findOneByUsuario(username);
//		if (usuario == null) {
//			throw new ResourceNotFound("Usuario", "username", username);
//		}
		return new ResponseEntity<>(mapFromEntityToDto(usuario), HttpStatus.OK);
	}

	@PutMapping()
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	public ResponseEntity<SuccesMessageDto> updateUsuarioById(@RequestBody @Valid UsuarioDto usuarioDto) throws Exception {
		Usuario usuario = iUsuarioService.getById(usuarioDto.getUsuario_id());
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuarioDto.getUsuario_id());
		}

		if (!usuarioDto.getPassword().startsWith("$2a$")) {
	        // Encriptar la nueva contraseña
	        String nuevaContrasenaEncriptada = passwordEncoder.encode(usuarioDto.getPassword());
	        usuarioDto.setPassword(nuevaContrasenaEncriptada);
	    }
		
		iUsuarioService.updateUsuario(mapFromDtoRequestToEntity(usuarioDto));
		
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Usuario actualizado exitosamente.").build(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
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
