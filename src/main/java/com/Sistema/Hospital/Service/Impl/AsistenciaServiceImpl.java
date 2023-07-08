package com.Sistema.Hospital.Service.Impl;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.AsistenciaResumenUsuarioDto;
import com.Sistema.Hospital.Dto.JustificacionTardanzaDto;
import com.Sistema.Hospital.Entity.Asistencia;
import com.Sistema.Hospital.Entity.EstadoAsistencia;
import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Repository.IAsistenciaRepository;
import com.Sistema.Hospital.Repository.IEstadoAsistenciaRepository;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IUsuarioRepository;
import com.Sistema.Hospital.Service.IAsistenciaService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
	public void verificarAsistenciaDespuesHorario(Integer usuario_id) {
		Usuario       usuario              = iUsuarioRepository.findById(usuario_id).orElse(null);
		Asistencia    asistenciaRegistrada = this.verificarAsistenciaUsuario(usuario_id);
		LocalDateTime now                  = LocalDateTime.now();

		if (asistenciaRegistrada == null) {
			EstadoAsistencia estadoFalta = iEstadoAsistenciaRepository.getById(4);

			if (estadoFalta != null) {
				Asistencia falta = new Asistencia();
				falta.setEstadoAsistencia(estadoFalta);
				falta.setFechaAsistencia(now.toLocalDate());
				falta.setFechaHoraAsistencia(now);
				falta.setUsuario(usuario);

				iAsistenciaRepository.save(falta);
			}
		}

	}

	@Override
	public String registrarAsistenciaConValidaciones(Integer usuario_id) {
		Usuario    usuario              = iUsuarioRepository.findById(usuario_id).orElse(null);
		LocalDate  fechaActual          = LocalDate.now();
		Asistencia asistenciaRegistrada = this.verificarAsistenciaUsuario(usuario_id);

		if (asistenciaRegistrada == null) {
			LocalTime horaEntradaProgramada = this.horaEntradaUsuario(usuario);
			LocalTime horaActual            = LocalTime.now();
			LocalTime horaLimiteTardanza    = horaEntradaProgramada.plusMinutes(15);

			Asistencia asistencia = new Asistencia();
			asistencia.setUsuario(usuario);
			asistencia.setFechaAsistencia(fechaActual);
			asistencia.setFechaHoraAsistencia(LocalDateTime.now());

			if (horaActual.isBefore(horaLimiteTardanza)) {
				// Registrar la asistencia
				EstadoAsistencia estadoAsistencia = iEstadoAsistenciaRepository.getById(1);

				asistencia.setEstadoAsistencia(estadoAsistencia);

				iAsistenciaRepository.save(asistencia);
				return "Asistencia registrada exitosamente.";
			} else {
				// Registrar la tardanza
				EstadoAsistencia estadoTardanza = iEstadoAsistenciaRepository.getById(2);

				asistencia.setEstadoAsistencia(estadoTardanza);

				iAsistenciaRepository.save(asistencia);
				return "Tardanza registrada exitosamente.";
			}
		} else {
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

	@Override
	public String justificarTardanza(JustificacionTardanzaDto justificacionTardanzaDto) {
		System.out.println(justificacionTardanzaDto);
		iUsuarioRepository.findById(justificacionTardanzaDto.getUsuario_id()).orElse(null);
		Asistencia asistenciaRegistrada = this.verificarAsistenciaUsuario(justificacionTardanzaDto.getUsuario_id());

		if (asistenciaRegistrada == null) {
			return "No tiene Asistencia Registrada.";
		}

		asistenciaRegistrada.setJustificacionTardanza(justificacionTardanzaDto.getJustificacion());
		iAsistenciaRepository.save(asistenciaRegistrada);

		return "Se agregó la justificacion a tu tardanza.";
	}

	@Override
	public List<Asistencia> asistenciasOfUsuario(Integer usuario_id) {
		return iAsistenciaRepository.asistenciasOfUsuario(usuario_id);
	}

	@Override
	public Map<String, Integer> cantidadAsistenciasPorEstado(Integer usuario_id) {
		List<Asistencia>     asistenciasUsuario = asistenciasOfUsuario(usuario_id);
		Map<String, Integer> contadores         = new HashMap<>();

		for (Asistencia asistencia : asistenciasUsuario) {
			EstadoAsistencia estadoAsistencia       = asistencia.getEstadoAsistencia();
			String           nombreEstadoAsistencia = estadoAsistencia.getNombreEstadoAsistencia();

			// Verificar si el estadoAsistencia ya está en el mapa
			if (contadores.containsKey(nombreEstadoAsistencia)) {
				// Incrementar el contador existente
				Integer contador = contadores.get(nombreEstadoAsistencia);
				contadores.put(nombreEstadoAsistencia, contador + 1);
			} else {
				// Agregar el estadoAsistencia al mapa con un contador inicial de 1
				contadores.put(nombreEstadoAsistencia, 1);
			}
		}

		return contadores;
	}

	public List<AsistenciaResumenUsuarioDto> asistenciasResumenUsuario(Integer usuario_id) {
		List<Object[]> asistenciasUsuarioResumen = iAsistenciaRepository.asistenciasResumenUsuario(usuario_id);

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

		List<AsistenciaResumenUsuarioDto> asistenciaResumenUsuarioDtoList = asistenciasUsuarioResumen.stream().map(obj -> {
			java.sql.Date      fechaSql       = (java.sql.Date) obj[0];
			LocalDate          fechaLocal     = fechaSql.toLocalDate();
			java.sql.Timestamp fechaHoraSql   = (java.sql.Timestamp) obj[1];
			LocalDateTime      fechaHoraLocal = fechaHoraSql.toLocalDateTime();
			String             nombre         = (String) obj[2];

			String fechaLocalFormatted     = fechaLocal.format(dateFormatter);
			String fechaHoraLocalFormatted = fechaHoraLocal.format(timeFormatter);

			return new AsistenciaResumenUsuarioDto(fechaLocalFormatted, fechaHoraLocalFormatted, nombre);
		}).collect(Collectors.toList());
		return asistenciaResumenUsuarioDtoList;
	}

	@Override
	public byte[] generarReporteAsistenciaUsuario(Usuario usuario) {
		byte[] data = null;

		List<EstadoAsistencia> estadosAsistencia          = iEstadoAsistenciaRepository.findAll();
		Map<String, Integer>   cantAsistPorEstadoYUsuario = cantidadAsistenciasPorEstado(usuario.getUsuario_id());

		Map<String, Object> parametros = new HashMap<>();

		for (EstadoAsistencia estadoAsistencia : estadosAsistencia) {

			switch (estadoAsistencia.getEstado_asistencia_id()) {
				case 1:
					if (cantAsistPorEstadoYUsuario.containsKey(estadoAsistencia.getNombreEstadoAsistencia())) {
						parametros.put("cant_asistencias", cantAsistPorEstadoYUsuario.get(estadoAsistencia.getNombreEstadoAsistencia()));
					} else {
						parametros.put("cant_asistencias", 0);
					}
					break;
				case 2:
					if (cantAsistPorEstadoYUsuario.containsKey(estadoAsistencia.getNombreEstadoAsistencia())) {
						parametros.put("cant_tardanzas", cantAsistPorEstadoYUsuario.get(estadoAsistencia.getNombreEstadoAsistencia()));
					} else {
						parametros.put("cant_tardanzas", 0);
					}
					break;
				case 3:
					if (cantAsistPorEstadoYUsuario.containsKey(estadoAsistencia.getNombreEstadoAsistencia())) {
						parametros.put("cant_tardanzas_justifi", cantAsistPorEstadoYUsuario.get(estadoAsistencia.getNombreEstadoAsistencia()));
					} else {
						parametros.put("cant_tardanzas_justifi", 0);
					}
					break;
				case 4:
					if (cantAsistPorEstadoYUsuario.containsKey(estadoAsistencia.getNombreEstadoAsistencia())) {
						parametros.put("cant_faltas", cantAsistPorEstadoYUsuario.get(estadoAsistencia.getNombreEstadoAsistencia()));
					} else {
						parametros.put("cant_faltas", 0);
					}
					break;
				case 5:
					if (cantAsistPorEstadoYUsuario.containsKey(estadoAsistencia.getNombreEstadoAsistencia())) {
						parametros.put("cant_faltas_justifi", cantAsistPorEstadoYUsuario.get(estadoAsistencia.getNombreEstadoAsistencia()));
					} else {
						parametros.put("cant_faltas_justifi", 0);
					}
					break;
			}

		}
		parametros.put("username", usuario.getUsuario());
		parametros.put("nombre_usuario", usuario.getNombresUsuario());
		parametros.put("apellido_usuario", usuario.getApellidosUsuario());
		parametros.put("dni", usuario.getDniUsuario());
		parametros.put("location_img", "reportes/imagenes/logo2.jpg");

		File file;

		try {
			file = new ClassPathResource("/reportes/asistenciaUsuario.jasper").getFile();
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), parametros,
					new JRBeanCollectionDataSource(asistenciasResumenUsuario(usuario.getUsuario_id())));
			data = JasperExportManager.exportReportToPdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	};

}
