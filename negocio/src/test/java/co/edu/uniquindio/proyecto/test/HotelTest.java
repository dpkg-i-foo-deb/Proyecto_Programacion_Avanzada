package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.servicios.implementacion.HotelServicioImpl;
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
public class HotelTest {
    @Autowired
    private HotelServicioImpl hotelServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void listarHotelesPorCiudadTest() {
        Ciudad ciudad = ciudadRepo.getById(1);

        List<Hotel> hotelesFiltrados = hotelServicio.obtenerHotelesPorCiudad(ciudad);

        Assertions.assertNotNull(hotelesFiltrados);
        Assertions.assertEquals(2, hotelesFiltrados.size());

        ciudad = ciudadRepo.getById(2);

        hotelesFiltrados = hotelServicio.obtenerHotelesPorCiudad(ciudad);

        Assertions.assertEquals(0, hotelesFiltrados.size());
    }
}
