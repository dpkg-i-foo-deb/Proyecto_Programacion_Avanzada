package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.Ciudad_TotalHoteles_DTO;
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
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest
{
    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    DepartamentoRepo departamentoRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    private Departamento departamento;
    private Ciudad ciudad;
    private Persona_Administrador_Hotel administrador_hotel;

    private void crearDepartamento ()
    {
        departamento = new Departamento();

        departamento.setNombre("Quindio");

        departamento = departamentoRepo.save(departamento);
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
    public void crearCiudadTest()
    {
        Ciudad ciudad = new Ciudad();

        ciudad.setNombre("Armenia");

        crearDepartamento();

        ciudad.setDepartamento(departamento);

        ciudad = ciudadRepo.save(ciudad);

        Assertions.assertNotNull(ciudad);
        Assertions.assertNotNull(ciudad.getDepartamento());
        Assertions.assertEquals(ciudad.getDepartamento(), departamento);
    }

    @Test
    public void eliminarCiudadTest()
    {
        Ciudad ciudad = new Ciudad();
        int codigo = 0;

        ciudad.setNombre("Armenia");

        crearDepartamento();

        ciudad.setDepartamento(departamento);

        ciudad = ciudadRepo.save(ciudad);

        ciudadRepo.delete(ciudad);

        ciudad = ciudadRepo.findById(codigo).orElse(null);

        Assertions.assertNull(ciudad);
    }

    @Test
    public void editarCiudadTest()
    {
        Ciudad ciudad = new Ciudad();
        int codigo;

        ciudad.setNombre("Armenia");

        crearDepartamento();

        ciudad.setDepartamento(departamento);

        ciudad = ciudadRepo.save(ciudad);

        codigo = ciudad.getCodigoCiudad();

        ciudad.setNombre("Calarca");

        ciudadRepo.save(ciudad);

        ciudad = ciudadRepo.findById(codigo).orElse(null);

        assert ciudad != null;
        Assertions.assertEquals("Calarca", ciudad.getNombre());
    }

    @Test
    public void obtenerCiudadesTest()
    {
        crearDepartamento();

        List<Ciudad> ciudades;

        Ciudad ciudad1 = new Ciudad();
        Ciudad ciudad2 = new Ciudad();
        Ciudad ciudad3 = new Ciudad();
        Ciudad ciudad4 = new Ciudad();

        ciudad1.setNombre("Calarca");
        ciudad2.setNombre("Armenia");
        ciudad3.setNombre("Montenegro");
        ciudad4.setNombre("Circasia");

        ciudad1.setDepartamento(departamento);
        ciudad2.setDepartamento(departamento);
        ciudad3.setDepartamento(departamento);
        ciudad4.setDepartamento(departamento);

        ciudadRepo.save(ciudad1);
        ciudadRepo.save(ciudad2);
        ciudadRepo.save(ciudad3);
        ciudadRepo.save(ciudad4);

        ciudades = ciudadRepo.findAll();

        System.out.print(ciudades);

        Assertions.assertEquals(4, ciudades.size());
    }

    @Test
    public void obtenerHotelesCiudadTest() {
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

        hoteles = ciudadRepo.obtenerHoteles(ciudad.getNombre());

        System.out.print(hoteles);

        Assertions.assertEquals(3, hoteles.size());
    }

    @Test
    @Sql("classpath:DatosSQL.sql")
    public void obtenerTotalHotelesPorCiudad() {
        List<Ciudad_TotalHoteles_DTO> res = ciudadRepo.obtenerTotalHotelesPorCiudad();

        res.forEach(System.out::println);

        Assertions.assertEquals(9, res.size());
        Assertions.assertEquals(2, res.get(0).getTotalHoteles());
        Assertions.assertEquals(0, res.get(1).getTotalHoteles());
        Assertions.assertEquals(1, res.get(3).getTotalHoteles());
    }
}