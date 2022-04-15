package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.DepartamentoRepo;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import co.edu.uniquindio.proyecto.servicios.implementacion.UsuarioServicioImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {
    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    private Departamento departamento;
    private Ciudad ciudad;

    private void crearDepartamento() {
        departamento = new Departamento("Quindío");
        departamentoRepo.save(departamento);
    }

    private void crearCiudad() {
        ciudad = new Ciudad("Armenia", departamento);
        ciudadRepo.save(ciudad);
    }

    @Test
    public void registrarUsuarioTest() {
        crearDepartamento();
        crearCiudad();

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
}
