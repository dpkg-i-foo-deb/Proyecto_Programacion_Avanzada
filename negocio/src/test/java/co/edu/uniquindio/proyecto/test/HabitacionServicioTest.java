package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.repositorios.HotelRepo;
import co.edu.uniquindio.proyecto.servicios.excepciones.HabitacionException;
import co.edu.uniquindio.proyecto.servicios.implementacion.HabitacionServicioImpl;
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
public class HabitacionServicioTest {
    @Autowired
    private HabitacionServicioImpl habitacionServicio;

    @Autowired
    private HotelRepo hotelRepo;

    @Test
    public void registrarHabitacionTest() {
        Hotel hotel = hotelRepo.getById(1);

        Habitacion habitacion = new Habitacion(15000.00, 3, hotel);

        habitacion = habitacionServicio.registrarHabitacion(habitacion);

        Assertions.assertNotNull(habitacion);
    }

    @Test
    public void editarHabitacionTest() {
        Hotel hotel1 = hotelRepo.getById(1);
        Hotel hotel2 = hotelRepo.getById(2);

        try {
            Habitacion habitacion = habitacionServicio.obtenerHabitacion(2);
            Assertions.assertEquals(hotel1, habitacion.getHotel());

            habitacion.setHotel(hotel2);

            habitacion = habitacionServicio.actualizarHabitacion(habitacion);

            Assertions.assertNotNull(habitacion);
            Assertions.assertEquals(hotel2, habitacion.getHotel());
        } catch (HabitacionException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void eliminarHabitacionTest() {
        try {
            Habitacion habitacion = habitacionServicio.obtenerHabitacion(2);

            habitacionServicio.eliminarHabitacion(habitacion);

            Assertions.assertTrue(true);
        } catch (HabitacionException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void obtenerHabitacionTest() {
        try {
            Habitacion habitacion = habitacionServicio.obtenerHabitacion(2);

            Assertions.assertNotNull(habitacion);

            Assertions.assertThrows(HabitacionException.class, () -> habitacionServicio.obtenerHabitacion(20));
        } catch (HabitacionException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void obtenerHabitacionesTest() {
        List<Habitacion> habitaciones = habitacionServicio.listarHabitaciones();

        Assertions.assertNotNull(habitaciones);
        Assertions.assertEquals(12, habitaciones.size());
    }

    @Test
    public void obtenerHabitacionesPorHotelTest() {
        Hotel hotel = hotelRepo.getById(2);

        List<Habitacion> habitacionesFiltradas = habitacionServicio.listarHabitacionesPorHotel(hotel);

        Assertions.assertNotNull(habitacionesFiltradas);
        Assertions.assertEquals(3, habitacionesFiltradas.size());
    }
}
