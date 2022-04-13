package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {
    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    private Departamento depto;
    private Ciudad ciudad;
    private Persona_Administrador_Hotel adminHotel;
    private Persona_Usuario usuario;
    private Hotel hotel;

    private void crearDepartamento() {
        depto = new Departamento("Quindío");
        departamentoRepo.save(depto);
    }

    private void crearCiudad() {
        ciudad = new Ciudad("Armenia", depto);
        ciudadRepo.save(ciudad);
    }

    private void crearAdminHotel() {
        adminHotel = new Persona_Administrador_Hotel(
                "12345",
                "Administrador",
                "admin@email.com",
                "admin123",
                ciudad
        );
        administradorHotelRepo.save(adminHotel);
    }

    private void crearUsuario() {
        usuario = new Persona_Usuario(
                "09876",
                "Usuario",
                "usuario@email.com",
                "usuario123", ciudad
        );
        usuarioRepo.save(usuario);
    }

    private void crearHotel() {
        hotel = new Hotel("Hotelito", "Calle 45-25", ciudad, adminHotel);
        hotelRepo.save(hotel);
    }

    @Test
    public void registrarComentarioTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();

        Comentario comentario = new Comentario("Muy buena atención", (short) 5, usuario, hotel);
        Comentario comentarioRecuperado = comentarioRepo.save(comentario);

        Assertions.assertNotNull(comentarioRecuperado);
        Assertions.assertEquals(comentario.getCodigo(), comentarioRecuperado.getCodigo());
    }

    @Test
    public void eliminarComentarioTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();

        Comentario comentario = new Comentario("Muy buena atención", (short) 5, usuario, hotel);
        comentarioRepo.save(comentario);
        int codigo = comentario.getCodigo();

        comentarioRepo.delete(comentario);

        Optional<Comentario> comentarioRecuperado = comentarioRepo.findById(codigo);

        Assertions.assertFalse(comentarioRecuperado.isPresent());
    }

    @Test
    public void editarComentarioTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();

        Comentario comentario = new Comentario("Muy buena atención", (short) 5, usuario, hotel);
        comentario = comentarioRepo.save(comentario);

        comentario.setCalificacion( (short) 3);
        come

        Assertions.assertNotNull(comentarioRecuperado);
        Assertions.assertEquals(comentario.getCodigo(), comentarioRecuperado.getCodigo());
    }
}
