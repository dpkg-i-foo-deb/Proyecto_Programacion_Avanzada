package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
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
public class AdministradorTest
{
    @Autowired
    private AdministradorRepo administradorRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private DepartamentoRepo departamentoRepo;

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
    public void crearAdministradorTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Administrador administrador = new Persona_Administrador();

        administrador.setNombreCompleto("Chukimamberto Pipicano");
        administrador.setEmail("chukimamberto@example.com");
        administrador.setCedula("12345");
        administrador.setContrasena("54321");

        administrador.setCiudad(ciudad);

        administrador = administradorRepo.save(administrador);

        Assertions.assertNotNull(administrador);
    }

    @Test
    public void eliminarAdministradorTest()
    {
        crearDepartamento();
        crearCiudad();

        String cedula;

        Persona_Administrador administrador = new Persona_Administrador();

        administrador.setNombreCompleto("Chukimamberto Pipicano");
        administrador.setEmail("chukimamberto@example.com");
        administrador.setCedula("12345");
        administrador.setContrasena("54321");

        administrador.setCiudad(ciudad);

        administrador = administradorRepo.save(administrador);

        cedula = administrador.getCedula();

        administradorRepo.delete(administrador);

        administrador = administradorRepo.findByCedula(cedula);

        Assertions.assertNull(administrador);
    }

    @Test
    public void editarAdministradorTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Administrador administrador = new Persona_Administrador();

        administrador.setNombreCompleto("Chukimamberto Pipicano");
        administrador.setEmail("chukimamberto@example.com");
        administrador.setCedula("12345");
        administrador.setContrasena("54321");

        administrador.setCiudad(ciudad);

        administrador = administradorRepo.save(administrador);

        administrador.setNombreCompleto("Maricarmen Boloño");

        administrador = administradorRepo.save(administrador);

        Assertions.assertEquals("Maricarmen Boloño", administrador.getNombreCompleto());
    }

    @Test
    public void obtenerAdministradoresTest()
    {
        crearDepartamento();
        crearCiudad();

        List<Persona_Administrador> administradores;

        Persona_Administrador administrador1 = new Persona_Administrador();
        Persona_Administrador administrador2 = new Persona_Administrador();

        administrador1.setNombreCompleto("Chukimamberto Pipicano");
        administrador1.setEmail("chukimamberto@example.com");
        administrador1.setCedula("12345");
        administrador1.setContrasena("54321");

        administrador1.setCiudad(ciudad);

        administradorRepo.save(administrador1);

        administrador2.setNombreCompleto("Chukimamberto Pipicano");
        administrador2.setEmail("chukimamberto@example.com");
        administrador2.setCedula("12345");
        administrador2.setContrasena("54321");

        administrador2.setCiudad(ciudad);

        administradorRepo.save(administrador2);

        administradores = administradorRepo.findAll();

        System.out.print(administradores);
    }
}
