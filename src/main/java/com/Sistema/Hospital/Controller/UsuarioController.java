package com.Sistema.Hospital.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Dto.Usuario.UsuarioRequestDto;
import com.Sistema.Hospital.Dto.Usuario.UsuarioResponseDto;
import com.Sistema.Hospital.Service.IUsuarioService;

@RestController
@RequestMapping("/hospital/usuarios")
public class UsuarioController {

	@Autowired
	IUsuarioService iUsuarioService;

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createUsuario(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
		return new ResponseEntity<>(iUsuarioService.create(usuarioRequestDto), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<UsuarioResponseDto>> getAllPacientes() {
		return new ResponseEntity<>(iUsuarioService.getAll(), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDto> getPacienteById(@PathVariable(value = "id") Integer usuario_id) {
		return new ResponseEntity<>(iUsuarioService.getById(usuario_id), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updatePacienteById(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
		return new ResponseEntity<>(iUsuarioService.updateById(usuarioRequestDto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deletePacienteById(@PathVariable(value = "id") Integer usuario_id) {
		return new ResponseEntity<>(iUsuarioService.deleteById(usuario_id), HttpStatus.OK);
	}
}
