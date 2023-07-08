package com.Sistema.Hospital.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.AsistenciaDto;
import com.Sistema.Hospital.Dto.JustificacionTardanzaDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.Asistencia;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IAsistenciaService;
import com.Sistema.Hospital.Service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/hospital/asistencia")
public class AsistenciaController extends MAPPERBetweenDtoAndEntity<AsistenciaDto, Asistencia> {

	@Autowired
	private IAsistenciaService iAsistenciaService;

	@Autowired
	private IUsuarioService iUsuarioService;

	@Override
	protected Class<Asistencia> getTClass() {
		return Asistencia.class;
	}

	@Override
	protected Class<AsistenciaDto> getDTOClass() {
		return AsistenciaDto.class;
	}

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createAsistencia(@RequestBody @Valid AsistenciaDto asistenciaDto) throws Exception {
		iAsistenciaService.create(mapFromDtoRequestToEntity(asistenciaDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Asistencia creado exitosamente.").build(), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<AsistenciaDto>> getAllAsistencias() throws Exception {
		List<AsistenciaDto> listaDto = iAsistenciaService.getAll().stream().map(asistencia -> mapFromEntityToDto(asistencia))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<AsistenciaDto>> getAllAsistenciasOfUsuario(@PathVariable(value = "id") Integer usuario_id) throws Exception {
		List<AsistenciaDto> listaDto = iAsistenciaService.asistenciasOfUsuario(usuario_id).stream().map(asistencia-> mapFromEntityToDto(asistencia))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}
	
	@GetMapping("/cantAsistUser/{id}")
	public ResponseEntity<String> cantidadAsistenciasPorEstado(@PathVariable(value = "id") Integer usuario_id) throws Exception {
		Map<String, Integer> contadorAsistenciasUsuario = iAsistenciaService.cantidadAsistenciasPorEstado(usuario_id);
		ObjectMapper mapper = new ObjectMapper();
	    String json = mapper.writeValueAsString(contadorAsistenciasUsuario);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AsistenciaDto> getAsistenciaById(@PathVariable(value = "id") Integer asistencia_id) throws Exception {
		Asistencia paciente = iAsistenciaService.getById(asistencia_id);
		if (paciente == null) {
			throw new ResourceNotFound("Asistencia", "id", asistencia_id);
		}
		return new ResponseEntity<>(mapFromEntityToDto(paciente), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updateAsistenciaById(@RequestBody @Valid AsistenciaDto asistenciaDto) throws Exception {
		Asistencia paciente = iAsistenciaService.getById(asistenciaDto.getAsistencia_id());
		if (paciente == null) {
			throw new ResourceNotFound("Asistencia", "id", asistenciaDto.getAsistencia_id());
		}

		iAsistenciaService.update(mapFromDtoRequestToEntity(asistenciaDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Asistencia actualizado exitosamente.").build(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteAsistenciaById(@PathVariable(value = "id") Integer asistencia_id) throws Exception {
		Asistencia paciente = iAsistenciaService.getById(asistencia_id);
		if (paciente == null) {
			throw new ResourceNotFound("Asistencia", "id", asistencia_id);
		}
		iAsistenciaService.deleteById(asistencia_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Asistencia eliminado exitosamente.").build(), HttpStatus.OK);
	}

	@GetMapping("/verificar/{usuarioId}")
	public ResponseEntity<SuccesMessageDto> verificarAsistenciaUsuarioHoy(@PathVariable(value = "usuarioId") Integer usuario_id) throws Exception {
		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}
		Asistencia asistenciaExiste = iAsistenciaService.verificarAsistenciaUsuario(usuario_id);

		if (asistenciaExiste != null) {
			return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
					.message("SI => El usuario ya ha registrado asistencia hoy.").build(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
					.message("NO => El usuario no ha registrado asistencia hoy.").build(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/registrarConValid/{usuarioId}")
	public ResponseEntity<SuccesMessageDto> registrarAsistenciaConValidaciones(@PathVariable(value = "usuarioId") Integer usuario_id) throws Exception {
		String respuestaRegistro = iAsistenciaService.registrarAsistenciaConValidaciones(usuario_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message(respuestaRegistro).build(), HttpStatus.CREATED);
	}
	
	@PutMapping("/justificarTardanza")
	public ResponseEntity<SuccesMessageDto> justificarTardanza(@RequestBody @Valid JustificacionTardanzaDto justificacionTardanzaDto) throws Exception {
		Usuario usuario = iUsuarioService.getById(justificacionTardanzaDto.getUsuario_id());
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", justificacionTardanzaDto.getUsuario_id());
		}

		String respJustificacion = iAsistenciaService.justificarTardanza(justificacionTardanzaDto);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message(respJustificacion).build(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/genReportAsist/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporteAsistenciaUsuario(@PathVariable(value = "id") Integer usuario_id) throws Exception {
		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}
		byte[] data = null;
		data = iAsistenciaService.generarReporteAsistenciaUsuario(usuario);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
}
