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
import org.springframework.data.domain.Sort;

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
        hotel.setNumeroEstrellas((short) 4);

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
        hotel.setNumeroEstrellas((short) 4);

        hotel = hotelRepo.save(hotel);
        codigo = hotel.getCodigoHotel();

        hotelRepo.delete(hotel);

        hotel = hotelRepo.findById(codigo).orElse(null);

        Assertions.assertNull(hotel);
    }

    @Test
    public void obtenerHotelPorDireccionYCiudadTest()
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
        hotel.setNumeroEstrellas((short) 4);

        hotelRepo.save(hotel);

        Assertions.assertTrue(hotelRepo.existsByDireccionAndCiudad("CLL # CRA #", ciudad));
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
        hotel.setNumeroEstrellas((short) 4);

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
        hotel1.setNumeroEstrellas((short) 4);

        hotelRepo.save(hotel1);

        hotel2.setNombre("Hotelito");
        hotel2.setDireccion("CLL # CRA 1");
        hotel2.setCiudad(ciudad);
        hotel2.setAdministrador(administrador_hotel);
        hotel2.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel2.setNumeroEstrellas((short) 4);

        hotelRepo.save(hotel2);

        hoteles = hotelRepo.findAll();

        System.out.print(hoteles);

        Assertions.assertEquals(2,hoteles.size());
    }

    @Test
    public void obtenerHotelesPorCiudadTest() {
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
        hotel1.setNumeroEstrellas((short) 2);

        hotelRepo.save(hotel1);

        hotel2.setNombre("Hotelito");
        hotel2.setDireccion("CLL # CRA 1");
        hotel2.setCiudad(ciudad);
        hotel2.setAdministrador(administrador_hotel);
        hotel2.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel2.setNumeroEstrellas((short) 1);

        hotelRepo.save(hotel2);

        hoteles = hotelRepo.findAllByCiudad(ciudad);

        System.out.print(hoteles);

        Assertions.assertEquals(2,hoteles.size());
    }

    @Test
    public void obtenerHotelesPorEstrellasTest() {
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
        hotel1.setNumeroEstrellas((short) 4);

        hotelRepo.save(hotel1);

        hotel2.setNombre("Hotelito");
        hotel2.setDireccion("CLL # CRA 1");
        hotel2.setCiudad(ciudad);
        hotel2.setAdministrador(administrador_hotel);
        hotel2.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel2.setNumeroEstrellas((short) 5);

        hotelRepo.save(hotel2);

        hoteles = hotelRepo.findAllByNumeroEstrellas((short) 4);

        System.out.print(hoteles);

        Assertions.assertEquals(1,hoteles.size());
    }

    @Test
    public void buscarHotelPorTelefonoTest() {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();

        Hotel hotel1 = new Hotel();

        hotel1.setNombre("Hotelito");
        hotel1.setDireccion("CLL # CRA #");
        hotel1.setCiudad(ciudad);
        hotel1.setAdministrador(administrador_hotel);
        hotel1.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel1.setNumeroEstrellas((short) 4);
        hotel1.setTelefono("72304029");

        hotelRepo.save(hotel1);

        Assertions.assertTrue(hotelRepo.findByTelefono("72304029").isPresent());
    }

    @Test
    public void obtenerHotelesOrdenadosPorNombreTest() {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();

        Hotel hotel1 = new Hotel();
        Hotel hotel2 = new Hotel();
        Hotel hotel3 = new Hotel();
        List<Hotel> hoteles;

        hotel1.setNombre("Hotelito");
        hotel1.setDireccion("CLL # CRA #");
        hotel1.setCiudad(ciudad);
        hotel1.setAdministrador(administrador_hotel);
        hotel1.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel1.setNumeroEstrellas((short) 4);

        hotelRepo.save(hotel1);

        hotel2.setNombre("Rancho");
        hotel2.setDireccion("CLL # CRA 1");
        hotel2.setCiudad(ciudad);
        hotel2.setAdministrador(administrador_hotel);
        hotel2.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel2.setNumeroEstrellas((short) 5);

        hotelRepo.save(hotel2);

        hotel3.setNombre("Cabañas");
        hotel3.setDireccion("CLL # CRA 7");
        hotel3.setCiudad(ciudad);
        hotel3.setAdministrador(administrador_hotel);
        hotel3.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel3.setNumeroEstrellas((short) 5);

        hotelRepo.save(hotel3);

        hoteles = hotelRepo.findAll(Sort.by("nombre"));

        System.out.print(hoteles);

        Assertions.assertEquals("Cabañas",hoteles.get(0).getNombre());
        Assertions.assertEquals("Hotelito",hoteles.get(1).getNombre());
        Assertions.assertEquals("Rancho",hoteles.get(2).getNombre());
    }

    @Test
    public void obtenerHotelesOrdenadosPorEstrellasTest() {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();

        Hotel hotel1 = new Hotel();
        Hotel hotel2 = new Hotel();
        Hotel hotel3 = new Hotel();
        List<Hotel> hoteles;

        hotel1.setNombre("Hotelito");
        hotel1.setDireccion("CLL # CRA #");
        hotel1.setCiudad(ciudad);
        hotel1.setAdministrador(administrador_hotel);
        hotel1.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel1.setNumeroEstrellas((short) 4);

        hotelRepo.save(hotel1);

        hotel2.setNombre("Rancho");
        hotel2.setDireccion("CLL # CRA 1");
        hotel2.setCiudad(ciudad);
        hotel2.setAdministrador(administrador_hotel);
        hotel2.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel2.setNumeroEstrellas((short) 5);

        hotelRepo.save(hotel2);

        hotel3.setNombre("Cabañas");
        hotel3.setDireccion("CLL # CRA 7");
        hotel3.setCiudad(ciudad);
        hotel3.setAdministrador(administrador_hotel);
        hotel3.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel3.setNumeroEstrellas((short) 2);

        hotelRepo.save(hotel3);

        hoteles = hotelRepo.findAll(Sort.by(Sort.Direction.DESC, "numeroEstrellas"));

        System.out.print(hoteles);

        Assertions.assertEquals("Rancho",hoteles.get(0).getNombre());
        Assertions.assertEquals("Hotelito",hoteles.get(1).getNombre());
        Assertions.assertEquals("Cabañas",hoteles.get(2).getNombre());
    }

    @Test
    public void obtenerNombreCiudadTest() {
        crearDepartamento();
        crearCiudad();
        crearAdministradorHotel();

        Hotel hotel1 = new Hotel();

        hotel1.setNombre("Hotelito");
        hotel1.setDireccion("CLL # CRA #");
        hotel1.setCiudad(ciudad);
        hotel1.setAdministrador(administrador_hotel);
        hotel1.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel1.setNumeroEstrellas((short) 4);
        hotel1.setTelefono("72304029");

        hotelRepo.save(hotel1);

        String nombreCiudad = hotelRepo.obtenerNombreCiudad(hotel1.getCodigoHotel());

        Assertions.assertEquals(ciudad.getNombre(), nombreCiudad);
    }
}
