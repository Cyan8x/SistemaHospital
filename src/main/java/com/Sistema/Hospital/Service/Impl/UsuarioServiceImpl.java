package com.Sistema.Hospital.Service.Impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Config.UsuarioActualizadoEvent;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IUsuarioRepository;
import com.Sistema.Hospital.Service.IUsuarioService;

@Service
public class UsuarioServiceImpl extends CRUDServiceImpl<Usuario, Integer> implements UserDetailsService, IUsuarioService {

	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	@Autowired
    private ApplicationEventPublisher eventPublisher;
	
	@Override
	protected IGENERICRepository<Usuario, Integer> getRepo() {
		return iUsuarioRepository;
	}

	@Override
	public Usuario findOneByUsuario(String username) {
		return iUsuarioRepository.findOneByUsuario(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = iUsuarioRepository.findOneByUsuario(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		
		// Verificar si el usuario está activo y dentro del rango de ingreso
//		if (!usuario.getEsActivoUsuario() || !isDentroDelRangoDeIngreso(usuario)) {
//			throw new DisabledException("Usuario no permitido para iniciar sesión");
//		}
		
		if (usuario.getEsActivoUsuario() == false || isDentroDelRangoDeIngreso(usuario) == false) {
			throw new DisabledException("Usuario desactivado o Se encuentra fuera de su horario de acceso.");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();

	    roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombreRol()));

		
		UserDetails ud = new User(usuario.getUsuario(), usuario.getPassword(), usuario.getEsActivoUsuario(), true, true, true, roles);
		
		return ud;
	}

	private boolean isDentroDelRangoDeIngreso(Usuario usuario) {
		// Obtener el día actual
		DayOfWeek diaActual = LocalDate.now().getDayOfWeek();

		// Obtener la hora actual
		LocalTime horaActual = LocalTime.now();

		// Verificar si el usuario está activo en el día actual y dentro del rango de ingreso
		switch (diaActual) {
			case MONDAY:
				return usuario.getEsActivoLunes() && getHoraEnRango(horaActual, usuario.getHoraInicioLunes(), usuario.getHoraFinLunes());
			case TUESDAY:
				return usuario.getEsActivoMartes() && getHoraEnRango(horaActual, usuario.getHoraInicioMartes(), usuario.getHoraFinMartes());
			case WEDNESDAY:
				return usuario.getEsActivoMiercoles() && getHoraEnRango(horaActual, usuario.getHoraInicioMiercoles(), usuario.getHoraFinMiercoles());
			case THURSDAY:
				return usuario.getEsActivoJueves() && getHoraEnRango(horaActual, usuario.getHoraInicioJueves(), usuario.getHoraFinJueves());
			case FRIDAY:
				return usuario.getEsActivoViernes() && getHoraEnRango(horaActual, usuario.getHoraInicioViernes(), usuario.getHoraFinViernes());
			case SATURDAY:
				return usuario.getEsActivoSabado() && getHoraEnRango(horaActual, usuario.getHoraInicioSabado(), usuario.getHoraFinSabado());
			case SUNDAY:
				return usuario.getEsActivoDomingo() && getHoraEnRango(horaActual, usuario.getHoraInicioDomingo(), usuario.getHoraFinDomingo());
			default:
				return false;
		}
	}

	private boolean getHoraEnRango(LocalTime hora, LocalTime horaInicio, LocalTime horaFin) {
		return !hora.isBefore(horaInicio) && !hora.isAfter(horaFin);
	}
	
	@Override
	public Usuario updateUsuario(Usuario usuario){
		Usuario usuarioActualizado = getRepo().save(usuario);
	    
		// Publicar el evento de usuario actualizado
        eventPublisher.publishEvent(new UsuarioActualizadoEvent(this, usuario));

	    return usuarioActualizado;		
	}
}
