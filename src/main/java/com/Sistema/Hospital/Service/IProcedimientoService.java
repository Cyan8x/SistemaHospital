package com.Sistema.Hospital.Service;

import java.util.List;
import java.util.Map;

import com.Sistema.Hospital.Entity.Paciente;
import com.Sistema.Hospital.Entity.Procedimiento;

public interface IProcedimientoService extends ICRUDService<Procedimiento, Integer> {

	List<Procedimiento> selectProcedimientosPendientesPorPaciente(Integer idPaciente);

	List<Procedimiento> selectProcedimientosPorPaciente(Integer idPaciente);

	List<Procedimiento> selectProcedimientosTerminadosPorPaciente(Integer idPaciente);

	List<Procedimiento> selectProcedimientosPendientesPorUsuario(Integer usuario_id);
	
	List<Procedimiento> selectProcedimientosTerminadosPorUsuario(Integer usuario_id);	

	Map<String, Integer> cantidadTerminadoPendientePorUsuario(Integer usuario_id);
	
	byte[] generarReporteProcedimientosPaciente(Paciente paciente);
}
