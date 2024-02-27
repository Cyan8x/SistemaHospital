package com.Sistema.Hospital.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Hospital.Dto.MenuDto;
import com.Sistema.Hospital.Dto.SuccesMessageDto;
import com.Sistema.Hospital.Entity.Menu;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Exception.ResourceNotFound;
import com.Sistema.Hospital.Service.IMenuService;
import com.Sistema.Hospital.Service.IUsuarioService;

@RestController
@RequestMapping("/hospital/menu")
public class MenuController extends MAPPERBetweenDtoAndEntity<MenuDto, Menu> {

	@Autowired
	private IMenuService iMenuService;

	@Autowired
	private IUsuarioService iUsuarioService;
	
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

	@PostMapping("/{username}")
	public ResponseEntity<List<MenuDto>> listarMenuPorUsuario(@PathVariable(value = "username") String username) throws Exception {
		/*
		 * Authentication usuarioLogueado = SecurityContextHolder.getContext().getAuthentication(); List<MenuDto> listaDto =
		 * iMenuService.listarMenuPorUsuario(usuarioLogueado.getName()).stream().map(menu -> mapFromEntityToDto(menu)) .collect(Collectors.toList());
		 */

		List<MenuDto> listaDto = iMenuService.listarMenuPorUsuario(username).stream().map(menu -> mapFromEntityToDto(menu))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@PostMapping("/agregarMenus/{id}")
	public ResponseEntity<SuccesMessageDto> asignarRolesUsuario(@PathVariable(value = "id") Integer usuario_id,
			@RequestBody @Valid List<MenuDto> menusDto) throws Exception {
		Usuario usuario = iUsuarioService.getById(usuario_id);
		if (usuario == null) {
			throw new ResourceNotFound("Usuario", "id", usuario_id);
		}
		List<Menu> menusUsuario = menusDto.stream().map(menuDto -> mapper.map(menuDto, Menu.class)).collect(Collectors.toList());
		iMenuService.asignarMenusUsuario(usuario_id, menusUsuario);
		return new ResponseEntity<>(SuccesMessageDto.builder().statusCode(HttpStatus.CREATED.value()).timestamp(new Date())
				.message("Se asgignaron correctamente los menus.").build(), HttpStatus.CREATED);
	}

}
