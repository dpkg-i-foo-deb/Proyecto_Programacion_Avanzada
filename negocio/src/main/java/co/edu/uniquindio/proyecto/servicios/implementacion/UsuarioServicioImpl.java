package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.servicios.IUsuarioServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Silla;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.excepciones.ComentarioException;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {
    private final UsuarioRepo usuarioRepo;
    private final ComentarioRepo comentarioRepo;
    private final HotelRepo hotelRepo;
    private final VueloRepo vueloRepo;
    private final SillaRepo sillaRepo;
    private final ReservaRepo reservaRepo;
    private final DetalleReservaSillaRepo detalleReservaSillaRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, ComentarioRepo comentarioRepo, HotelRepo hotelRepo, VueloRepo vueloRepo, SillaRepo sillaRepo, ReservaRepo reservaRepo, DetalleReservaSillaRepo detalleReservaSillaRepo) {
        this.usuarioRepo = usuarioRepo;
        this.comentarioRepo = comentarioRepo;
        this.hotelRepo = hotelRepo;
        this.vueloRepo = vueloRepo;
        this.sillaRepo = sillaRepo;
        this.reservaRepo = reservaRepo;
        this.detalleReservaSillaRepo = detalleReservaSillaRepo;
    }

    @Override
    public Persona_Usuario registrarUsuario(Persona_Usuario usuario) throws UsuarioException {
        boolean enUso = usuarioRepo.existsByCedulaOrEmail( usuario.getCedula(), usuario.getEmail() );

        if ( enUso ) {
            throw new UsuarioException("La cédula o email ya se encuentran en uso");
        }

        return usuarioRepo.save(usuario);
    }

    @Override
    public Persona_Usuario actualizarUsuario(Persona_Usuario usuarioActualizado) throws UsuarioException {
        Persona_Usuario usuario = usuarioRepo.getPersona_UsuarioByCedula(usuarioActualizado.getCedula());

        if ( usuario == null ) {
            throw new UsuarioException("El usuario no está registrado");
        }

        return usuarioRepo.save(usuarioActualizado);
    }

    /**
     * Elimina el registro de la base de datos.
     * @param cedula Cedula del usuario. <String>
     * @throws UsuarioException Si el usuario no existe.
     */
    @Override
    public void eliminarUsuario(String cedula) throws UsuarioException {
        Persona_Usuario usuario = usuarioRepo.getPersona_UsuarioByCedula(cedula);

        if (usuario == null) {
            throw new UsuarioException("No hay registro que coincida con la cédula especificada");
        }

        usuarioRepo.deleteById(cedula);
    }

    /**
     * Modifica el estado del registro a INACTIVO para realizar un borrado lógico.
     * @param usuario Usuario.
     * @return Usuario borrado.
     */
    @Override
    public Persona_Usuario eliminarUsuario(Persona_Usuario usuario) {
        usuario.setEstadoPersona(EstadoPersona.INACTIVA);

        return usuarioRepo.save(usuario);
    }

    @Override
    public List<Persona_Usuario> obtenerUsuarios() {

        return usuarioRepo.findAll();
    }

    @Override
    public Persona_Usuario obtenerUsuarioByCedula(String cedula) throws UsuarioException {
        Persona_Usuario usuario = usuarioRepo.getPersona_UsuarioByCedula(cedula);

        if(usuario == null)
            throw new UsuarioException("No hay un usuario registrado con esa cédula");

        return usuario;
    }

    @Override
    public Persona_Usuario obtenerUsuarioByEmail(String email) throws UsuarioException {
        return usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new UsuarioException("No hay un usuario registrado con ese email"));
    }

    @Override
    public void agregarHotelFavorito(Hotel hotelFavorito, String emailUsuario) throws UsuarioException, HotelException {
        Persona_Usuario usuario = validarUsuarioByEmail(emailUsuario);

        if(! hotelRepo.existsById(hotelFavorito.getCodigoHotel()))
            throw new HotelException("El hotel no está registrado");
        if(usuarioRepo.obtenerHotelesFavoritosUsuario(emailUsuario).contains(hotelFavorito))
            throw new HotelException("El hotel ya está registrado como favorito");

        usuario.getHotelesFavoritos().add(hotelFavorito);

        usuarioRepo.save(usuario);
    }

    @Override
    public void eliminarHotelFavorito(Hotel hotelFavorito, String emailUsuario) throws UsuarioException, HotelException {
        Persona_Usuario usuario = validarUsuarioByEmail(emailUsuario);

        if(! hotelRepo.existsById(hotelFavorito.getCodigoHotel()))
            throw new HotelException("El hotel no está registrado");
        if(! usuarioRepo.obtenerHotelesFavoritosUsuario(emailUsuario).contains(hotelFavorito))
            throw new HotelException("El hotel no está registrado como favorito");

        usuario.getHotelesFavoritos().remove(hotelFavorito);

        usuarioRepo.save(usuario);
    }

    @Override
    public List<Hotel> obtenerHotelesFavoritos(String emailUsuario) {
        return usuarioRepo.obtenerHotelesFavoritosUsuario(emailUsuario);
    }

    @Override
    public List<Hotel> obtenerHotelesFavoritosByName(String emailUsuario, String nombreHotel) throws UsuarioException {
        validarUsuarioByEmail(emailUsuario);

        return usuarioRepo.obtenerHotelesFavoritosByName(emailUsuario, nombreHotel);
    }

    private Persona_Usuario validarUsuarioByEmail(String email) throws UsuarioException {
        return usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new UsuarioException("El usuario no está registrado"));
    }
    public Persona_Usuario validarLogin(String correo, String password) throws Exception {
        Optional<Persona_Usuario> usuario = usuarioRepo.findByEmailAndContrasena(correo, password);
        if(usuario.isEmpty()){
            throw new UsuarioException("Los datos de autenticación son incorrectos");
        }
        return usuario.get();
    }

    @Override
    public Comentario crearComentario(Comentario comentario, String cedula, Integer codigo) throws Exception {
        Optional<Persona_Usuario> usuario = usuarioRepo.findById(cedula);
        Optional<Hotel> hotel = hotelRepo.findById(codigo);
        if(usuario.isEmpty() && hotel.isEmpty()){
            throw new UsuarioException("El usuario no existe");
        }
        return comentarioRepo.save(comentario);
    }

    @Override
    public Comentario editarComentario(Comentario comentarioAntiguo, Comentario comentarioNuevo, String cedula, Integer codigo) throws Exception {
        Optional<Persona_Usuario> usuario = usuarioRepo.findById(cedula);
        Optional<Hotel> hotel = hotelRepo.findById(codigo);
        if(usuario.isEmpty() && hotel.isEmpty()){
            throw new UsuarioException("El usuario no existe");
        }

        boolean existe = comentarioRepo.existsById(comentarioAntiguo.getCodigo());
        if ( !existe ) {
            throw new UsuarioException("El comentario no existe");
        }

        comentarioRepo.delete(comentarioAntiguo);
        return comentarioRepo.save(comentarioNuevo);
    }


    public void eliminarComentario(Comentario comentario, String cedula, Integer codigo) throws Exception {
        Optional<Persona_Usuario> usuario = usuarioRepo.findById(cedula);
        hotelRepo.findById(codigo).orElseThrow(()-> new UsuarioException("El hotel no existe"));
        if(usuario.isEmpty()){
            throw new UsuarioException("El usuario no existe");
        }

        boolean existe = comentarioRepo.existsById(comentario.getCodigo());
        if ( !existe ) {
            throw new UsuarioException("El comentario no existe");
        }
        comentarioRepo.delete(comentario);
    }


    public Comentario obtenerComentario(Integer codigo) throws ComentarioException {
        Optional<Comentario> comentario = comentarioRepo.findById(codigo);
        if ( comentario.isEmpty() ) {
            throw new ComentarioException("No hay registro que coincida con el comentario especificado");
        }
        return comentario.get();
    }

    @Override
    public Boolean reservarVuelo(List<Reserva> reservas, Integer codigoVuelo, Integer codigoSilla, Integer codigoReserva, Integer codigoReservaSilla) throws Exception {
        Vuelo vuelo = vueloRepo.getById(codigoVuelo);
        Silla silla = sillaRepo.getById(codigoSilla);
        Reserva reserva = reservaRepo.getById(codigoReserva);
        Detalle_Reserva_Silla detalle_reserva_silla = detalleReservaSillaRepo.getById(codigoReservaSilla);

        if(!(vuelo.getCodigoVuelo().equals(codigoVuelo) && silla.getCodigoSilla().equals(codigoSilla) && reserva.getCodigo().equals(codigoReserva) && detalle_reserva_silla.getCodigo().equals(codigoReservaSilla))){
            throw new UsuarioException("Algun dato relacionado con la reserva no existe");
        }
        return reservas.add(reserva);
    }

}
