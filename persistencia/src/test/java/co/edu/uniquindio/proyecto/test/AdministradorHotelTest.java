package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.DepartamentoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorHotelTest
{
    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;
    @Autowired
    private DepartamentoRepo departamentoRepo;
    @Autowired
    private CiudadRepo ciudadRepo;

    private Ciudad ciudad;
    private Departamento departamento;

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

    @Test
    public void agregarAdministradorHotelTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Administrador_Hotel admin_hotel = new Persona_Administrador_Hotel();

        admin_hotel.setNombreCompleto("Stiven Herrera Sierra");
        admin_hotel.setCedula("12345");
        admin_hotel.setEmail("stiven.herreras@uqvirtual.edu.co");
        admin_hotel.setContrasena("54321");

        admin_hotel.setCiudad(ciudad);

        admin_hotel = administradorHotelRepo.save(admin_hotel);

        Assertions.assertNotNull(admin_hotel);
    }

    @Test
    public void eliminarAdministradorHotelTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Administrador_Hotel admin_hotel = new Persona_Administrador_Hotel();
        String codigo="";

        admin_hotel.setNombreCompleto("Stiven Herrera Sierra");
        admin_hotel.setCedula("12345");
        admin_hotel.setEmail("stiven.herreras@uqvirtual.edu.co");
        admin_hotel.setContrasena("54321");

        admin_hotel.setCiudad(ciudad);

        admin_hotel = administradorHotelRepo.save(admin_hotel);

        codigo=admin_hotel.getCedula();

        administradorHotelRepo.delete(admin_hotel);

        admin_hotel = administradorHotelRepo.findByCedula(codigo);

        Assertions.assertNull(admin_hotel);
    }

    @Test
    public void editarAdministradorHotelTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Administrador_Hotel admin_hotel = new Persona_Administrador_Hotel();
        String codigo="";

        admin_hotel.setNombreCompleto("Stiven Herrera Sierra");
        admin_hotel.setCedula("12345");
        admin_hotel.setEmail("stiven.herreras@uqvirtual.edu.co");
        admin_hotel.setContrasena("54321");

        admin_hotel.setCiudad(ciudad);

        admin_hotel = administradorHotelRepo.save(admin_hotel);

        codigo=admin_hotel.getCedula();
        admin_hotel.setNombreCompleto("Mateo Estrada Ramirez");

        administradorHotelRepo.save(admin_hotel);

        admin_hotel = administradorHotelRepo.findByCedula(codigo);

        Assertions.assertEquals("Mateo Estrada Ramirez",admin_hotel.getNombreCompleto());
    }

    @Test
    public void obtenerAdministradoresHotelTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Administrador_Hotel admin_hotel1 = new Persona_Administrador_Hotel();
        Persona_Administrador_Hotel admin_hotel2 = new Persona_Administrador_Hotel();

        List<Persona_Administrador_Hotel> admins_hotel;

        admin_hotel1.setNombreCompleto("Stiven Herrera Sierra");
        admin_hotel1.setCedula("12345");
        admin_hotel1.setEmail("stiven.herreras@uqvirtual.edu.co");
        admin_hotel1.setContrasena("54321");

        admin_hotel1.setCiudad(ciudad);

        admin_hotel2.setNombreCompleto("Stiven Herrera Sierra");
        admin_hotel2.setCedula("12345");
        admin_hotel2.setEmail("stiven.herreras@uqvirtual.edu.co");
        admin_hotel2.setContrasena("54321");

        admin_hotel2.setCiudad(ciudad);

        administradorHotelRepo.save(admin_hotel1);
        administradorHotelRepo.save(admin_hotel2);

        admins_hotel = administradorHotelRepo.findAll();

        System.out.print(admins_hotel);
    }
}
