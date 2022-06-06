package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.implementacion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatosIniciales implements CommandLineRunner{

    @Autowired
    private DepartamentoServicioImpl departamentoServicio;

    @Autowired
    private CiudadServicioImpl ciudadServicio;

    @Autowired
    private AdministradorServicioImpl administradorServicio;

    @Autowired
    private AdministradorHotelServicioImpl administradorHotelServicio;

    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @Autowired
    private HotelServicioImpl hotelServicio;

    @Autowired
    private HabitacionServicioImpl habitacionServicio;

    @Autowired
    private CamaServicioImpl camaServicio;

    @Autowired
    private ComentarioServicioImpl comentarioServicio;

    public void run(String... args) throws Exception{

        //Departamentos
        Departamento departamento_1 = new Departamento("Antioquia");
        Departamento departamento_2 = new Departamento("Armenia");
        departamentoServicio.registrarDepartamento(departamento_1);
        departamentoServicio.registrarDepartamento(departamento_2);

        //Ciudades
        Ciudad ciudad_1 = new Ciudad("Medellin", departamento_1);
        Ciudad ciudad_2 = new Ciudad("Yarumal", departamento_1);
        Ciudad ciudad_3 = new Ciudad("Armenia", departamento_2);
        Ciudad ciudad_4 = new Ciudad("Calarcá", departamento_2);
        ciudadServicio.registrarCiudad(ciudad_1);
        ciudadServicio.registrarCiudad(ciudad_2);
        ciudadServicio.registrarCiudad(ciudad_3);
        ciudadServicio.registrarCiudad(ciudad_4);

        //Personas
        Persona_Administrador administrador = new Persona_Administrador(
                "24561875", "Cristian Pérez",
                "cristian@email.com", "Cristian_123", ciudad_1
        );
        administradorServicio.registrarAdministrador(administrador);

        Persona_Administrador_Hotel administradorHotel_1 = new Persona_Administrador_Hotel(
                "44261588", "Ana Martinez",
                "ana@email.com", "Ana123", ciudad_2
        );
        Persona_Administrador_Hotel administradorHotel_2 = new Persona_Administrador_Hotel(
                "55648745", "Maria Mendez",
                "maria@email.com", "Maria123", ciudad_3
        );
        administradorHotelServicio.registrarAdministradorHotel(administradorHotel_1);
        administradorHotelServicio.registrarAdministradorHotel(administradorHotel_2);

        Persona_Usuario usuario_1 = new Persona_Usuario(
                "1012564879", "Germán Rodriguez",
                "german@gmail.com", "German@123", ciudad_4
        );
        Persona_Usuario usuario_2 = new Persona_Usuario(
                "1002268879", "Alicia Rios",
                "alicia@gmail.com", "AliRios@123", ciudad_4
        );
        usuarioServicio.registrarUsuario(usuario_1);
        usuarioServicio.registrarUsuario(usuario_2);

        //Hoteles
        Hotel hotel_1 = new Hotel(
                "Hotel Resplandor", (short)4, "Cl. 38 #24-37",
                ciudad_1, administradorHotel_1, EstadoHotel.DISPONIBLE
        );
        Hotel hotel_2 = new Hotel(
                "Hotel Caminante", (short)5, "Cl. 44 #23-28",
                ciudad_1, administradorHotel_2, EstadoHotel.DISPONIBLE
        );
        hotelServicio.registrarHotel(hotel_1);
        hotelServicio.registrarHotel(hotel_2);

        //Caracteristicas
        List<Caracteristica> caracteristicas = new ArrayList<>();
        caracteristicas.add(new Caracteristica(0, "Piscina"));
        caracteristicas.add(new Caracteristica(1, "Bar"));
        hotel_1.setListaCaracteristicas(caracteristicas);
        hotelServicio.editarHotel(hotel_1);

        caracteristicas.add(new Caracteristica(1, "Cine"));
        hotel_2.setListaCaracteristicas(caracteristicas);
        hotelServicio.editarHotel(hotel_2);

        //Habitaciones
        Habitacion habitacion_1 = new Habitacion(520_000.0, 2, hotel_1);
        Habitacion habitacion_2 = new Habitacion(320_000.0, 1, hotel_1);
        Habitacion habitacion_3 = new Habitacion(420_000.0, 2, hotel_2);
        Habitacion habitacion_4 = new Habitacion(720_000.0, 5, hotel_2);
        habitacionServicio.registrarHabitacion(habitacion_1);
        habitacionServicio.registrarHabitacion(habitacion_2);
        habitacionServicio.registrarHabitacion(habitacion_3);
        habitacionServicio.registrarHabitacion(habitacion_4);

        //Camas
        Cama cama_1 = new Cama("Sencilla", habitacion_1);
        Cama cama_2 = new Cama("Doble", habitacion_2);
        Cama cama_3 = new Cama("Matrimonial", habitacion_2);
        Cama cama_4 = new Cama("Doble", habitacion_3);
        Cama cama_5 = new Cama("Sencilla", habitacion_3);
        Cama cama_6 = new Cama("Matrimonial", habitacion_4);
        camaServicio.registrarCama(cama_1);
        camaServicio.registrarCama(cama_2);
        camaServicio.registrarCama(cama_3);
        camaServicio.registrarCama(cama_4);
        camaServicio.registrarCama(cama_5);
        camaServicio.registrarCama(cama_6);

        //Comentarios
        Comentario comentario_1 = new Comentario(
                "La comida sabia extraño", (short) 2,
                Date.valueOf(LocalDate.now()), usuario_1, hotel_1
        );
        Comentario comentario_2 = new Comentario(
                "Excelente servicio", (short) 5,
                Date.valueOf(LocalDate.now().minusMonths(1)), usuario_2, hotel_2
        );
        Comentario comentario_3 = new Comentario(
                "Regular el paisaje", (short) 3,
                Date.valueOf(LocalDate.now().minusMonths(5)), usuario_1, hotel_2
        );
        comentarioServicio.crearComentario(comentario_1);
        comentarioServicio.crearComentario(comentario_2);
        comentarioServicio.crearComentario(comentario_3);

        //Favoritos
        List<Hotel> hotelesFavoritos_1 = new ArrayList<>();
        hotelesFavoritos_1.add(hotel_1);
        usuario_1.setHotelesFavoritos(hotelesFavoritos_1);
        usuarioServicio.actualizarUsuario(usuario_1);
        List<Hotel> hotelesFavoritos_2 = new ArrayList<>();
        hotelesFavoritos_2.add(hotel_2);
        usuario_2.setHotelesFavoritos(hotelesFavoritos_2);
        usuarioServicio.actualizarUsuario(usuario_2);

    }

}
