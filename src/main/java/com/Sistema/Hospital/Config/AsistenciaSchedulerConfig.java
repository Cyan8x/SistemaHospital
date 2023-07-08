package com.Sistema.Hospital.Config;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.Sistema.Hospital.Entity.Usuario;
import com.Sistema.Hospital.Service.IAsistenciaService;
import com.Sistema.Hospital.Service.IUsuarioService;

@Component
@Configuration
@EnableScheduling
public class AsistenciaSchedulerConfig implements SchedulingConfigurer, ApplicationListener<UsuarioActualizadoEvent> {

	@Autowired
	private IAsistenciaService iAsistenciaService;

	@Autowired
	private IUsuarioService iUsuarioService;

	@Autowired
	private TaskScheduler taskScheduler;

	private Map<String, ScheduledFuture<?>> scheduledTasks = new HashMap<>();
	
	@Override
    public void onApplicationEvent(UsuarioActualizadoEvent event) {
        Usuario usuario = event.getUsuario();
        crearTareaProgramada(usuario);
    }

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		scheduledTasks.clear(); // Vaciar el mapa al inicio
		try {
			List<Usuario> usuarios = iUsuarioService.getAll();

			for (Usuario usuario : usuarios) {
				if (usuario.getEsActivoUsuario()) {
					for (DayOfWeek dia : DayOfWeek.values()) {
						LocalTime horaSalida  = null;
						boolean   esActivoDia = false;

						switch (dia) {
							case MONDAY:
								horaSalida = usuario.getHoraFinLunes();
								esActivoDia = usuario.getEsActivoLunes();
								break;
							case TUESDAY:
								horaSalida = usuario.getHoraFinMartes();
								esActivoDia = usuario.getEsActivoMartes();
								break;
							case WEDNESDAY:
								horaSalida = usuario.getHoraFinMiercoles();
								esActivoDia = usuario.getEsActivoMiercoles();
								break;
							case THURSDAY:
								horaSalida = usuario.getHoraFinJueves();
								esActivoDia = usuario.getEsActivoJueves();
								break;
							case FRIDAY:
								horaSalida = usuario.getHoraFinViernes();
								esActivoDia = usuario.getEsActivoViernes();
								break;
							case SATURDAY:
								horaSalida = usuario.getHoraFinSabado();
								esActivoDia = usuario.getEsActivoSabado();
								break;
							case SUNDAY:
								horaSalida = usuario.getHoraFinDomingo();
								esActivoDia = usuario.getEsActivoDomingo();
								break;
						}
						// Verifica si el día está activado y la hora de salida no es nula
						if (esActivoDia && horaSalida != null) {
							String key = usuario.getUsuario_id() + "-" + dia;
							// Crea la tarea programada para cada usuario
							ScheduledFuture<?> scheduledTask = taskScheduler.schedule(
									() -> iAsistenciaService.verificarAsistenciaDespuesHorario(usuario.getUsuario_id()),
									new CronTrigger("0 " + horaSalida.getMinute() + " " + horaSalida.getHour() + " * * " + dia.getValue()));

							// Guarda la tarea programada en el mapa específico del usuario
							scheduledTasks.put(key, scheduledTask);
						}
					}

				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Eliminar una tarea programada para un usuario específico
	public void eliminarTareaProgramada(String key) {
	    ScheduledFuture<?> scheduledTask = scheduledTasks.get(key);
	    if (scheduledTask != null) {
	        scheduledTask.cancel(true);
	        scheduledTasks.remove(key);
	    }
	}

	// Crear una tarea programada para un usuario específico
	public void crearTareaProgramada(Usuario usuario) {
		if (usuario.getEsActivoUsuario()) {
			LocalTime horaSalida  = null;
			boolean   esActivoDia = false;

			// Establecer la hora de salida y la activación del día según el día de la semana
			for (DayOfWeek dia : DayOfWeek.values()) {
				switch (dia) {
					case MONDAY:
						horaSalida = usuario.getHoraFinLunes();
						esActivoDia = usuario.getEsActivoLunes();
						break;
					case TUESDAY:
						horaSalida = usuario.getHoraFinMartes();
						esActivoDia = usuario.getEsActivoMartes();
						break;
					case WEDNESDAY:
						horaSalida = usuario.getHoraFinMiercoles();
						esActivoDia = usuario.getEsActivoMiercoles();
						break;
					case THURSDAY:
						horaSalida = usuario.getHoraFinJueves();
						esActivoDia = usuario.getEsActivoJueves();
						break;
					case FRIDAY:
						horaSalida = usuario.getHoraFinViernes();
						esActivoDia = usuario.getEsActivoViernes();
						break;
					case SATURDAY:
						horaSalida = usuario.getHoraFinSabado();
						esActivoDia = usuario.getEsActivoSabado();
						break;
					case SUNDAY:
						horaSalida = usuario.getHoraFinDomingo();
						esActivoDia = usuario.getEsActivoDomingo();
						break;
				}

				// Verificar si el día está activado y la hora de salida no es nula
				if (esActivoDia && horaSalida != null) {
					String key = usuario.getUsuario_id() + "-" + dia;
					
					// Eliminar la tarea programada existente si existe para el usuario
					eliminarTareaProgramada(key);
					// Crea la tarea programada para cada usuario
					ScheduledFuture<?> scheduledTask = taskScheduler.schedule(
							() -> iAsistenciaService.verificarAsistenciaDespuesHorario(usuario.getUsuario_id()),
							new CronTrigger("0 " + horaSalida.getMinute() + " " + horaSalida.getHour() + " * * " + dia.getValue()));

					// Guarda la tarea programada en el mapa específico del usuario
					scheduledTasks.put(key, scheduledTask);
				}
			}

		}
	}
}
