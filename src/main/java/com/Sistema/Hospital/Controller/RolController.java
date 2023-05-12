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
import com.Sistema.Hospital.Dto.Rol.RolDto;
import com.Sistema.Hospital.Service.IRolService;

@RestController
@RequestMapping("/hospital/roles")
public class RolController {

	@Autowired
	IRolService iRolService;

	@PostMapping
	public ResponseEntity<SuccesMessageDto> createRol(@RequestBody @Valid RolDto rolDto) {
		return new ResponseEntity<>(iRolService.create(rolDto), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<RolDto>> getAll() {
		return new ResponseEntity<>(iRolService.getAll(), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<RolDto> getById(@PathVariable(value = "id") Integer rol_id) {
		return new ResponseEntity<>(iRolService.getById(rol_id), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<SuccesMessageDto> updateById(@RequestBody @Valid RolDto rolDto) {
		return new ResponseEntity<>(iRolService.updateById(rolDto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccesMessageDto> deleteById(@PathVariable(value = "id") Integer rol_id) {
		return new ResponseEntity<>(iRolService.deleteById(rol_id), HttpStatus.OK);
	}
}
