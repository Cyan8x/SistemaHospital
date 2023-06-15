package com.Sistema.Hospital.Service.Impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Entity.Asistencia;
import com.Sistema.Hospital.Entity.EstadoAsistencia;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Repository.IAsistenciaRepository;
import com.Sistema.Hospital.Repository.IEstadoAsistenciaRepository;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IUsuarioRepository;
import com.Sistema.Hospital.Service.IAsistenciaService;

@Service
public class AsistenciaServiceImpl extends CRUDServiceImpl<Asistencia, Integer> implements IAsistenciaService {

	@Autowired
	private IAsistenciaRepository iAsistenciaRepository;
	
	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	@Autowired
	private IEstadoAsistenciaRepository iEstadoAsistenciaRepository;

	@Override
	protected IGENERICRepository<Asistencia, Integer> getRepo() {
		return iAsistenciaRepository;
	}

	@Override
	public Asistencia verificarAsistenciaUsuario(Integer usuario_id) {
		LocalDate fechaActual = LocalDate.now();
		return iAsistenciaRepository.verificarAsistenciaUsuario(usuario_id, fechaActual);
	}

	@Override
	public String registrarAsistenciaConValidaciones(Integer usuario_id) {
		Usuario usuario = iUsuarioRepository.findById(usuario_id).orElse(null);
		LocalDate fechaActual = LocalDate.now();
		Asistencia asistenciaRegistrada = this.verificarAsistenciaUsuario(usuario_id);
		
		if(asistenciaRegistrada==null) {
            LocalTime horaEntradaProgramada = this.horaEntradaUsuario(usuario);
            LocalTime horaActual = LocalTime.now();
            LocalTime horaLimiteTardanza = horaEntradaProgramada.plusMinutes(15);
            
            Asistencia asistencia = new Asistencia();
            asistencia.setUsuario(usuario);
        	asistencia.setFechaAsistencia(fechaActual);
            asistencia.setFechaHoraAsistencia(LocalDateTime.now());
            
            if (horaActual.isBefore(horaLimiteTardanza)) {
                // Registrar la asistencia
            	EstadoAsistencia estadoAsistencia = iEstadoAsistenciaRepository.getById(1);
            	
                asistencia.setEstadoAsistencia(estadoAsistencia);

                iAsistenciaRepository.save(asistencia);
            } else {
            	// Registrar la tardanza
            	EstadoAsistencia estadoTardanza = iEstadoAsistenciaRepository.getById(2);
            	
                asistencia.setEstadoAsistencia(estadoTardanza);

                iAsistenciaRepository.save(asistencia);
            }
            return "Asistencia registrada exitosamente.";
		}else {
			return "Ya ha marcado asistecia.";
		}
	}
	
	private LocalTime horaEntradaUsuario(Usuario usuario) {
		DayOfWeek diaActual = LocalDate.now().getDayOfWeek();

		switch (diaActual) {
			case MONDAY:
				return usuario.getHoraInicioLunes();
			case TUESDAY:
				return usuario.getHoraInicioMartes();
			case WEDNESDAY:
				return usuario.getHoraInicioMiercoles();
			case THURSDAY:
				return usuario.getHoraInicioJueves();
			case FRIDAY:
				return usuario.getHoraInicioViernes();
			case SATURDAY:
				return usuario.getHoraInicioSabado();
			case SUNDAY:
				return usuario.getHoraInicioDomingo();
			default:
				return null;
		}
	}

}
