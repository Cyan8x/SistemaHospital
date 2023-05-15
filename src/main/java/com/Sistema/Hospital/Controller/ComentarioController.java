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

import com.Sistema.Hospital.Dto.ComentarioDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.Comentario;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IComentarioService;

public class ComentarioController extends MAPPERBetweenDtoAndEntity<ComentarioDto, Comentario> {

	@Autowired
	private IComentarioService iComentarioService;

	@Override
	protected Class<Comentario> getTClass() {
		return Comentario.class;
	}

	@Override
	protected Class<ComentarioDto> getDTOClass() {
		return ComentarioDto.class;
	}

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createComentario(@RequestBody @Valid ComentarioDto comentarioDto) throws Exception {
		iComentarioService.create(mapFromDtoRequestToEntity(comentarioDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Comentario creado exitosamente.").build(), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<ComentarioDto>> getAllComentarios() throws Exception {
		List<ComentarioDto> listaDto = iComentarioService.getAll().stream().map(comentario -> mapFromEntityToDto(comentario)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComentarioDto> getComentarioById(@PathVariable(value = "id") Integer comentario_id) throws Exception {
		Comentario comentario = iComentarioService.getById(comentario_id);
		if (comentario == null) {
			throw new ResourceNotFound("Comentario", "id", comentario_id);
		}
		return new ResponseEntity<>(mapFromEntityToDto(comentario), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updateComentarioById(@RequestBody @Valid ComentarioDto comentarioDto) throws Exception {
		Comentario comentario = iComentarioService.getById(comentarioDto.getComentario_id());
		if (comentario == null) {
			throw new ResourceNotFound("Comentario", "id", comentarioDto.getComentario_id());
		}

		iComentarioService.update(mapFromDtoRequestToEntity(comentarioDto));
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Comentario actualizado exitosamente.").build(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteComentarioById(@PathVariable(value = "id") Integer comentario_id) throws Exception {
		Comentario comentario = iComentarioService.getById(comentario_id);
		if (comentario == null) {
			throw new ResourceNotFound("Comentario", "id", comentario_id);
		}
		iComentarioService.deleteById(comentario_id);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(new Date())
				.message("Comentario eliminado exitosamente.").build(), HttpStatus.OK);
	}
}
