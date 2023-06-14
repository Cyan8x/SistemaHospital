INSERT INTO public.usuarios(
	usuario_id, apellidos_usuario, dni_usuario, email_usuario, es_activo_domingo, es_activo_jueves, es_activo_lunes, es_activo_martes, es_activo_miercoles, es_activo_sabado, es_activo_usuario, es_activo_viernes, fecha_creacion_usuario, hora_fin_domingo, hora_fin_jueves, hora_fin_lunes, hora_fin_martes, hora_fin_miercoles, hora_fin_sabado, hora_fin_viernes, hora_inicio_domingo, hora_inicio_jueves, hora_inicio_lunes, hora_inicio_martes, hora_inicio_miercoles, hora_inicio_sabado, hora_inicio_viernes, nombres_usuario, password, telefono_usuario, usuario)
	VALUES (1,	'Salinas',	'12312312',	'leonardo@gmail.com',	true,	true,	true,	true,	true,	true,	true,	true, '2023-06-14 23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'00:00:00', '00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'Leonardo',	'$2a$10$ju20i95JTDkRa7Sua63JWOChSBc0MNFtG/6Sps2ahFFqN.HCCUMW.',	'123123123',	'administrador');

INSERT INTO public.usuarios(
	usuario_id, apellidos_usuario, dni_usuario, email_usuario, es_activo_domingo, es_activo_jueves, es_activo_lunes, es_activo_martes, es_activo_miercoles, es_activo_sabado, es_activo_usuario, es_activo_viernes, fecha_creacion_usuario, hora_fin_domingo, hora_fin_jueves, hora_fin_lunes, hora_fin_martes, hora_fin_miercoles, hora_fin_sabado, hora_fin_viernes, hora_inicio_domingo, hora_inicio_jueves, hora_inicio_lunes, hora_inicio_martes, hora_inicio_miercoles, hora_inicio_sabado, hora_inicio_viernes, nombres_usuario, password, telefono_usuario, usuario)
	VALUES (2,	'Paullo',	'45645645',	'nicolas@gmail.com',	true,	true,	true,	true,	true,	true,	true,	true, '2023-06-14 23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'00:00:00', '00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'Nicolas',	'$2a$10$ju20i95JTDkRa7Sua63JWOChSBc0MNFtG/6Sps2ahFFqN.HCCUMW.',	'789789789',	'usuario');


select * from usuarios

select * from rol

select * from usuario_rol

select * from menu

select * from menu_rol

INSERT INTO Rol (rol_id, descripcion_rol, nombre_rol) VALUES (1, 'Administrador', 'ADMIN');
INSERT INTO Rol (rol_id, descripcion_rol, nombre_rol) VALUES (2, 'Usuario', 'USER');
INSERT INTO Rol (rol_id, descripcion_rol, nombre_rol) VALUES (3, 'Admin de bd', 'DBA');

INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 3);
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (2, 2);

INSERT INTO menu(menu_id, nombre_menu, icono_menu, url_menu) VALUES (1, 'Inicio', 'home', '/pages/inicio');
INSERT INTO menu(menu_id, nombre_menu, icono_menu, url_menu) VALUES (2, 'Pacientes', 'accessibility', '/pages/paciente');
INSERT INTO menu(menu_id, nombre_menu, icono_menu, url_menu) VALUES (3, 'Usuarios', 'manage_accounts', '/pages/usuario');
INSERT INTO menu(menu_id, nombre_menu, icono_menu, url_menu) VALUES (4, 'Estados Atencion', 'medication', '/pages/estadoAtencion');

INSERT INTO menu_rol (menu_id, rol_id) VALUES (1, 1);
INSERT INTO menu_rol (menu_id, rol_id) VALUES (2, 1);
INSERT INTO menu_rol (menu_id, rol_id) VALUES (3, 1);
INSERT INTO menu_rol (menu_id, rol_id) VALUES (4, 1);
INSERT INTO menu_rol (menu_id, rol_id) VALUES (1, 2);

select * from estado_asistencia

INSERT INTO estado_asistencia values (1,'ASISTENCIA');
INSERT INTO estado_asistencia values (2,'TARDANZA');
INSERT INTO estado_asistencia values (3,'TARDANZA JUSTIFICADA');