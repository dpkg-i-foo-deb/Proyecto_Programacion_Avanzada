package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.excepciones.ComentarioException;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import co.edu.uniquindio.proyecto.servicios.implementacion.ComentarioServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.HotelServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.UsuarioServicioImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {
    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

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
    public void validarLogin() {
        Ciudad ciudad = ciudadRepo.getById(1);
        Persona_Usuario usuario1 = new Persona_Usuario(
                "09876",
                "Mateo Estrada",
                "stiven@email.com",
                "mateo123",
                ciudad
        );

        try{
            usuario1 = usuarioServicio.registrarUsuario(usuario1);
            String correo = usuario1.getEmail();
            String password = usuario1.getContrasena();
            Persona_Usuario usuarioEncontrado = usuarioServicio.validarLogin(correo, password);
            Assertions.assertNotNull(usuarioEncontrado);
        } catch(Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void comentarHotel(){
        Ciudad ciudad = ciudadRepo.getById(1);
        Persona_Usuario usuario = new Persona_Usuario(
                "09876",
                "Mateo Estrada",
                "stiven@email.com",
                "mateo123",
                ciudad
        );

        Persona_Administrador_Hotel adminHotel = administradorHotelRepo.getById("22222");
        Hotel hotel = new Hotel(
                "Hotel Cacique",
                (short) 3,
                "Calle 18",
                ciudad,
                adminHotel,
                EstadoHotel.DISPONIBLE
        );

        usuarioRepo.save(usuario);
        hotelRepo.save(hotel);
        Comentario comentario = new Comentario("Me gusto", (short)4, Date.valueOf(LocalDate.now()), usuario, hotel);

        try {
            Comentario comentario1 = usuarioServicio.crearComentario(comentario, usuario.getCedula(), hotel.getCodigoHotel());
            Assertions.assertNotNull(comentario1);
        }catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void editarComentarioHotel(){
        Ciudad ciudad = ciudadRepo.getById(1);
        Persona_Usuario usuario = new Persona_Usuario(
                "09876",
                "Mateo Estrada",
                "stiven@email.com",
                "mateo123",
                ciudad
        );

        Persona_Administrador_Hotel adminHotel = administradorHotelRepo.getById("22222");
        Hotel hotel = new Hotel(
                "Hotel Cacique",
                (short) 3,
                "Calle 18",
                ciudad,
                adminHotel,
                EstadoHotel.DISPONIBLE
        );

        usuarioRepo.save(usuario);
        hotelRepo.save(hotel);
        Comentario comentario = new Comentario("Me gusto", (short)4, Date.valueOf(LocalDate.now()), usuario, hotel);
        comentarioRepo.save(comentario);
        Comentario comentarioNuevo = new Comentario("No me gusta", (short)2, Date.valueOf(LocalDate.now()), usuario, hotel);
        comentarioRepo.save(comentarioNuevo);

        try {
            usuarioServicio.editarComentario(comentario, comentarioNuevo, usuario.getCedula(), hotel.getCodigoHotel());
            Assertions.assertEquals(2, comentarioNuevo.getCalificacion());
        }catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void eliminarComentario(){
        Comentario comentario = comentarioRepo.getById(1);
        Persona_Usuario usuario = usuarioRepo.getById("44444");
        Hotel hotel = hotelRepo.getById(1);

        try {
            usuarioServicio.eliminarComentario(comentario, usuario.getCedula(), hotel.getCodigoHotel());
            Assertions.assertThrows(ComentarioException.class, () -> usuarioServicio.obtenerComentario(1));
        }catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }

}
