package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.HotelRepo;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import co.edu.uniquindio.proyecto.servicios.implementacion.UsuarioServicioImpl;
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
    private HotelRepo hotelRepo;

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
    public void obtenerUsuarioByEmail() {
        try {
            Persona_Usuario usuario = usuarioServicio.obtenerUsuarioByEmail("miguel@email.com");

            Assertions.assertNotNull(usuario);
            Assertions.assertEquals("44444", usuario.getCedula());
        } catch (UsuarioException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void obtenerUsuarioByCedula() {
        try {
            Persona_Usuario usuario = usuarioServicio.obtenerUsuarioByCedula("44444");

            Assertions.assertNotNull(usuario);
            Assertions.assertEquals("miguel@email.com", usuario.getEmail());
        } catch (UsuarioException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void agregarHotelFavorito() {
        Hotel hotel = hotelRepo.getById(3);

        try {
            Persona_Usuario usuario = usuarioServicio.obtenerUsuarioByCedula("44444");
            usuarioServicio.agregarHotelFavorito(hotel, usuario.getEmail());

            List<Hotel> favoritos = usuarioServicio.obtenerHotelesFavoritos(usuario.getEmail());

            Assertions.assertNotNull(favoritos);
            Assertions.assertEquals(3, favoritos.size());

            Assertions.assertThrows(UsuarioException.class,
                    () -> usuarioServicio.agregarHotelFavorito(hotel, "otro@email.com"));
        } catch (UsuarioException | HotelException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void eliminarHotelFavorito() {
        Hotel hotel1 = hotelRepo.getById(1);
        Hotel hotel3 = hotelRepo.getById(3);

        try {
            Persona_Usuario usuario = usuarioServicio.obtenerUsuarioByCedula("44444");
            usuarioServicio.eliminarHotelFavorito(hotel1, usuario.getEmail());

            List<Hotel> favoritos = usuarioServicio.obtenerHotelesFavoritos(usuario.getEmail());

            Assertions.assertNotNull(favoritos);
            Assertions.assertEquals(1, favoritos.size());

            Assertions.assertThrows(HotelException.class,
                    () -> usuarioServicio.eliminarHotelFavorito(hotel3, usuario.getEmail()));
        } catch (UsuarioException | HotelException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void obtenerHotelesFavoritosTest() {
        List<Hotel> favoritos = usuarioServicio.obtenerHotelesFavoritos("miguel@email.com");

        Assertions.assertNotNull(favoritos);
        Assertions.assertEquals(2,  favoritos.size());

        favoritos = usuarioServicio.obtenerHotelesFavoritos("otro@email.com");

        Assertions.assertEquals(0,  favoritos.size());
    }

    @Test
    public void obtenerHotelesFavoritosByNameTest() {
        try {
            //Consulta con usuario y hoteles válidos.
            List<Hotel> favoritos = usuarioServicio
                    .obtenerHotelesFavoritosByName("miguel@email.com", "Hotel");

            Assertions.assertEquals(2,  favoritos.size());

            //Consulta con usuario incorrecto.
            Assertions.assertThrows(UsuarioException.class,
                    () -> usuarioServicio
                            .obtenerHotelesFavoritosByName("otro@email.com", "Hotel"));

            //Consulta con nombre de hotel sin registros.
            favoritos = usuarioServicio.obtenerHotelesFavoritosByName("miguel@email.com", "Campestre");
            Assertions.assertEquals(0,  favoritos.size());
        } catch (UsuarioException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
