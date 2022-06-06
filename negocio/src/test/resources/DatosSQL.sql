-- Departamentos
INSERT INTO departamento (nombre) VALUES("Antioquia");
INSERT INTO departamento (nombre) VALUES("Caldas");
INSERT INTO departamento (nombre) VALUES("Quindío");

-- Ciudades
INSERT INTO ciudad (nombre, codigo_departamento) VALUES("Medellín", 1);
INSERT INTO ciudad (nombre, codigo_departamento) VALUES("Turbo", 1);
INSERT INTO ciudad (nombre, codigo_departamento) VALUES("Bello", 1);

INSERT INTO ciudad (nombre, codigo_departamento) VALUES("Manizales", 2);
INSERT INTO ciudad (nombre, codigo_departamento) VALUES("La Dorada", 2);
INSERT INTO ciudad (nombre, codigo_departamento) VALUES("Neira", 2);

INSERT INTO ciudad (nombre, codigo_departamento) VALUES("Armenia", 3);
INSERT INTO ciudad (nombre, codigo_departamento) VALUES("Calarcá", 3);
INSERT INTO ciudad (nombre, codigo_departamento) VALUES("Salento", 3);

-- Administradores de hotel
INSERT INTO persona (tipo, cedula, contrasena, email, nombre_completo, codigo_ciudad) VALUES ( "admin_hotel", "24680", "admin123", "hotel123@email.com", "Administrador Hotel", 1);
INSERT INTO persona (tipo, cedula, contrasena, email, nombre_completo, codigo_ciudad) VALUES ( "admin_hotel", "13579", "nuevo123", "nuevo.hotel@email.com", "Nuevo Administrador Hotel", 4);
INSERT INTO persona (tipo, cedula, contrasena, email, nombre_completo, codigo_ciudad) VALUES ( "admin_hotel", "11111", "pacho123", "pacho@email.com", "Francisco Pérez", 1);
INSERT INTO persona (tipo, cedula, contrasena, email, nombre_completo, codigo_ciudad) VALUES ( "admin_hotel", "22222", "lucho123", "lucho@email.com", "Luis García", 1);
INSERT INTO persona (tipo, cedula, contrasena, email, nombre_completo, codigo_ciudad) VALUES ( "admin_hotel", "33333", "hugo123", "hugo@email.com", "Hugo Mesa", 2);

-- Usuarios
INSERT INTO persona (tipo, cedula, contrasena, email, nombre_completo, codigo_ciudad) VALUES ( "usuario", "44444", "miguel123", "miguel@email.com", "Miguel Toro", 1);
INSERT INTO persona (tipo, cedula, contrasena, email, nombre_completo, codigo_ciudad) VALUES ( "usuario", "55555", "stiven123", "herreras.stiven@gmail.com", "Stiven Herrera", 8);

-- Hoteles
INSERT INTO hotel (direccion, estado_hotel, nombre, numero_estrellas, telefono, administrador, codigo_ciudad) VALUES ( "Calle 25", "DISPONIBLE", "Hotel Paisa", 5, "7123535", "11111", 1);
INSERT INTO hotel (direccion, estado_hotel, nombre, numero_estrellas, telefono, administrador, codigo_ciudad) VALUES ( "Calle 37", "DISPONIBLE", "Hotel El Peñol", 3, "7281212", "22222", 1);
INSERT INTO hotel (direccion, estado_hotel, nombre, numero_estrellas, telefono, administrador, codigo_ciudad) VALUES ( "Calle 12", "DISPONIBLE", "Hotel Caldas", 4, "7428182", "33333", 4);

-- Habitaciones
INSERT INTO habitacion (capacidad, precio, codigo_hotel) VALUES (2, 15000.00, 1);
INSERT INTO habitacion (capacidad, precio, codigo_hotel) VALUES (5, 17000.00, 1);
INSERT INTO habitacion (capacidad, precio, codigo_hotel) VALUES (1, 10000.00, 1);

INSERT INTO habitacion (capacidad, precio, codigo_hotel) VALUES (10, 45000.00, 2);
INSERT INTO habitacion (capacidad, precio, codigo_hotel) VALUES (12, 150000.00, 2);
INSERT INTO habitacion (capacidad, precio, codigo_hotel) VALUES (11, 100000.00, 2);

INSERT INTO habitacion (capacidad, precio, codigo_hotel) VALUES (4, 65000.00, 3);
INSERT INTO habitacion (capacidad, precio, codigo_hotel) VALUES (8, 32000.00, 3);
INSERT INTO habitacion (capacidad, precio, codigo_hotel) VALUES (9, 20000.00, 3);

-- Camas
INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Sencilla", 1);
INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Doble", 1);

INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Sencilla", 2);
INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Doble", 2);

INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Sencilla", 3);
INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Doble", 3);

INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Sencilla", 4);
INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Doble", 4);

INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Sencilla", 5);
INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Doble", 5);

INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Sencilla", 6);
INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Doble", 6);

INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Sencilla", 7);
INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Doble", 7);

INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Sencilla", 8);
INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Doble", 8);

INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Sencilla", 9);
INSERT INTO cama(tipo, codigo_habitacion) VALUES ("Doble", 9);

-- Comentarios
INSERT INTO comentario(calificacion, fecha, observacion, cedula_usuario, codigo_hotel) VALUES (4, "2021-07-30", "Me gustó", "44444", 1);
INSERT INTO comentario(calificacion, fecha, observacion, cedula_usuario, codigo_hotel) VALUES (2, "2021-07-30", "Comida vencida", "55555", 1);
INSERT INTO comentario(calificacion, fecha, observacion, cedula_usuario, codigo_hotel) VALUES (2, "2020-07-20", "Mala atención", "44444", 2);

-- Reservas
INSERT INTO reserva(estado_reserva, fecha_llegada, fecha_reserva, fecha_salida, codigo_usuario) VALUES ("CONFIRMADA", "2022-07-10", "2022-04-30", "2022-12-31", 55555);
INSERT INTO reserva(estado_reserva, fecha_llegada, fecha_reserva, fecha_salida, codigo_usuario) VALUES ("CONFIRMADA", "2022-06-10", "2022-04-30", "2022-12-31", 55555);
INSERT INTO reserva(estado_reserva, fecha_llegada, fecha_reserva, fecha_salida, codigo_usuario) VALUES ("CONFIRMADA", "2022-04-10", "2022-03-05", "2022-11-25", 55555);
INSERT INTO reserva(estado_reserva, fecha_llegada, fecha_reserva, fecha_salida, codigo_usuario) VALUES ("FINALIZADA", "2022-04-10", "2022-03-05", "2022-12-18", 44444);

-- Reserva-Habitacion
INSERT INTO detalle_reserva_habitacion(cantidad_habitaciones, precio, codigo_habitacion, codigo_reserva) VALUES (2, 150000, 1, 1);
INSERT INTO detalle_reserva_habitacion(cantidad_habitaciones, precio, codigo_habitacion, codigo_reserva) VALUES (1, 270000, 1, 2);
INSERT INTO detalle_reserva_habitacion(cantidad_habitaciones, precio, codigo_habitacion, codigo_reserva) VALUES (3, 20000, 1, 1);
INSERT INTO detalle_reserva_habitacion(cantidad_habitaciones, precio, codigo_habitacion, codigo_reserva) VALUES (2, 17000, 2, 2);
INSERT INTO detalle_reserva_habitacion(cantidad_habitaciones, precio, codigo_habitacion, codigo_reserva) VALUES (2, 19000, 2, 4);

-- Vuelos
INSERT INTO vuelo(aerolinea, estado, ciudad_destino, ciudad_origen) VALUES ("Avianca", "CONFIRMADO", 1, 4);
INSERT INTO vuelo(aerolinea, estado, ciudad_destino, ciudad_origen) VALUES ("Avianca", "CONFIRMADO", 8, 4);
INSERT INTO vuelo(aerolinea, estado, ciudad_destino, ciudad_origen) VALUES ("Avianca", "CONFIRMADO", 1, 6);
INSERT INTO vuelo(aerolinea, estado, ciudad_destino, ciudad_origen) VALUES ("Avianca", "CONFIRMADO", 9, 3);

-- Sillas
INSERT INTO silla(precio, codigo_vuelo) VALUES (27000, 1);
INSERT INTO silla(precio, codigo_vuelo) VALUES (18000, 1);
INSERT INTO silla(precio, codigo_vuelo) VALUES (36000, 2);

-- Reservas-Sillas
INSERT INTO detalle_reserva_silla(precio, codigo_reserva, codigo_silla) VALUES (25000, 1, 1);
INSERT INTO detalle_reserva_silla(precio, codigo_reserva, codigo_silla) VALUES (18000, 1, 2);
INSERT INTO detalle_reserva_silla(precio, codigo_reserva, codigo_silla) VALUES (41000, 4, 3);

-- Hotel favorito
INSERT INTO persona_hoteles_favoritos VALUES("44444", 1);
INSERT INTO persona_hoteles_favoritos VALUES("44444", 2);
INSERT INTO persona_hoteles_favoritos VALUES("55555", 2);
INSERT INTO persona_hoteles_favoritos VALUES("55555", 3);

-- Características
INSERT INTO caracteristica VALUES(0, 'Piscina');
INSERT INTO caracteristica VALUES(0, 'Helipuerto');
INSERT INTO caracteristica VALUES(0, 'Fuente de agua');

-- Características hotel
INSERT INTO hotel_lista_caracteristicas VALUES(1, 1);
INSERT INTO hotel_lista_caracteristicas VALUES(1, 2);
INSERT INTO hotel_lista_caracteristicas VALUES(2, 1);