package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.EstadoPersona;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.HabitacionRepo;
import co.edu.uniquindio.proyecto.repositorios.HotelRepo;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorHotelException;
import co.edu.uniquindio.proyecto.servicios.implementacion.AdministradorHotelServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.CamaServicioImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class AdministradorHotelServicioTest {
    @Autowired
    private AdministradorHotelServicioImpl administradorHotelServicio;

    @Autowired
    private CamaServicioImpl camaServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private HabitacionRepo habitacionRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    @Test
    public void registrarAdministradorHotelTest() {
        Ciudad ciudad = ciudadRepo.getById(3);

        Persona_Administrador_Hotel administradorHotel1 = new Persona_Administrador_Hotel(
                "12345",
                "AdminHotel",
                "admin.hotel@email.com",
                "admin123",
                ciudad
        );

        try {
            administradorHotel1 = administradorHotelServicio.registrarAdministradorHotel(administradorHotel1);

            Assertions.assertNotNull(administradorHotel1);
        } catch (AdministradorHotelException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void eliminarAdministradoHotelTest()
    {
        Persona_Administrador_Hotel administrador_hotel=null;
        try
        {
            administrador_hotel= administradorHotelServicio.eliminarAdministradorHotel("24680");
        } catch (AdministradorHotelException e)
        {
            System.out.print(e.getMessage());
        }

        assert administrador_hotel != null;
        Assertions.assertEquals(EstadoPersona.INACTIVA, administrador_hotel.getEstadoPersona());

    }

    @Test
    public void actualizarAdministradorHotelTest() {
        Ciudad ciudad = ciudadRepo.getById(3);
        try {
            Persona_Administrador_Hotel administradorHotel = administradorHotelServicio
                    .obtenerAdministradorHotel("24680");

            administradorHotel.setCiudad(ciudad);

            administradorHotel = administradorHotelServicio.actualizarAdministradorHotel(administradorHotel);

            Assertions.assertNotNull(administradorHotel);
            Assertions.assertEquals(ciudad, administradorHotel.getCiudad());
        } catch (AdministradorHotelException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void obtenerAdministradorHotelTest() {
        try {
            Persona_Administrador_Hotel administradorHotel = administradorHotelServicio
                    .obtenerAdministradorHotel("24680");

            Assertions.assertNotNull(administradorHotel);
        } catch (AdministradorHotelException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void listarAdministradoresHotelTest() {
        List<Persona_Administrador_Hotel> administradoresHotel =
                administradorHotelServicio.listarAdministradoresHotel();

        Assertions.assertNotNull(administradoresHotel);
        Assertions.assertEquals(5, administradoresHotel.size());
    }

    @Test
    public void validarLogin() {
        Ciudad ciudad = ciudadRepo.getById(1);
        Persona_Administrador_Hotel administrador_hotel = new Persona_Administrador_Hotel(
                "09876",
                "Mateo Estrada",
                "stiven@email.com",
                "mateo123",
                ciudad
        );

        try{
            administrador_hotel = administradorHotelServicio.registrarAdministradorHotel(administrador_hotel);
            String correo = administrador_hotel.getEmail();
            String password = administrador_hotel.getContrasena();
            Persona_Administrador_Hotel administrador_HotelEncontrado = administradorHotelServicio.validarLogin(correo, password);
            Assertions.assertNotNull(administrador_HotelEncontrado);
        } catch(Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    @Test
    public void registrarCama (){
        Ciudad ciudad1 = ciudadRepo.getById(8);
        Persona_Administrador_Hotel adminHotel = administradorHotelRepo.getById("22222");

        Hotel hotel1 = new Hotel(
                "Hotel Cacique",
                (short) 3,
                "Calle 18",
                ciudad1,
                adminHotel,
                EstadoHotel.DISPONIBLE
        );
        Habitacion habitacion = new Habitacion( 15.000, 5, hotel1 );
        Cama cama = new Cama("sencilla", habitacion);
        try {
            Cama cama1 = camaServicio.registrarCama(cama);
            Assertions.assertNotNull(cama1);
        }catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void registrarHabitacion(){
        Hotel hotel = hotelRepo.getById(1);
        hotelRepo.save(hotel);
        Habitacion habitacion = new Habitacion(15000.00, 3, hotel);

        try {
            habitacion = administradorHotelServicio.registrarHabitacion(habitacion);
            Assertions.assertNotNull(habitacion);
        }catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }

}
