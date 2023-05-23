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

import com.Sistema.Hospital.Dto.ProcedimientoDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.Procedimiento;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IProcedimientoService;

@RestController
@RequestMapping("/hospital/procedimientos")
public class ProcedimientoController extends MAPPERBetweenDtoAndEntity<ProcedimientoDto, Procedimiento> {

	@Autowired
	private IProcedimientoService iProcedimientoService;
	
	@Override
	protected Class<Procedimiento> getTClass() {
		return Procedimiento.class;
	}

	@Override
	protected Class<ProcedimientoDto> getDTOClass() {
		return ProcedimientoDto.class;
	}

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createProcedimiento(@RequestBody @Valid ProcedimientoDto procedimientoDto) throws Exception {
		iProcedimientoService.create(mapFromDtoRequestToEntity(procedimientoDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Procedimiento creado exitosamente.").build(), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<ProcedimientoDto>> getAllProcedimientos() throws Exception {
		List<ProcedimientoDto> listaDto = iProcedimientoService.getAll().stream().map(procedimientoDto -> mapFromEntityToDto(procedimientoDto)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProcedimientoDto> getProcedimientoById(@PathVariable(value = "id") Integer procedimientoDto_id) throws Exception {
		Procedimiento procedimiento = iProcedimientoService.getById(procedimientoDto_id);
		if (procedimiento == null) {
			throw new ResourceNotFound("Procedimiento", "id", procedimientoDto_id);
		}
		return new ResponseEntity<>(mapFromEntityToDto(procedimiento), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updateProcedimientoById(@RequestBody @Valid ProcedimientoDto procedimientoDto) throws Exception {
		Procedimiento procedimiento = iProcedimientoService.getById(procedimientoDto.getProcedimiento_id());
		if (procedimiento == null) {
			throw new ResourceNotFound("Procedimiento", "id", procedimientoDto.getProcedimiento_id());
		}

		iProcedimientoService.update(mapFromDtoRequestToEntity(procedimientoDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Procedimiento actualizado exitosamente.").build(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteProcedimientoById(@PathVariable(value = "id") Integer procedimientoDto_id) throws Exception {
		Procedimiento procedimiento = iProcedimientoService.getById(procedimientoDto_id);
		if (procedimiento == null) {
			throw new ResourceNotFound("Procedimiento", "id", procedimientoDto_id);
		}
		iProcedimientoService.deleteById(procedimientoDto_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Procedimiento eliminado exitosamente.").build(), HttpStatus.OK);
	}
}
