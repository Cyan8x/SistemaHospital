package com.Sistema.Hospital.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.PacienteDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.Paciente;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Exception.AttributeNotHaveToBeNull;
import com.Sistema.Hospital.Exception.ResourceAlreadyExist;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IPacienteService;
import com.Sistema.Hospital.Service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/hospital/paciente")
public class PacienteController extends MAPPERBetweenDtoAndEntity<PacienteDto, Paciente> {

	@Autowired
	private IPacienteService iPacienteService;

	@Autowired
	private IUsuarioService iUsuarioService;

	@Override
	protected Class<Paciente> getTClass() {
		return Paciente.class;
	}

	@Override
	protected Class<PacienteDto> getDTOClass() {
		return PacienteDto.class;
	}

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createPaciente(@RequestBody @Valid PacienteDto pacienteDto) throws Exception {
		List<Paciente> pacientes = iPacienteService.validarExistenciaPacientePorDocumento(pacienteDto.getDniPaciente(), pacienteDto.getCarneExtranjeria());
		if(pacienteDto.getDniPaciente() == null && pacienteDto.getCarneExtranjeria() == null) {
			throw new AttributeNotHaveToBeNull("DNI o CARNE DE EXTRANJERIA");
		}
		if (pacientes.size()>0) {
			if(pacienteDto.getDniPaciente() == null) {
				throw new ResourceAlreadyExist("Paciente", "Carne Extranjeria", pacienteDto.getCarneExtranjeria());
			}
			throw new ResourceAlreadyExist("Paciente", "DNI", pacienteDto.getDniPaciente());
		}
		iPacienteService.create(mapFromDtoRequestToEntity(pacienteDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Paciente creado exitosamente.").build(), HttpStatus.CREATED);
	}

	@GetMapping("/pagination")
	// @RequestMapping(value = "/" , method = RequestMethod.GET)
	// @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Page<PacienteDto>> getAllPacientes(@PageableDefault(sort = "fechaCreacionPaciente", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
		Page<PacienteDto> listaDto = iPacienteService.getAllPagination(pageable).map(paciente -> mapFromEntityToDto(paciente));
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}
	
	@GetMapping("/activos")
	public ResponseEntity<List<PacienteDto>> getAllPacientesActivos() throws Exception {
		List<PacienteDto> listaDto = iPacienteService.selectPacientesActivos().stream().map(paciente -> mapFromEntityToDto(paciente)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/activosPagination")
	public ResponseEntity<Page<PacienteDto>> getAllPacientesActivosPagination(Pageable pageable) throws Exception {
		Page<PacienteDto> listaDto = iPacienteService.selectPacientesActivosPagination(pageable).map(paciente -> mapFromEntityToDto(paciente));
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PacienteDto> getPacienteById(@PathVariable(value = "id") Integer paciente_id) throws Exception {
		Paciente paciente = iPacienteService.getById(paciente_id);
		if (paciente == null) {
			throw new ResourceNotFound("Paciente", "id", paciente_id);
		}
		return new ResponseEntity<>(mapFromEntityToDto(paciente), HttpStatus.OK);
	}

	@PutMapping()
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	public ResponseEntity<SuccesMessageDto> updatePacienteById(@RequestBody @Valid PacienteDto pacienteDto) throws Exception {
		Paciente paciente = iPacienteService.getById(pacienteDto.getPaciente_id());
		if (paciente == null) {
			throw new ResourceNotFound("Paciente", "id", pacienteDto.getPaciente_id());
		}

		iPacienteService.update(mapFromDtoRequestToEntity(pacienteDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Paciente actualizado exitosamente.").build(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	public ResponseEntity<SuccesMessageDto> deletePacienteById(@PathVariable(value = "id") Integer paciente_id) throws Exception {
		Paciente paciente = iPacienteService.getById(paciente_id);
		if (paciente == null) {
			throw new ResourceNotFound("Paciente", "id", paciente_id);
		}
		iPacienteService.deleteById(paciente_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Paciente eliminado exitosamente.").build(), HttpStatus.OK);
	}

	@GetMapping("/favoritos/{usuario_id}")
	public ResponseEntity<List<PacienteDto>> getPacientesFavoritos(@PathVariable(value = "usuario_id") Integer usuario_id) throws Exception {
		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}
		List<PacienteDto> listaDto = iPacienteService.selectFavoritosPorUsuario(usuario_id).stream().map(paciente -> mapFromEntityToDto(paciente))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@PostMapping("/insertFavoritos/{usuario_id}/{paciente_id}")
	public ResponseEntity<Integer> insertFavoritoPorUsuario(@PathVariable(value = "usuario_id") Integer usuario_id,
			@PathVariable(value = "paciente_id") Integer paciente_id) throws Exception {

		Paciente paciente = iPacienteService.getById(paciente_id);
		if (paciente == null) {
			throw new ResourceNotFound("Paciente", "id", paciente_id);
		}

		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}

		Integer cantidadInsercciones = iPacienteService.insertFavoritoPorUsuario(usuario_id, paciente_id);
		return new ResponseEntity<>(cantidadInsercciones, HttpStatus.OK);
	}

	@DeleteMapping("/deleteFavoritos/{usuario_id}/{paciente_id}")
	public ResponseEntity<Integer> deleteFavoritoPorUsuario(@PathVariable(value = "usuario_id") Integer usuario_id,
			@PathVariable(value = "paciente_id") Integer paciente_id) throws Exception {

		Paciente paciente = iPacienteService.getById(paciente_id);
		if (paciente == null) {
			throw new ResourceNotFound("Paciente", "id", paciente_id);
		}

		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}

		Integer cantidadDeletes = iPacienteService.deleteFavoritoPorUsuario(usuario_id, paciente_id);
		return new ResponseEntity<>(cantidadDeletes, HttpStatus.OK);
	}
	
	@GetMapping("/cantPacieEstado")
	public ResponseEntity<String> cantidadPacientesPorEstado() throws Exception {
		Map<String, Integer> contadorPacientesPorEstado = iPacienteService.cantidadPacientesPorEstado();
		ObjectMapper mapper = new ObjectMapper();
	    String json = mapper.writeValueAsString(contadorPacientesPorEstado);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
}
