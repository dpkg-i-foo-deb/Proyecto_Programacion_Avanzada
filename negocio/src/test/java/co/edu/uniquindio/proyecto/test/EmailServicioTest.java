package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.email.EmailServicioImpl;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class EmailServicioTest {
    @Autowired
    private EmailServicioImpl emailServicio;

    @Test
    public void recuperarContrasenaTest() {
        try {
            emailServicio.recuperarContrasena("herreras.stiven@gmail.com");
            Assertions.assertTrue(true);
        } catch (MessagingException | UsuarioException e) {
            Assertions.fail(e.getMessage());
        }

    }
}
