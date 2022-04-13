# Proyecto Final Programación Avanzada

Este proyeto representa el codigo que desarrollamos para dar solucion al proyecto final del espacio academico Programación Avanzada

## Los integrantes del grupo son:

#### Stiven Herrera Sierra - Mateo Estrada Ramirez - Sebastian Lugo Mateus

## Descripción del proyecto

## uniTravel

uniTravel es una plataforma que brinda apoyo a las personas para que coticen y programen sus vacaciones en Colombia. En esta plataforma es posible reservar el vuelo (ida y vuelta) y el hotel para su estadía y la de sus acompañantes a algún destino del País. Para esto, la persona debe registrarse, en el registro se le pide su cédula, nombre completo, números de teléfono, ciudad, email y contraseña. Una vez registrado, puede buscar su destino, elegir las habitaciones del hotel que más le agrade y las fechas para su estadía. Cada Hotel cuenta con un nombre, fotos, una ubicación, número de estrellas, unas habitaciones, comentarios y características generales (tiene piscina, buffet, etc.). Cada habitación tiene unas camas (sencilla, doble, cuádruple, etc), fotos, precio y sus propias características (aire acondicionado, balcón, televisión, etc).

Cuando el usuario hace la reserva se debe validar que las fechas estén disponibles, y le debe mostrar el costo total, también se le pueden recomendar vuelos. Haga que la lista de vuelos sea generada de manera aleatoria, así como las sillas para los viajeros (verifique que la silla asignada si esté disponible). Si la persona elige algún vuelo entonces se le debe sumar dicho valor al costo total de la reserva.

El usuario puede comentar los hoteles dejando sus observaciones, y dando una calificación (positiva o negativa del lugar). 

En la página inicial se debe mostrar un buscador y los hoteles por Ubicación, por ejemplo: Eje cafetero, costa atlántica, San Andrés, Boyacá, etc. Cualquier persona puede ver la información de los Hoteles pero para hacer la reserva es necesario registrarse e iniciar sesión. 

En uniTravel se desea contar con una aplicación que maneje tres tipos de persona.

Administrador:
*Loguearse
*Gestionar Destinos
*Gestionar Vuelos
*Gestionar administradores de hoteles.

Administrador del Hotel:
*Loguearse
*Gestionar Hoteles

Usuario:
*Registrarse y loguearse.
*Buscar destinos y/o hoteles.
*Crear una reserva (seleccionando el destino, las habitaciones, vuelo).
*Gestionar sus propias reservas.
*Listar todos los hoteles por cada destino.
*Comentar y calificar Hoteles.
*Recuperar contraseñas usando correo electrónico.
*Listar sus propias reservas.

Para tener en cuenta: 

Se debe calcular de manera automática el valor de la reserva incluyendo el IVA.
Se debe validar automáticamente que las habitaciones están disponibles en las fechas elegidas. 
En la reserva se debe guardar, además del cliente, el hotel y el vuelo, la fecha de reservación, las fechas de estadía y método de pago.
Cada vez que se realice una reserva se debe enviar un correo electrónico que muestre los detalles de la misma. 
Crear una API REST propia que tenga un formato JSON que permita obtener cierta información y ejecutar algunas acciones que más adelante serán reveladas. 
Se debe poder compartir Hoteles en Facebook y Twitter.
