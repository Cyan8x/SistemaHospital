package com.Sistema.Hospital.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.MenuDto;
import com.Sistema.Hospital.Entity.Menu;
import com.Sistema.Hospital.Service.IMenuService;

@RestController
@RequestMapping("/hospital/menu")
public class MenuController extends MAPPERBetweenDtoAndEntity<MenuDto, Menu> {

	@Autowired
	private IMenuService iMenuService;

	@Override
	protected Class<Menu> getTClass() {
		return Menu.class;
	}

	@Override
	protected Class<MenuDto> getDTOClass() {
		return MenuDto.class;
	}

	@GetMapping()
	public ResponseEntity<List<MenuDto>> getAllMenus() throws Exception {
		List<MenuDto> listaDto = iMenuService.getAll().stream().map(menu -> mapFromEntityToDto(menu)).collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@PostMapping("/{usuario}")
	public ResponseEntity<List<MenuDto>> listarMenuPorUsuario(@PathVariable(value = "usuario") String usuario) throws Exception {
		/*
		 * Authentication usuarioLogueado = SecurityContextHolder.getContext().getAuthentication(); List<MenuDto> listaDto =
		 * iMenuService.listarMenuPorUsuario(usuarioLogueado.getName()).stream().map(menu -> mapFromEntityToDto(menu)) .collect(Collectors.toList());
		 */
		
		List<MenuDto> listaDto = iMenuService.listarMenuPorUsuario(usuario).stream().map(menu -> mapFromEntityToDto(menu))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

}
