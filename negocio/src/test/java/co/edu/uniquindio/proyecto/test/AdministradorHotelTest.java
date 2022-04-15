package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorHotelException;
import co.edu.uniquindio.proyecto.servicios.implementacion.AdministradorHotelServicioImpl;
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
public class AdministradorHotelTest {
    @Autowired
    private AdministradorHotelServicioImpl administradorHotelServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void registrarAdministradorHotelTest() {
        Ciudad ciudad = ciudadRepo.getById(3);

        Persona_Administrador_Hotel administradorHotel1 = new Persona_Administrador_Hotel(
                "12345",
                "AdminHotel",
                "admin.hotel@email.com",
                "admin123",
                ciudad
        );

        try {
            administradorHotel1 = administradorHotelServicio.registrarAdministradorHotel(administradorHotel1);

            Assertions.assertNotNull(administradorHotel1);
        } catch (AdministradorHotelException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
