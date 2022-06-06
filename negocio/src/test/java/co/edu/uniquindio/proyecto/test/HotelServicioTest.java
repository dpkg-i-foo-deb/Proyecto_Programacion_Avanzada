package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.EstadoHotel;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
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
public class HotelServicioTest {
    @Autowired
    private HotelServicioImpl hotelServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    @Test
    public void registrarHotelTest() {
        Ciudad ciudad1 = ciudadRepo.getById(8);
        Ciudad ciudad2 = ciudadRepo.getById(1);
        Persona_Administrador_Hotel adminHotel = administradorHotelRepo.getById("22222");

        Hotel hotel1 = new Hotel(
                "Hotel Cacique",
                (short) 3,
                "Calle 18",
                ciudad1,
                adminHotel,
                EstadoHotel.DISPONIBLE
        );

        Hotel hotel2 = new Hotel(
                "Hotel Cacique",
                (short) 5,
                "Calle 25",
                ciudad2,
                adminHotel,
                EstadoHotel.DISPONIBLE
        );

        try {
            hotel1 = hotelServicio.registrarHotel(hotel1);

            Assertions.assertNotNull(hotel1);

            Assertions.assertThrows(HotelException.class, () -> hotelServicio.registrarHotel(hotel2));
        } catch (HotelException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void editarHotelTest() {
        try {
            Hotel hotel = hotelServicio.obtenerHotel(1);
            hotel.setNombre("Hotel Montañero");

            hotel = hotelServicio.editarHotel(hotel);

            Assertions.assertNotNull(hotel);
            Assertions.assertEquals("Hotel Montañero", hotel.getNombre());
        } catch (HotelException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void eliminarHotelTest() {
        try {
            Hotel hotel = hotelServicio.obtenerHotel(1);

            Assertions.assertTrue(hotelServicio.eliminarHotel(hotel));
        } catch (HotelException e) {
            Assertions.fail(e.getMessage());
        }
    }

    public void obtenerHotelTest() {
        try {
            Hotel hotel = hotelServicio.obtenerHotel(1);

            Assertions.assertNotNull(hotel);
        } catch (HotelException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void listarHotelesTest() {
        List<Hotel> hoteles = hotelServicio.obtenerHoteles();

        Assertions.assertNotNull(hoteles);
        Assertions.assertEquals(3, hoteles.size());
    }

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

    @Test
    public void obtenerHotelesPorNombre() {
        List<Hotel> hoteles = hotelServicio.obtenerHotelesPorNombre("Hotel");

        Assertions.assertNotNull(hoteles);
        Assertions.assertEquals(3, hoteles.size());

        hoteles = hotelServicio.obtenerHotelesPorNombre("Paraiso");

        Assertions.assertNotNull(hoteles);
        Assertions.assertEquals(0, hoteles.size());
    }

    @Test
    public void obtenerPrecioHabitacionMasEconomica() {
        Double precio = hotelServicio.obtenerPrecioHabitacionMasEconomica(1);

        Assertions.assertEquals(10000.0, precio);
    }

    @Test
    public void obtenerHotelesPorNombreYCiudad() {
        List<Hotel> hoteles = hotelServicio.obtenerHotelesPorNombreYCiudad("Cal", 4);

        Assertions.assertNotNull(hoteles);
        Assertions.assertEquals(1, hoteles.size());
    }

    @Test
    public void obtenerHotelesPorIdCiudad() {
        List<Hotel> hoteles = hotelServicio.obtenerHotelesPorIdCiudad(4);

        Assertions.assertNotNull(hoteles);
        Assertions.assertEquals(1, hoteles.size());
    }
}
