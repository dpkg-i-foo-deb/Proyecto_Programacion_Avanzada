INSERT INTO departamento (nombre) VALUES ("Quindío");

INSERT INTO ciudad (nombre, codigo_departamento) VALUES ("Armenia", 1);

INSERT INTO ciudad (nombre, codigo_departamento) VALUES ("Calarcá", 1);

INSERT INTO persona (tipo, cedula, contrasena, email, nombre_completo, codigo_ciudad)
    VALUES ("Administrador", "1002445182", "admin123", "admin@email.com", "Admin", 1);

INSERT INTO persona (tipo, cedula, contrasena, email, nombre_completo, codigo_ciudad)
    VALUES ("Usuario", "1015553192", "usuario123", "usuario@email.com", "Usuario", 1);

INSERT INTO hotel (direccion, nombre, administrador, codigo_ciudad)
    VALUES("Calle 45-29", "Hotelito", "1002445182", 2);

INSERT INTO habitacion (capacidad, precio, codigo_hotel)
    VALUES(5, 15.00, 1);

INSERT INTO cama (tipo, codigo_habitacion) VALUES ("Cama doble", 1);

INSERT INTO vuelo (aerolinea, ciudad_destino, ciudad_origen)
    VALUES ("Avianca", 2, 1);

INSERT INTO silla (precio, codigo_vuelo) VALUES (5.00, 1);

INSERT INTO reserva (estado_reserva, fecha_llegada, fecha_reserva, fecha_salida, codigo_usuario)
    VALUES ("CONFIRMADA", '2022-04-14', '2022-04-13', '2022-04-25', "1015553192");

INSERT INTO detalle_reserva_habitacion (cantidad_habitaciones, precio, codigo_habitacion, codigo_reserva)
    VALUES (2, 7.00, 1, 1);


INSERT INTO detalle_reserva_silla (cantidad_sillas, precio, codigo_reserva, codigo_silla)
    VALUES (1, 3.00, 1, 1);


INSERT INTO reserva (estado_reserva, fecha_llegada, fecha_reserva, fecha_salida, codigo_usuario)
    VALUES ("CONFIRMADA", '2022-05-05', '2022-05-01', '2022-05-30', "1015553192");