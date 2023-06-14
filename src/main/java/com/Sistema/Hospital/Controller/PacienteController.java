package com.Sistema.Hospital.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IPacienteService;

@RestController
@RequestMapping("/hospital/paciente")
public class PacienteController extends MAPPERBetweenDtoAndEntity<PacienteDto, Paciente> {

	@Autowired
	private IPacienteService iPacienteService;

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
		iPacienteService.create(mapFromDtoRequestToEntity(pacienteDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Paciente creado exitosamente.").build(), HttpStatus.CREATED);
	}

	@GetMapping()
	// @RequestMapping(value = "/" , method = RequestMethod.GET)
	// @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	public ResponseEntity<List<PacienteDto>> getAllPacientes() throws Exception {
		List<PacienteDto> listaDto = iPacienteService.getAll().stream().map(paciente -> mapFromEntityToDto(paciente)).collect(Collectors.toList());
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
	public ResponseEntity<SuccesMessageDto> deletePacienteById(@PathVariable(value = "id") Integer paciente_id) throws Exception {
		Paciente paciente = iPacienteService.getById(paciente_id);
		if (paciente == null) {
			throw new ResourceNotFound("Paciente", "id", paciente_id);
		}
		iPacienteService.deleteById(paciente_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Paciente eliminado exitosamente.").build(), HttpStatus.OK);
	}

	@GetMapping("/favoritos")
	public ResponseEntity<List<PacienteDto>> getPacientesFavoritos() throws Exception {
		List<PacienteDto> listaDto = iPacienteService.selectFavoritos().stream().map(paciente -> mapFromEntityToDto(paciente))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}
}
