package com.Sistema.Hospital.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.NotificacionDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.Notificacion;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.INotificacionService;
import com.Sistema.Hospital.Service.IUsuarioService;

@RestController
@RequestMapping("/hospital/notificacion")
public class NotificacionController extends MAPPERBetweenDtoAndEntity<NotificacionDto, Notificacion> {

	@Autowired
	INotificacionService iNotificacionService;
	
	@Autowired
	private IUsuarioService iUsuarioService;

	@Override
	protected Class<Notificacion> getTClass() {
		return Notificacion.class;
	}

	@Override
	protected Class<NotificacionDto> getDTOClass() {
		return NotificacionDto.class;
	}

	@PostMapping()
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarId')")
	public ResponseEntity<SuccesMessageDto> createNotificacion(@RequestBody @Valid NotificacionDto notificacionDto) throws Exception {
		iNotificacionService.create(mapFromDtoRequestToEntity(notificacionDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value())
				.message("La Notificacion fue creada exitosamente.").timestamp(new Date()).build(), HttpStatus.CREATED);
	}

//	@GetMapping()
//	@PreAuthorize("@authServiceImpl.tieneAcceso('listarId')")
//	public ResponseEntity<Page<NotificacionDto>> getAllNotificaciones(@PageableDefault(sort = "fechaHoraNotificacion", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
//		Page<NotificacionDto> listaDto = iNotificacionService.getAll(pageable).map(notificacion -> mapFromEntityToDto(notificacion));
//		return new ResponseEntity<>(listaDto, HttpStatus.OK);
//	}

	@DeleteMapping("/{id}")
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarId')")
	public ResponseEntity<SuccesMessageDto> deleteNotificacionById(@PathVariable(value = "id") Integer notificacion_id) throws Exception {
		Notificacion notificacion= iNotificacionService.getById(notificacion_id);
		if (notificacion == null) {
			throw new ResourceNotFound("Notificacion", "id", notificacion_id);
		}
		iNotificacionService.deleteById(notificacion_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Notificacion eliminada exitosamente.").build(), HttpStatus.OK);
	}
	
	@GetMapping("/cantidadNotif/{usuario_id}")
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarId')")
	public ResponseEntity<Integer> cantidadaNotificacionesPorUsuario(@PathVariable(value = "usuario_id") Integer usuario_id) throws Exception {
		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}
		Integer cantidadNotificacionesPorUsuario= iNotificacionService.cantidadNotificacionesPorUsuario(usuario_id);
		return new ResponseEntity<>(cantidadNotificacionesPorUsuario, HttpStatus.OK);
	}
	
	@GetMapping("/porUsuario/{usuario_id}")
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarId')")
	public ResponseEntity<List<NotificacionDto>> selectNotificacionesPorUsuario(@PathVariable(value = "usuario_id") Integer usuario_id) throws Exception {
		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}
		List<NotificacionDto> listaDto = iNotificacionService.selectNotificacionesPorUsuario(usuario_id).stream().map(notificacion -> mapFromEntityToDto(notificacion))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}
}
