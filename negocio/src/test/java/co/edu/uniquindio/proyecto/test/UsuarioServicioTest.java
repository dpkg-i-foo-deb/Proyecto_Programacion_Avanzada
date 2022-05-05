package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.EstadoPersona;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.DepartamentoRepo;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import co.edu.uniquindio.proyecto.servicios.implementacion.CiudadServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.DepartamentoServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.UsuarioServicioImpl;
import net.bytebuddy.build.ToStringPlugin;
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
public class UsuarioServicioTest {
    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private DepartamentoServicioImpl departamentoServicio;

    @Autowired
    private CiudadServicioImpl ciudadServicio;

    private Departamento departamento;

    private Ciudad ciudad;


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


        ciudadServicio.registrarCiudad(ciudad);
    }

    @Test
    public void registrarUsuarioTest() {
        Ciudad ciudad = ciudadRepo.getById(1);

        Persona_Usuario usuario1 = new Persona_Usuario(
                "12345",
                "Stiven Herrera",
                "stiven@email.com",
                "usuario123",
                ciudad
        );

        Persona_Usuario usuario2 = new Persona_Usuario(
                "09876",
                "Mateo Estradar",
                "stiven@email.com",
                "mateo123",
                ciudad
        );

        try {
            usuario1 = usuarioServicio.registrarUsuario(usuario1);

            Assertions.assertNotNull(usuario1);

            Assertions.assertThrows(UsuarioException.class, () -> usuarioServicio.registrarUsuario(usuario2));
        } catch (UsuarioException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void eliminarUsuarioTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Usuario usuario = new Persona_Usuario(
                "12345",
                "Stiven Herrera",
                "stiven@email.com",
                "usuario123",
                ciudad
        );

        try
        {
            usuario = usuarioServicio.registrarUsuario(usuario);
        } catch (UsuarioException e)
        {
            System.out.print(e.getMessage());
        }

        Assertions.assertNotNull(usuario);

        usuario = usuarioServicio.eliminarUsuario(usuario);

        Assertions.assertEquals(EstadoPersona.INACTIVA, usuario.getEstadoPersona());
    }

    @Test
    public void editarUsuarioTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Usuario usuario = new Persona_Usuario(
                "12345",
                "Stiven Herrera",
                "stiven@email.com",
                "usuario123",
                ciudad
        );

        try
        {
            usuario = usuarioServicio.registrarUsuario(usuario);
        } catch (UsuarioException e)
        {
            System.out.print(e.getMessage());
        }

        Assertions.assertNotNull(usuario);

        usuario.setNombreCompleto("Mateo Estrada");

        usuario = usuarioServicio.editarUsuario(usuario);

        Assertions.assertEquals("Mateo Estrada",usuario.getNombreCompleto());
    }

    @Test
    public void obtenerUsuariosTest()
    {
        crearDepartamento();
        crearCiudad();

        List<Persona_Usuario> listaUsuarios;

        Persona_Usuario usuario = new Persona_Usuario(
                "12345",
                "Stiven Herrera",
                "stiven@email.com",
                "usuario123",
                ciudad
        );

        Persona_Usuario usuario1 = new Persona_Usuario(
                "123456",
                "Mateo Estrada",
                "mateo@email.com",
                "usuario1234",
                ciudad
        );

        try
        {
            usuario = usuarioServicio.registrarUsuario(usuario);
            usuario1 = usuarioServicio.registrarUsuario(usuario1);
        } catch (UsuarioException e)
        {
            System.out.print(e.getMessage());
        }

        Assertions.assertNotNull(usuario);
        Assertions.assertNotNull(usuario1);

        listaUsuarios = usuarioServicio.obtenerUsuarios();

        Assertions.assertTrue(listaUsuarios.contains(usuario));
        Assertions.assertTrue(listaUsuarios.contains(usuario1));
    }

}
