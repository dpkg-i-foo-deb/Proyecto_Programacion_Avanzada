package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.DepartamentoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest
{
    @Autowired
    private UsuarioRepo usuarioRepo;
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
    public void crearUsuarioTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Usuario usuario = new Persona_Usuario();
        usuario.setNombreCompleto("Chukimamberto Pipicano");
        usuario.setCedula("12345");
        usuario.setEmail("someone@example.com");
        usuario.setContrasena("54321");

        usuario.setCiudad(ciudad);

        usuario = usuarioRepo.save(usuario);

        Assertions.assertNotNull(usuario);
    }

    @Test
    public void eliminarUsuarioTest()
    {
        crearDepartamento();
        crearCiudad();
        String cedula;

        Persona_Usuario usuario = new Persona_Usuario();
        usuario.setNombreCompleto("Chukimamberto Pipicano");
        usuario.setCedula("12345");
        usuario.setEmail("someone@example.com");
        usuario.setContrasena("54321");

        usuario.setCiudad(ciudad);

        usuario = usuarioRepo.save(usuario);

        cedula = usuario.getCedula();

        usuarioRepo.delete(usuario);

        usuario = usuarioRepo.getPersona_UsuarioByCedula(cedula);

        Assertions.assertNull(usuario);
    }

    @Test
    public void editarUsuarioTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Usuario usuario = new Persona_Usuario();
        usuario.setNombreCompleto("Chukimamberto Pipicano");
        usuario.setCedula("12345");
        usuario.setEmail("someone@example.com");
        usuario.setContrasena("54321");

        usuario.setCiudad(ciudad);

        usuario = usuarioRepo.save(usuario);

        usuario.setNombreCompleto("Maricarmen Chamizo");

        usuario = usuarioRepo.save(usuario);

        Assertions.assertEquals("Maricarmen Chamizo",usuario.getNombreCompleto());
    }

    @Test
    public void obtenerUsuariosTest()
    {
        crearDepartamento();
        crearCiudad();

        List<Persona_Usuario> usuarios;

        Persona_Usuario usuario1 = new Persona_Usuario();
        Persona_Usuario usuario2 = new Persona_Usuario();

        usuario1.setNombreCompleto("Chukimamberto Pipicano");
        usuario1.setCedula("12345");
        usuario1.setEmail("someone@example.com");
        usuario1.setContrasena("54321");

        usuario1.setCiudad(ciudad);

        usuarioRepo.save(usuario1);

        usuario2.setNombreCompleto("Maricarmen Chamizo");
        usuario2.setCedula("192994");
        usuario2.setEmail("someone2@example.com");
        usuario2.setContrasena("6567678");

        usuario2.setCiudad(ciudad);

        usuarioRepo.save(usuario2);

        usuarios = usuarioRepo.findAll();

        System.out.print(usuarios);

        Assertions.assertEquals(2, usuarios.size());
    }


}
