create table oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256)
);

select * from oauth_access_token



select * from usuarios

select * from rol

select * from usuario_menu

select * from menu

INSERT INTO Rol (rol_id, descripcion_rol, nombre_rol) VALUES (1, 'Administrador', 'ADMIN');
INSERT INTO Rol (rol_id, descripcion_rol, nombre_rol) VALUES (2, 'Usuario', 'USER');

INSERT INTO menu(menu_id, nombre_menu, icono_menu, url_menu) VALUES (1, 'Inicio', 'home', '/pages/inicio');
INSERT INTO menu(menu_id, nombre_menu, icono_menu, url_menu) VALUES (2, 'Pacientes', 'accessibility', '/pages/paciente');
INSERT INTO menu(menu_id, nombre_menu, icono_menu, url_menu) VALUES (3, 'Usuarios', 'manage_accounts', '/pages/usuario');
INSERT INTO menu(menu_id, nombre_menu, icono_menu, url_menu) VALUES (4, 'Estados Atencion', 'medication', '/pages/estadoAtencion');
INSERT INTO menu(menu_id, nombre_menu, icono_menu, url_menu) VALUES (5, 'Asistencia', 'approval_delegation', '/pages/asistencia');
INSERT INTO menu(menu_id, nombre_menu, icono_menu, url_menu) VALUES (6, 'Notificaciones', 'notifications', '/pages/notificacion');

INSERT INTO public.usuarios(
	usuario_id, apellidos_usuario, dni_usuario, email_usuario, es_activo_domingo, es_activo_jueves, es_activo_lunes, es_activo_martes, es_activo_miercoles, es_activo_sabado, es_activo_usuario, es_activo_viernes, fecha_creacion_usuario, hora_fin_domingo, hora_fin_jueves, hora_fin_lunes, hora_fin_martes, hora_fin_miercoles, hora_fin_sabado, hora_fin_viernes, hora_inicio_domingo, hora_inicio_jueves, hora_inicio_lunes, hora_inicio_martes, hora_inicio_miercoles, hora_inicio_sabado, hora_inicio_viernes, nombres_usuario, password, telefono_usuario, usuario, rol_id)
	VALUES (1,	'Salinas',	'12312312',	'leonardo@gmail.com',	true,	true,	true,	true,	true,	true,	true,	true, '2023-06-14 23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'00:00:00', '00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'Leonardo',	'$2a$10$ju20i95JTDkRa7Sua63JWOChSBc0MNFtG/6Sps2ahFFqN.HCCUMW.',	'123123123', 'administrador', 1);

INSERT INTO public.usuarios(
	usuario_id, apellidos_usuario, dni_usuario, email_usuario, es_activo_domingo, es_activo_jueves, es_activo_lunes, es_activo_martes, es_activo_miercoles, es_activo_sabado, es_activo_usuario, es_activo_viernes, fecha_creacion_usuario, hora_fin_domingo, hora_fin_jueves, hora_fin_lunes, hora_fin_martes, hora_fin_miercoles, hora_fin_sabado, hora_fin_viernes, hora_inicio_domingo, hora_inicio_jueves, hora_inicio_lunes, hora_inicio_martes, hora_inicio_miercoles, hora_inicio_sabado, hora_inicio_viernes, nombres_usuario, password, telefono_usuario, usuario, rol_id)
	VALUES (2,	'Paullo',	'45645645',	'nicolas@gmail.com',	true,	true,	true,	true,	true,	true,	true,	true, '2023-06-14 23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'23:59:59',	'00:00:00', '00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'00:00:00',	'Nicolas',	'$2a$10$ju20i95JTDkRa7Sua63JWOChSBc0MNFtG/6Sps2ahFFqN.HCCUMW.',	'789789789', 'usuario',2);

INSERT INTO usuario_menu (menu_id, usuario_id) VALUES (1, 1);
INSERT INTO usuario_menu (menu_id, usuario_id) VALUES (2, 1);
INSERT INTO usuario_menu (menu_id, usuario_id) VALUES (3, 1);
INSERT INTO usuario_menu (menu_id, usuario_id) VALUES (4, 1);
INSERT INTO usuario_menu (menu_id, usuario_id) VALUES (5, 1);
INSERT INTO usuario_menu (menu_id, usuario_id) VALUES (6, 1);
INSERT INTO usuario_menu (menu_id, usuario_id) VALUES (1, 2);
INSERT INTO usuario_menu (menu_id, usuario_id) VALUES (2, 2);
INSERT INTO usuario_menu (menu_id, usuario_id) VALUES (6, 2);

INSERT INTO estado_asistencia values (1,'ASISTENCIA');
INSERT INTO estado_asistencia values (2,'TARDANZA');
INSERT INTO estado_asistencia values (3,'TARDANZA JUSTIFICADA');
INSERT INTO estado_asistencia values (4,'FALTA');
INSERT INTO estado_asistencia values (5,'FALTA JUSTIFICADA');

select m.* from usuario_menu um inner join menu m on m.menu_id = um.menu_id where usuario_id = 2

select * from estado_asistencia
select * from asistencia


select (CASE when tab.estado_asistencia_id = 1 then 'Asistencia' 
			 when tab.estado_asistencia_id = 2 then 'Tardanza'
			when tab.estado_asistencia_id = 3 then 'Tardanza J.'
			when tab.estado_asistencia_id = 4 then 'Falta'
			when tab.estado_asistencia_id = 5 then 'Falta J.'
		END) as estado_asistencia, 
		count(tab.asistencia_id) as cant from
(select a.* from asistencia a
inner join estado_asistencia ea on a.estado_asistencia_id = ea.estado_asistencia_id
where usuario_id = 2) tab
group by tab.estado_asistencia_id

select p.* from pacientefavorito_usuario pu inner join pacientes p on p.paciente_id = pu.paciente_id where pu.usuario_id = 1

select * from pacientes

select * from pacientefavorito_usuario

delete from pacientefavorito_usuario where paciente_id = 2 and usuario_id = 1

insert into pacientefavorito_usuario(usuario_id, paciente_id) values (1,8);
insert into pacientefavorito_usuario(usuario_id, paciente_id) values (1,2);