package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Cama;
import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.repositorios.HabitacionRepo;
import co.edu.uniquindio.proyecto.servicios.excepciones.CamaException;
import co.edu.uniquindio.proyecto.servicios.implementacion.CamaServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class CamaServicioTest {
    @Autowired
    private CamaServicioImpl camaServicio;

    @Autowired
    private HabitacionRepo habitacionRepo;

    @Test
    public void registrarCamaTest() {
        Habitacion habitacion = habitacionRepo.getById(1);

        Cama cama = new Cama("Sencilla", habitacion);

        cama = camaServicio.registrarCama(cama);

        Assertions.assertNotNull(cama);
    }

    @Test
    public void editarCamaTest() {
        Habitacion habitacion1 = habitacionRepo.getById(1);
        Habitacion habitacion6 = habitacionRepo.getById(6);

        try {
            Cama cama = camaServicio.obtenerCama(1);

            Assertions.assertEquals(habitacion1, cama.getHabitacion());

            cama.setHabitacion(habitacion6);

            Assertions.assertEquals(habitacion6, cama.getHabitacion());
        } catch (CamaException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void obtenerCamaTest() {
        try {
            Cama cama = camaServicio.obtenerCama(1);

            Assertions.assertNotNull(cama);

            Assertions.assertThrows(CamaException.class, () -> camaServicio.obtenerCama(20));
        } catch (CamaException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void listarCamasTest() {
        List<Cama> camas = camaServicio.listarCamas();

        Assertions.assertNotNull(camas);
        Assertions.assertEquals(18, camas.size());
    }

    @Test
    public void listarCamasPorHabitacionTest() {
        Habitacion habitacion = habitacionRepo.getById(1);

        List<Cama> camasFiltradas = camaServicio.listarCamasPorHabitacion(habitacion);

        Assertions.assertNotNull(camasFiltradas);
        Assertions.assertEquals(2, camasFiltradas.size());
    }
}
