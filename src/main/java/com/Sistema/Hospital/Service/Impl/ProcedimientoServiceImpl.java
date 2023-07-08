package com.Sistema.Hospital.Service.Impl;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.Sistema.Hospital.Dto.ProcedimientoResumenPacienteDto;
import com.Sistema.Hospital.Entity.Paciente;
import com.Sistema.Hospital.Entity.Procedimiento;
import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Repository.IProcedimientoRepository;
import com.Sistema.Hospital.Service.IProcedimientoService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ProcedimientoServiceImpl extends CRUDServiceImpl<Procedimiento, Integer> implements IProcedimientoService {

	@Autowired
	private IProcedimientoRepository iProcedimientoRepository;

	// private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	protected IGENERICRepository<Procedimiento, Integer> getRepo() {
		return iProcedimientoRepository;
	}

	@Override
	public List<Procedimiento> selectProcedimientosPendientesPorPaciente(Integer idPaciente) {
		return iProcedimientoRepository.selectProcedimientosPendientesPorPaciente(idPaciente);
	}

	@Override
	public List<Procedimiento> selectProcedimientosPorPaciente(Integer idPaciente) {
		return iProcedimientoRepository.selectProcedimientosPorPaciente(idPaciente);
	}

	@Override
	public List<Procedimiento> selectProcedimientosTerminadosPorPaciente(Integer idPaciente) {
		return iProcedimientoRepository.selectProcedimientosTerminadosPorPaciente(idPaciente);
	}

	@Override
	public List<Procedimiento> selectProcedimientosPendientesPorUsuario(Integer usuario_id) {
		// LocalDateTime fechaHoraActual = LocalDateTime.now();
		// String fechaHoraFormateada = fechaHoraActual.format(this.formatter);
		// System.out.println(fechaHoraActual);
		// System.out.println(fechaHoraFormateada);
		return iProcedimientoRepository.selectProcedimientosPendientesPorUsuario(usuario_id);
	}

	@Override
	public List<Procedimiento> selectProcedimientosTerminadosPorUsuario(Integer usuario_id) {
		return iProcedimientoRepository.selectProcedimientosTerminadosPorUsuario(usuario_id);
	}

	@Override
	public Map<String, Integer> cantidadTerminadoPendientePorUsuario(Integer usuario_id) {
		List<Procedimiento>  procedimientosTerminados = selectProcedimientosTerminadosPorUsuario(usuario_id);
		List<Procedimiento>  procedimientosPendientes = selectProcedimientosPendientesPorUsuario(usuario_id);
		Map<String, Integer> contadores               = new HashMap<>();

		contadores.put("Completados", procedimientosTerminados.size());
		contadores.put("Pendientes", procedimientosPendientes.size());

		return contadores;
	}

	public List<ProcedimientoResumenPacienteDto> procedimientoResumenPaciente(Integer paciente_id) {
		List<Procedimiento> procedimientos = selectProcedimientosPorPaciente(paciente_id);

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mma");

		List<ProcedimientoResumenPacienteDto> procedimientosResumenPacienteDtoList = procedimientos.stream().map(proced -> {
			String usuario      = proced.getUsuario().getUsuario();
			String es_terminado = "";
			if (proced.getEs_terminado()) {
				es_terminado = "Completado";
			} else {
				es_terminado = "Pendiente";
			}
			String fecha_inicio = proced.getFechaHoraInicio().format(dateTimeFormatter);
			String fecha_fin    = proced.getFechaHoraFin().format(dateTimeFormatter);

			return new ProcedimientoResumenPacienteDto(usuario, proced.getProcedimiento(), es_terminado, fecha_inicio, fecha_fin);
		}).collect(Collectors.toList());
		return procedimientosResumenPacienteDtoList;
	}

	@Override
	public byte[] generarReporteProcedimientosPaciente(Paciente paciente) {
		byte[] data = null;

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("paciente_nombre", paciente.getNombresPaciente());
		parametros.put("paciente_apellido", paciente.getApellidosPaciente());
		parametros.put("tipo_documento", paciente.getTipoDocumento());
		if (paciente.getDniPaciente() != null) {
			parametros.put("nro_documento", paciente.getDniPaciente());
		} else {

			parametros.put("nro_documento", paciente.getCarneExtranjeria());
		}
		parametros.put("direccion_paciente", paciente.getDireccionPaciente());
		parametros.put("email_paciente", paciente.getEmailPaciente());
		parametros.put("telefono_paciente", paciente.getTelefonoPaciente());
		parametros.put("total_procedimientos", (selectProcedimientosTerminadosPorPaciente(paciente.getPaciente_id()).size()
				+ selectProcedimientosPendientesPorPaciente(paciente.getPaciente_id()).size()));
		parametros.put("proced_pendie", selectProcedimientosPendientesPorPaciente(paciente.getPaciente_id()).size());
		parametros.put("proced_termin", selectProcedimientosTerminadosPorPaciente(paciente.getPaciente_id()).size());
		parametros.put("location_img", "reportes/imagenes/logo2.jpg");
		
		File file;

		try {
			file = new ClassPathResource("/reportes/procedimientosPaciente.jasper").getFile();
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), parametros,
					new JRBeanCollectionDataSource(procedimientoResumenPaciente(paciente.getPaciente_id())));
			data = JasperExportManager.exportReportToPdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
