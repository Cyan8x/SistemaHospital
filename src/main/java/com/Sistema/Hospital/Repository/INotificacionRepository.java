package com.Sistema.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Sistema.Hospital.Entity.Notificacion;

public interface INotificacionRepository extends IGENERICRepository<Notificacion, Integer> {

	@Query(value = "select count(*) from notificacion where usuario_destino = :usuarioDestino", nativeQuery = true)
	Integer cantidadNotificacionesPorUsuario(@Param("usuarioDestino") Integer usuarioDestino);

	@Query(value = "select * from notificacion where usuario_destino = :usuarioDestino", nativeQuery = true)
	List<Notificacion> selectNotificacionesPorUsuario(@Param("usuarioDestino") Integer usuarioDestino);
}
