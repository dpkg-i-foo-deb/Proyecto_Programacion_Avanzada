package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.HotelRepo;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.EstadoPersona;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import co.edu.uniquindio.proyecto.servicios.implementacion.CiudadServicioImplmpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.DepartamentoServicioImpl;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Silla;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.excepciones.ComentarioException;
import co.edu.uniquindio.proyecto.servicios.implementacion.UsuarioServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.UsuarioServicioImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {
    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private DepartamentoServicioImpl departamentoServicio;

    @Autowired
    private CiudadServicioImpl ciudadServicio;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    private Departamento departamento;

    private Ciudad ciudad;


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


        ciudadServicio.registrarCiudad(ciudad);
    }

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private VueloRepo vueloRepo;

    @Autowired
    private SillaRepo sillaRepo;

    @Autowired
    private ReservaRepo reservaRepo;

    @Autowired
    private DetalleReservaSillaRepo detalleReservaSillaRepo;

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
    public void actualizarUsuarioTest() {
        try {
            Persona_Usuario usuario = usuarioServicio.obtenerUsuarioByCedula("44444");
            usuario.getTelefonos().add("3114657182");
            usuario.getTelefonos().add("3156658182");

            usuarioServicio.actualizarUsuario(usuario);

            usuario = usuarioServicio.obtenerUsuarioByCedula("44444");

            Assertions.assertEquals(2, usuario.getTelefonos().size());
        } catch (UsuarioException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void eliminarUsuarioTest() {
        try {
            Ciudad ciudad = ciudadRepo.getById(1);
            Persona_Usuario usuario = new Persona_Usuario("99999", "Julian Gaviria", "julian@email.com", "juli123", ciudad);
            usuarioServicio.registrarUsuario(usuario);

            usuarioServicio.eliminarUsuario("99999");

            Assertions.assertThrows(UsuarioException.class, () -> usuarioServicio.obtenerUsuarioByCedula("99999"));

            Assertions.assertThrows(UsuarioException.class, () -> usuarioServicio.obtenerUsuarioByCedula("888888"));
        } catch (UsuarioException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void obtenerUsuarios() {
        List<Persona_Usuario> usuarios = usuarioServicio.obtenerUsuarios();

        Assertions.assertEquals(2, usuarios.size());
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

    @Test
    public void eliminarUsuarioLogicoTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Usuario usuario = new Persona_Usuario(
                "12345",
                "Stiven Herrera",
                "stiven@email.com",
                "usuario123",
                ciudad
        );

        try
        {
            usuario = usuarioServicio.registrarUsuario(usuario);
        } catch (UsuarioException e)
        {
            System.out.print(e.getMessage());
        }

        Assertions.assertNotNull(usuario);

        usuario = usuarioServicio.eliminarUsuario(usuario);

        Assertions.assertEquals(EstadoPersona.INACTIVA, usuario.getEstadoPersona());
    }

    @Test
    public void validarLogin() {
        /*
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
         */

        try {
            Persona_Usuario usuarioEncontrado = usuarioServicio.validarLogin("herreras.stiven@gmail.com", "stiven123");
            System.out.println(usuarioEncontrado);
            Assertions.assertNotNull(usuarioEncontrado);
        } catch (Exception e) {
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

    @Test
    public void reservarVuelo(){
        List<Reserva> reservas = new List<>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Reserva> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Reserva reserva) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Reserva> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Reserva> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Reserva get(int index) {
                return null;
            }

            @Override
            public Reserva set(int index, Reserva element) {
                return null;
            }

            @Override
            public void add(int index, Reserva element) {

            }

            @Override
            public Reserva remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Reserva> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Reserva> listIterator(int index) {
                return null;
            }

            @Override
            public List<Reserva> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        Vuelo vuelo = vueloRepo.getById(1);
        Silla silla = sillaRepo.getById(1);
        Reserva reserva = reservaRepo.getById(1);
        Detalle_Reserva_Silla detalle_reserva_silla = detalleReservaSillaRepo.getById(1);

        try {
            usuarioServicio.reservarVuelo(reservas, vuelo.getCodigoVuelo(), silla.getCodigoSilla(), reserva.getCodigo(), detalle_reserva_silla.getCodigo());
            Assertions.assertTrue(true);
        }catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }
}
