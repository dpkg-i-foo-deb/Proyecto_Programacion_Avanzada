package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.servicios.implementacion.AdministradorServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class AdministradorServicioTest {

    @Autowired
    private AdministradorServicioImpl administradorServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void validarLogin() {
        Ciudad ciudad = ciudadRepo.getById(1);
        Persona_Administrador administrador = new Persona_Administrador(
                "09876",
                "Mateo Estrada",
                "stiven@email.com",
                "mateo123",
                ciudad
        );

        try{
            administrador = administradorServicio.registrarAdministrador(administrador);
            String correo = administrador.getEmail();
            String password = administrador.getContrasena();
            Persona_Administrador administradorEncontrado = administradorServicio.validarLogin(correo, password);
            Assertions.assertNotNull(administradorEncontrado);
        } catch(Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

}
