package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class HabitacionTest
{
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private DepartamentoRepo departamentoRepo;
    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private HabitacionRepo habitacionRepo;

    private Ciudad ciudad;
    private Departamento departamento;
    private Persona_Administrador_Hotel administrador_hotel;
    private Hotel hotel;

    private void crearDepartamento()
    {
        departamento = new Departamento();
        departamento.setNombre("Quindio");

        departamentoRepo.save(departamento);
    }

    private void crearCiudad()
    {
        ciudad = new Ciudad();
        ciudad.setNombre("Armenia");

        ciudad.setDepartamento(departamento);

        ciudadRepo.save(ciudad);
    }

    private void crearAdministradorHotel()
    {
        administrador_hotel = new Persona_Administrador_Hotel();

        administrador_hotel.setNombreCompleto("Stiven Herrera Sierra");
        administrador_hotel.setCedula("12345");
        administrador_hotel.setEmail("stiven.herreras@uqvirtual.edu.co");
        administrador_hotel.setContrasena("54321");

        administrador_hotel.setCiudad(ciudad);

        administrador_hotel = administradorHotelRepo.save(administrador_hotel);
    }

    private void crearHotel()
    {
        hotel = new Hotel();
        hotel.setNombre("Hotelito");
        hotel.setDireccion("CLL # CRA #");
        hotel.setCiudad(ciudad);
        hotel.setAdministrador(administrador_hotel);

        hotel = hotelRepo.save(hotel);
    }

    @Test
    public void crearHabitacionTest()
    {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();
        crearHotel();

        Habitacion habitacion = new Habitacion();

        habitacion.setHotel(hotel);
        habitacion.setPrecio(100.00);
        habitacion.setCapacidad(1);

        habitacion = habitacionRepo.save(habitacion);

        Assertions.assertNotNull(habitacion);
    }

    @Test
    public void eliminarHabitacionTest()
    {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();
        crearHotel();

        Habitacion habitacion = new Habitacion();

        int codigo;

        habitacion.setHotel(hotel);
        habitacion.setPrecio(100.00);
        habitacion.setCapacidad(1);

        habitacion = habitacionRepo.save(habitacion);
        codigo= habitacion.getCodigo_habitacion();

        habitacionRepo.delete(habitacion);

        habitacion = habitacionRepo.findById(codigo).orElse(null);

        Assertions.assertNull(habitacion);
    }

    @Test
    public void editarHabitacionTest()
    {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();
        crearHotel();

        Habitacion habitacion = new Habitacion();

        habitacion.setHotel(hotel);
        habitacion.setPrecio(100.00);
        habitacion.setCapacidad(1);

        habitacion = habitacionRepo.save(habitacion);

        habitacion.setPrecio(99.99);

        habitacion = habitacionRepo.save(habitacion);

        Assertions.assertEquals(99.99,habitacion.getPrecio());
    }

    @Test
    public void obtenerHabitacionesTest()
    {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();
        crearHotel();

        List<Habitacion> habitaciones;

        Habitacion habitacion1 = new Habitacion();
        Habitacion habitacion2 = new Habitacion();

        habitacion1.setHotel(hotel);
        habitacion1.setPrecio(100.00);
        habitacion1.setCapacidad(1);

        habitacionRepo.save(habitacion1);

        habitacion2.setHotel(hotel);
        habitacion2.setPrecio(100.00);
        habitacion2.setCapacidad(1);

        habitacionRepo.save(habitacion2);

        habitaciones = habitacionRepo.findAll();

        System.out.print(habitaciones);
    }
}
