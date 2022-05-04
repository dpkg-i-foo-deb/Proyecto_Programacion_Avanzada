package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.excepciones.*;
import co.edu.uniquindio.proyecto.servicios.implementacion.*;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import java.sql.Date;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ReservaServicioTest
{
    @Autowired
    private ReservaServicioImpl reservaServicio;

    @Autowired
    private DepartamentoServicioImpl departamentoServicio;

    @Autowired
    private CiudadServicioImpl ciudadServicio;

    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @Autowired
    private HotelServicioImpl hotelServicio;

    @Autowired
    private HabitacionServicioImpl habitacionServicio;

    @Autowired
    private SillaServicioImpl sillaServicio;

    @Autowired
    private AdministradorHotelServicioImpl administradorHotelServicio;

    @Autowired
    private VueloServicioImpl vueloServicio;

    private Departamento departamento;
    private Ciudad ciudad;
    private Ciudad ciudad1;
    private Persona_Usuario usuario;
    private Silla silla;
    private Hotel hotel;
    private Habitacion habitacion;
    private Persona_Administrador_Hotel administrador_hotel;

    private Vuelo vuelo;

    private void crearDepartamento()
    {
        departamento = new Departamento();
        departamento.setNombre("Quindio");

        departamentoServicio.registrarDepartamento(departamento);
    }

    private void crearCiudad()
    {
        ciudad = new Ciudad();
        ciudad.setNombre("Armenia");
        ciudad.setDepartamento(departamento);

        ciudad1 = new Ciudad();
        ciudad1.setNombre("Montenegro");
        ciudad1.setDepartamento(departamento);

        ciudadServicio.registrarCiudad(ciudad);
        ciudadServicio.registrarCiudad(ciudad1);
    }

    private void crearUsuario()
    {
        usuario = new Persona_Usuario();

        usuario.setNombreCompleto("Mateo Estrada Ramirez");
        usuario.setCedula("1010383430");
        usuario.setCiudad(ciudad);
        usuario.setContrasena("238283");
        usuario.setEmail("someone@example.com");

        try {
            usuarioServicio.registrarUsuario(usuario);
        } catch (UsuarioException e) {
           System.out.print(e.getMessage());
        }
    }

    private void crearAdministrador()
    {
        administrador_hotel = new Persona_Administrador_Hotel();
        administrador_hotel.setCedula("11");
        administrador_hotel.setNombreCompleto("Stiven Herrera Sierra");
        administrador_hotel.setContrasena("1234");
        administrador_hotel.setEmail("someone2@example.com");
        administrador_hotel.setCiudad(ciudad);

        try {
            administradorHotelServicio.registrarAdministradorHotel(administrador_hotel);
        } catch (AdministradorHotelException e) {
            System.out.print(e.getMessage());
        }
    }

    private void crearHotel()
    {
        hotel = new Hotel();
        hotel.setNombre("hotelito");
        hotel.setCiudad(ciudad);

        hotel.setAdministrador(administrador_hotel);

        hotel.setDireccion("CLL");
        hotel.setTelefono("123455");
        hotel.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel.setNumeroEstrellas((short) 1);


        try {
            hotelServicio.registrarHotel(hotel);
        } catch (HotelException e) {
            throw new RuntimeException(e);
        }

    }

    private void crearHabitacion()
    {
        habitacion = new Habitacion();
        habitacion.setHotel(hotel);
        habitacion.setCapacidad(2);
        habitacion.setPrecio(100000.0);

        habitacionServicio.registrarHabitacion(habitacion);
    }

    private void crearVuelo()
    {
        vuelo = new Vuelo();
        vuelo.setCiudadOrigen(ciudad);
        vuelo.setCiudadDestino(ciudad1);
        vuelo.setAerolinea("Avianca");
        vuelo.setEstado(EstadoVuelo.CONFIRMADO);

        try {
            vueloServicio.crearVuelo(vuelo);
        } catch (VueloException e) {
            System.out.print(e.getMessage());
        }
    }

    private void crearSilla()
    {
        silla = new Silla();
        silla.setPrecio(100.00);
        silla.setVuelo(vuelo);

        sillaServicio.crearSilla(silla);
    }

    @Test
    public void crearReservaTest() {
        crearDepartamento();
        crearCiudad();
        crearUsuario();
        crearAdministrador();
        crearHotel();
        crearVuelo();
        crearSilla();
        crearHabitacion();

        Date fechaEntrada;
        Date fechaSalida;
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        List<Silla> listaSillas = new ArrayList<>();
        Reserva reserva = null;

        fechaEntrada = Date.valueOf(LocalDate.now().plusDays(2));
        fechaSalida = Date.valueOf(LocalDate.now().plusDays(30));

        listaHabitaciones.add(habitacion);
        listaSillas.add(silla);

        try
        {
            reserva = reservaServicio.reservar(listaHabitaciones, listaSillas, usuario, fechaEntrada, fechaSalida, (short) 1,1);
        } catch (ReservaException e) {
            System.out.print(e.getMessage());
        }
        Assertions.assertNotNull(reserva);

    }

    @Test
    public void crearReservaHotelPausado()
    {
        crearDepartamento();
        crearCiudad();
        crearUsuario();
        crearAdministrador();
        crearHotel();
        crearVuelo();
        crearSilla();
        crearHabitacion();

        Date fechaEntrada;
        Date fechaSalida;
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        List<Silla> listaSillas = new ArrayList<>();
        Reserva reserva = null;

        fechaEntrada = Date.valueOf(LocalDate.now().plusDays(2));
        fechaSalida = Date.valueOf(LocalDate.now().plusDays(30));

        listaHabitaciones.add(habitacion);
        listaSillas.add(silla);

        hotel.setEstadoHotel(EstadoHotel.PAUSADO);
        Assertions.assertDoesNotThrow(()-> hotelServicio.editarHotel(hotel));

        Assertions.assertThrows(ReservaException.class, ()->reservaServicio.reservar(listaHabitaciones, listaSillas, usuario, fechaEntrada, fechaSalida, (short) 1, 1));

    }

    @Test
    public void reservarMultiplesHotelesTest()
    {
        crearDepartamento();
        crearCiudad();
        crearUsuario();
        crearAdministrador();
        crearHotel();
        crearVuelo();
        crearSilla();
        crearHabitacion();

        Date fechaEntrada;
        Date fechaSalida;
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        List<Silla> listaSillas = new ArrayList<>();
        Reserva reserva = null;

        fechaEntrada = Date.valueOf(LocalDate.now().plusDays(2));
        fechaSalida = Date.valueOf(LocalDate.now().plusDays(30));

        Hotel hotel1 = new Hotel();
        hotel1.setNombre("hotelito1");
        hotel1.setCiudad(ciudad);

        hotel1.setAdministrador(administrador_hotel);

        hotel1.setDireccion("CLL 1");
        hotel1.setTelefono("12345");
        hotel1.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel1.setNumeroEstrellas((short) 1);


        try {
            hotelServicio.registrarHotel(hotel1);
        } catch (HotelException e) {
            throw new RuntimeException(e);
        }

        Habitacion habitacion1 = new Habitacion();
        habitacion1.setHotel(hotel1);
        habitacion1.setCapacidad(2);
        habitacion1.setPrecio(100000.0);

        habitacion1 = habitacionServicio.registrarHabitacion(habitacion1);


        listaHabitaciones.add(habitacion);
        listaHabitaciones.add(habitacion1);
        listaSillas.add(silla);


        Assertions.assertThrows(ReservaException.class, ()->reservaServicio.reservar(listaHabitaciones, listaSillas, usuario, fechaEntrada, fechaSalida, (short) 1,1 ));

    }

    @Test
    public void RegistrarMuchosAcompanantesTest()
    {
        crearDepartamento();
        crearCiudad();
        crearUsuario();
        crearAdministrador();
        crearHotel();
        crearVuelo();
        crearSilla();
        crearHabitacion();

        Date fechaEntrada;
        Date fechaSalida;
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        List<Silla> listaSillas = new ArrayList<>();
        Reserva reserva = null;

        fechaEntrada = Date.valueOf(LocalDate.now().plusDays(2));
        fechaSalida = Date.valueOf(LocalDate.now().plusDays(30));

        listaHabitaciones.add(habitacion);
        listaSillas.add(silla);

        Assertions.assertThrows(ReservaException.class, ()->reservaServicio.reservar(listaHabitaciones, listaSillas, usuario, fechaEntrada, fechaSalida, (short) 1,100 ));
    }

    @Test
    public void reservarNoHabitacionTest()
    {
        crearDepartamento();
        crearCiudad();
        crearUsuario();
        crearAdministrador();
        crearHotel();
        crearVuelo();
        crearSilla();
        crearHabitacion();

        Date fechaEntrada;
        Date fechaSalida;
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        List<Silla> listaSillas = new ArrayList<>();
        Reserva reserva = null;

        fechaEntrada = Date.valueOf(LocalDate.now().plusDays(2));
        fechaSalida = Date.valueOf(LocalDate.now().plusDays(30));

        listaHabitaciones.add(habitacion);
        listaHabitaciones.clear();
        listaSillas.add(silla);

        Assertions.assertThrows(ReservaException.class, ()->reservaServicio.reservar(listaHabitaciones, listaSillas, usuario, fechaEntrada, fechaSalida, (short) 1,1 ));
    }

    @Test
    public void reservarMuchosVuelosTest()
    {
        crearDepartamento();
        crearCiudad();
        crearUsuario();
        crearAdministrador();
        crearHotel();
        crearVuelo();
        crearSilla();
        crearHabitacion();

        Date fechaEntrada;
        Date fechaSalida;
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        List<Silla> listaSillas = new ArrayList<>();
        Reserva reserva = null;

        Silla silla2= new Silla();
        Vuelo vuelo2= new Vuelo();

        vuelo2.setEstado(EstadoVuelo.CONFIRMADO);
        vuelo2.setAerolinea("Avianka");
        vuelo2.setCiudadOrigen(ciudad);
        vuelo2.setCiudadDestino(ciudad1);

        silla2.setVuelo(vuelo2);
        silla2.setPrecio(10.0);

        fechaEntrada = Date.valueOf(LocalDate.now().plusDays(2));
        fechaSalida = Date.valueOf(LocalDate.now().plusDays(30));

        listaHabitaciones.add(habitacion);
        listaSillas.add(silla);
        listaSillas.add(silla2);


        Assertions.assertThrows(ReservaException.class,()->reservaServicio.reservar(listaHabitaciones, listaSillas, usuario, fechaEntrada, fechaSalida, (short) 1,1));

    }
}
