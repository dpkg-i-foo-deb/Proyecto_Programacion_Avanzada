package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.excepciones.*;
import co.edu.uniquindio.proyecto.servicios.implementacion.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= NegocioApplication.class)
@Transactional
public class ComentarioServicioTest
{
    @Autowired
    private ComentarioServicioImpl comentarioServicio;

    @Autowired
    private DepartamentoServicioImpl departamentoServicio;

    @Autowired
    private CiudadServicioImpl ciudadServicio;

    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @Autowired
    private HotelServicioImpl hotelServicio;

    @Autowired
    private AdministradorHotelServicioImpl administradorHotelServicio;


    private Departamento departamento;
    private Ciudad ciudad;
    private Ciudad ciudad1;
    private Persona_Usuario usuario;
    private Hotel hotel;
    private Persona_Administrador_Hotel administrador_hotel;

    private void crearDepartamento()
    {
        departamento = new Departamento();
        departamento.setNombre("Quindio");

        departamentoServicio.registrarDepartamento(departamento);
    }

    private void crearCiudad()
    {
        ciudad = new Ciudad();
        ciudad.setNombre("Armenia");
        ciudad.setDepartamento(departamento);

        ciudad1 = new Ciudad();
        ciudad1.setNombre("Montenegro");
        ciudad1.setDepartamento(departamento);

        ciudadServicio.registrarCiudad(ciudad);
        ciudadServicio.registrarCiudad(ciudad1);
    }

    private void crearUsuario()
    {
        usuario = new Persona_Usuario();

        usuario.setNombreCompleto("Mateo Estrada Ramirez");
        usuario.setCedula("1010383430");
        usuario.setCiudad(ciudad);
        usuario.setContrasena("238283");
        usuario.setEmail("someone@example.com");

        try {
            usuarioServicio.registrarUsuario(usuario);
        } catch (UsuarioException e) {
            System.out.print(e.getMessage());
        }
    }

    private void crearAdministrador()
    {
        administrador_hotel = new Persona_Administrador_Hotel();
        administrador_hotel.setCedula("11");
        administrador_hotel.setNombreCompleto("Stiven Herrera Sierra");
        administrador_hotel.setContrasena("1234");
        administrador_hotel.setEmail("someone2@example.com");
        administrador_hotel.setCiudad(ciudad);

        try {
            administradorHotelServicio.registrarAdministradorHotel(administrador_hotel);
        } catch (AdministradorHotelException e) {
            System.out.print(e.getMessage());
        }
    }

    private void crearHotel()
    {
        hotel = new Hotel();
        hotel.setNombre("hotelito");
        hotel.setCiudad(ciudad);

        hotel.setAdministrador(administrador_hotel);

        hotel.setDireccion("CLL");
        hotel.setTelefono("123455");
        hotel.setEstadoHotel(EstadoHotel.DISPONIBLE);
        hotel.setNumeroEstrellas((short) 1);


        try {
            hotelServicio.registrarHotel(hotel);
        } catch (HotelException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void crearComentarioTest()
    {
        crearDepartamento();
        crearCiudad();
        crearUsuario();
        crearAdministrador();
        crearHotel();

        Comentario comentario = new Comentario();
        comentario.setCalificacion((short) 1);
        comentario.setCodigoHotel(hotel);
        comentario.setCedulaUsuario(usuario);
        comentario.setFecha(Date.valueOf((LocalDate.now())));
        comentario.setObservacion("Terrible, mi hijo se ahogó en la piscina");

        comentario=  comentarioServicio.crearComentario(comentario);


        Assertions.assertNotNull(comentario);

    }

    @Test
    public void editarComentarioTest()
    {
        crearDepartamento();
        crearCiudad();
        crearUsuario();
        crearAdministrador();
        crearHotel();

        Comentario comentario = new Comentario();
        comentario.setCalificacion((short) 1);
        comentario.setCodigoHotel(hotel);
        comentario.setCedulaUsuario(usuario);
        comentario.setFecha(Date.valueOf((LocalDate.now())));
        comentario.setObservacion("Terrible, mi hijo se ahogó en la piscina");

        comentario=  comentarioServicio.crearComentario(comentario);


        Assertions.assertNotNull(comentario);

        comentario.setCalificacion((short) 0);
        comentario.setObservacion("Me retracto, les coloco cero estrellas");

        comentario = comentarioServicio.editarComentario(comentario);

        Assertions.assertEquals("Me retracto, les coloco cero estrellas", comentario.getObservacion());

    }


    @Test
    public void eliminarComentarioTest()
    {
        crearDepartamento();
        crearCiudad();
        crearUsuario();
        crearAdministrador();
        crearHotel();

        Comentario comentario = new Comentario();
        comentario.setCalificacion((short) 1);
        comentario.setCodigoHotel(hotel);
        comentario.setCedulaUsuario(usuario);
        comentario.setFecha(Date.valueOf((LocalDate.now())));
        comentario.setObservacion("Terrible, mi hijo se ahogó en la piscina");

        comentario=  comentarioServicio.crearComentario(comentario);

        Assertions.assertNotNull(comentario);

        comentarioServicio.eliminarComentario(comentario);

        Assertions.assertFalse(comentarioServicio.obtenerComentariosHotel(hotel).contains(comentario));
    }


    @Test
    public void obtenerComentariosTest()
    {
        crearDepartamento();
        crearCiudad();
        crearUsuario();
        crearAdministrador();
        crearHotel();

        Comentario comentario = new Comentario();
        comentario.setCalificacion((short) 1);
        comentario.setCodigoHotel(hotel);
        comentario.setCedulaUsuario(usuario);
        comentario.setFecha(Date.valueOf((LocalDate.now())));
        comentario.setObservacion("Terrible, mi hijo se ahogó en la piscina");

        comentario=  comentarioServicio.crearComentario(comentario);

        Assertions.assertNotNull(comentario);

        Assertions.assertTrue(comentarioServicio.obtenerComentariosHotel(hotel).contains(comentario));
    }

}
