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
import org.springframework.http.MediaType;
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

import com.Sistema.Hospital.Dto.ProcedimientoDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.Paciente;
import com.Sistema.Hospital.Entity.Procedimiento;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IPacienteService;
import com.Sistema.Hospital.Service.IProcedimientoService;
import com.Sistema.Hospital.Service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/hospital/procedimiento")
public class ProcedimientoController extends MAPPERBetweenDtoAndEntity<ProcedimientoDto, Procedimiento> {

	@Autowired
	private IProcedimientoService iProcedimientoService;

	@Autowired
	private IPacienteService iPacienteService;

	@Autowired
	private IUsuarioService iUsuarioService;

	@Override
	protected Class<Procedimiento> getTClass() {
		return Procedimiento.class;
	}

	@Override
	protected Class<ProcedimientoDto> getDTOClass() {
		return ProcedimientoDto.class;
	}

	@PostMapping
	public ResponseEntity<ProcedimientoDto> createProcedimiento(@RequestBody @Valid ProcedimientoDto procedimientoDto) throws Exception {
		Procedimiento proced = iProcedimientoService.create(mapFromDtoRequestToEntity(procedimientoDto));
		return new ResponseEntity<>(mapFromEntityToDto(proced), HttpStatus.CREATED);
	}

//	@GetMapping()
//	public ResponseEntity<Page<ProcedimientoDto>> getAllProcedimientos(@PageableDefault(sort = "fechaCreacionProced", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
//		Page<ProcedimientoDto> listaDto = iProcedimientoService.getAll(pageable).map(procedimientoDto -> mapFromEntityToDto(procedimientoDto));
//		return new ResponseEntity<>(listaDto, HttpStatus.OK);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<ProcedimientoDto> getProcedimientoById(@PathVariable(value = "id") Integer procedimientoDto_id) throws Exception {
		Procedimiento procedimiento = iProcedimientoService.getById(procedimientoDto_id);
		if (procedimiento == null) {
			throw new ResourceNotFound("Procedimiento", "id", procedimientoDto_id);
		}
		return new ResponseEntity<>(mapFromEntityToDto(procedimiento), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<ProcedimientoDto> updateProcedimientoById(@RequestBody @Valid ProcedimientoDto procedimientoDto) throws Exception {
		Procedimiento procedimiento = iProcedimientoService.getById(procedimientoDto.getProcedimiento_id());
		if (procedimiento == null) {
			throw new ResourceNotFound("Procedimiento", "id", procedimientoDto.getProcedimiento_id());
		}

		Procedimiento newProced = iProcedimientoService.update(mapFromDtoRequestToEntity(procedimientoDto));
		return new ResponseEntity<>(mapFromEntityToDto(newProced), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	public ResponseEntity<SuccesMessageDto> deleteProcedimientoById(@PathVariable(value = "id") Integer procedimientoDto_id) throws Exception {
		Procedimiento procedimiento = iProcedimientoService.getById(procedimientoDto_id);
		if (procedimiento == null) {
			throw new ResourceNotFound("Procedimiento", "id", procedimientoDto_id);
		}
		iProcedimientoService.deleteById(procedimientoDto_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Procedimiento eliminado exitosamente.").build(), HttpStatus.OK);
	}

	@GetMapping("/pendientesPorPaciente/{pacienteId}")
	public ResponseEntity<List<ProcedimientoDto>> getProcedimientosPendientesPorPaciente(@PathVariable(value = "pacienteId") Integer pacienteId)
			throws Exception {
		Paciente paciente = iPacienteService.getById(pacienteId);
		if (paciente == null) {
			throw new ResourceNotFound("Paciente", "id", pacienteId);
		}
		List<ProcedimientoDto> listaDto = iProcedimientoService.selectProcedimientosPendientesPorPaciente(pacienteId).stream()
				.map(procedimiento -> mapFromEntityToDto(procedimiento)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/terminadosPorPaciente/{pacienteId}")
	public ResponseEntity<List<ProcedimientoDto>> selectProcedimientosTerminadosPorPaciente(@PathVariable(value = "pacienteId") Integer pacienteId)
			throws Exception {
		Paciente paciente = iPacienteService.getById(pacienteId);
		if (paciente == null) {
			throw new ResourceNotFound("Paciente", "id", pacienteId);
		}
		List<ProcedimientoDto> listaDto = iProcedimientoService.selectProcedimientosTerminadosPorPaciente(pacienteId).stream()
				.map(procedimiento -> mapFromEntityToDto(procedimiento)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/terminadosPorUsuario/{usuario_id}")
	public ResponseEntity<List<ProcedimientoDto>> selectProcedimientosTerminadosPorUsuario(@PathVariable(value = "usuario_id") Integer usuario_id)
			throws Exception {
		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}
		List<ProcedimientoDto> listaDto = iProcedimientoService.selectProcedimientosTerminadosPorUsuario(usuario_id).stream()
				.map(procedimiento -> mapFromEntityToDto(procedimiento)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/porPaciente/{pacienteId}")
	public ResponseEntity<List<ProcedimientoDto>> selectProcedimientosPorPaciente(@PathVariable(value = "pacienteId") Integer pacienteId)
			throws Exception {
		Paciente paciente = iPacienteService.getById(pacienteId);
		if (paciente == null) {
			throw new ResourceNotFound("Paciente", "id", pacienteId);
		}
		List<ProcedimientoDto> listaDto = iProcedimientoService.selectProcedimientosPorPaciente(pacienteId).stream()
				.map(procedimiento -> mapFromEntityToDto(procedimiento)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/pendientesPorUsuario/{usuario_id}")
	public ResponseEntity<List<ProcedimientoDto>> selectProcedimientosPendientesPorUsuario(@PathVariable(value = "usuario_id") Integer usuario_id)
			throws Exception {
		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}
		List<ProcedimientoDto> listaDto = iProcedimientoService.selectProcedimientosPendientesPorUsuario(usuario_id).stream()
				.map(procedimiento -> mapFromEntityToDto(procedimiento)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}
	
	@GetMapping("/cantComplTermin/{id}")
	public ResponseEntity<String> cantidadTerminadoPendientePorUsuario(@PathVariable(value = "id") Integer usuario_id) throws Exception {
		Map<String, Integer> contadorPendientesTermCompl = iProcedimientoService.cantidadTerminadoPendientePorUsuario(usuario_id);
		ObjectMapper mapper = new ObjectMapper();
	    String json = mapper.writeValueAsString(contadorPendientesTermCompl);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
	@GetMapping(value = "/genReportProced/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporteAsistenciaUsuario(@PathVariable(value = "id") Integer paciente_id) throws Exception {
		Paciente paciente = iPacienteService.getById(paciente_id);
		if (paciente == null) {
			throw new ResourceNotFound("Paciente", "id", paciente_id);
		}
		byte[] data = null;
		data = iProcedimientoService.generarReporteProcedimientosPaciente(paciente);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
