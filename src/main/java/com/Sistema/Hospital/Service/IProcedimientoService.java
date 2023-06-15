package com.Sistema.Hospital.Service;

import java.util.List;

import com.Sistema.Hospital.Entity.Procedimiento;

public interface IProcedimientoService extends ICRUDService<Procedimiento, Integer> {

	List<Procedimiento> selectProcedimientosPendientesPorPaciente(Integer idPaciente);

	List<Procedimiento> selectProcedimientosPorPaciente(Integer idPaciente);

	List<Procedimiento> selectProcedimientosTerminadosPorPaciente(Integer idPaciente);

	List<Procedimiento> selectProcedimientosPendientesPorUsuarioHoy(Integer usuario_id);
}
