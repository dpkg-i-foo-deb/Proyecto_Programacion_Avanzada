package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.DepartamentoRepo;
import co.edu.uniquindio.proyecto.repositorios.HotelRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class HotelTest
{
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private DepartamentoRepo departamentoRepo;
    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    private Ciudad ciudad;
    private Departamento departamento;
    private Persona_Administrador_Hotel administrador_hotel;

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

    @Test
    public void crearHotelTest()
    {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();

        Hotel hotel = new Hotel();

        hotel.setNombre("Hotelito");
        hotel.setDireccion("CLL # CRA #");
        hotel.setCiudad(ciudad);
        hotel.setAdministrador(administrador_hotel);
        hotel.setEstadoHotel(EstadoHotel.DISPONIBLE);

        hotel = hotelRepo.save(hotel);

        Assertions.assertNotNull(hotel);
    }

    @Test
    public void eliminarHotelTest()
    {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();

        Hotel hotel = new Hotel();
        int codigo;

        hotel.setNombre("Hotelito");
        hotel.setDireccion("CLL # CRA #");
        hotel.setCiudad(ciudad);
        hotel.setAdministrador(administrador_hotel);
        hotel.setEstadoHotel(EstadoHotel.DISPONIBLE);

        hotel = hotelRepo.save(hotel);
        codigo = hotel.getCodigoHotel();

        hotelRepo.delete(hotel);

        hotel = hotelRepo.findById(codigo).orElse(null);

        Assertions.assertNull(hotel);
    }

    @Test
    public void editarHotelTest()
    {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();

        Hotel hotel = new Hotel();

        hotel.setNombre("Hotelito");
        hotel.setDireccion("CLL # CRA #");
        hotel.setCiudad(ciudad);
        hotel.setAdministrador(administrador_hotel);
        hotel.setEstadoHotel(EstadoHotel.DISPONIBLE);

        hotel = hotelRepo.save(hotel);

        hotel.setNombre("Hotelito 2");

        hotel = hotelRepo.save(hotel);

        Assertions.assertEquals("Hotelito 2", hotel.getNombre());
    }

    @Test
    public void obtenerHoteles()
    {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();

        Hotel hotel1 = new Hotel();
        Hotel hotel2 = new Hotel();
        List<Hotel> hoteles;

        hotel1.setNombre("Hotelito");
        hotel1.setDireccion("CLL # CRA #");
        hotel1.setCiudad(ciudad);
        hotel1.setAdministrador(administrador_hotel);
        hotel1.setEstadoHotel(EstadoHotel.DISPONIBLE);

        hotel1 = hotelRepo.save(hotel1);

        hotel1.setNombre("Hotelito 2");

        hotelRepo.save(hotel1);

        hotel2.setNombre("Hotelito");
        hotel2.setDireccion("CLL # CRA 1");
        hotel2.setCiudad(ciudad);
        hotel2.setAdministrador(administrador_hotel);
        hotel2.setEstadoHotel(EstadoHotel.DISPONIBLE);

        hotel2 = hotelRepo.save(hotel2);

        hotel2.setNombre("Hotelito 2");

        hotelRepo.save(hotel2);

        hoteles = hotelRepo.findAll();

        System.out.print(hoteles);

        Assertions.assertEquals(2,hoteles.size());
    }

}
