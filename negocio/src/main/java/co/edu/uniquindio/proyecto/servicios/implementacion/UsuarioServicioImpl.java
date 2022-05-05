package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.EstadoPersona;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.repositorios.HotelRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.IUsuarioServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {
    private final UsuarioRepo usuarioRepo;
    private final HotelRepo hotelRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, HotelRepo hotelRepo) {
        this.usuarioRepo = usuarioRepo;
        this.hotelRepo = hotelRepo;
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
}
