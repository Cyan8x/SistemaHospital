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

import com.Sistema.Hospital.Dto.FaltaDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.Falta;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IFaltaService;

@RestController
@RequestMapping("/hospital/falta")
public class FaltaController extends MAPPERBetweenDtoAndEntity<FaltaDto, Falta>{

	@Autowired
	private IFaltaService iFaltaService;
	
	@Override
	protected Class<Falta> getTClass() {
		return Falta.class;
	}

	@Override
	protected Class<FaltaDto> getDTOClass() {
		return FaltaDto.class;
	}
	
	@PostMapping
	public ResponseEntity<SuccesMessageDto> createFalta(@Valid @RequestBody FaltaDto faltaDto) throws Exception {
		iFaltaService.create(mapFromDtoRequestToEntity(faltaDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Falta creado exitosamente.").build(), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<FaltaDto>> getAllFalta() throws Exception {
		List<FaltaDto> listaDto = iFaltaService.getAll().stream().map(falta -> mapFromEntityToDto(falta))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FaltaDto> getFaltaById(@PathVariable(value = "id") Integer getFalta_id) throws Exception {
		Falta falta = iFaltaService.getById(getFalta_id);
		if (falta == null) {
			throw new ResourceNotFound("Falta", "id", getFalta_id);
		}
		return new ResponseEntity<>(mapFromEntityToDto(falta), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updateFalta(@Valid @RequestBody FaltaDto faltaDto) throws Exception {
		Falta falta = iFaltaService.getById(faltaDto.getFalta_id());
		if (falta == null) {
			throw new ResourceNotFound("Falta", "id", faltaDto.getFalta_id());
		}
		iFaltaService.update(mapFromDtoRequestToEntity(faltaDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Falta actualizado exitosamente.").build(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteFaltaById(@PathVariable(value = "id") Integer getFalta_id) throws Exception {
		Falta falta = iFaltaService.getById(getFalta_id);
		if (falta == null) {
			throw new ResourceNotFound("Falta", "id", getFalta_id);
		}
		iFaltaService.deleteById(getFalta_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Falta eliminado exitosamente.").build(), HttpStatus.OK);
	}

}
