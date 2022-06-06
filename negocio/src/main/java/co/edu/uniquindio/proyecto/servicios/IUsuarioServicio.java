package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;

import java.util.List;

public interface IUsuarioServicio {
    Persona_Usuario registrarUsuario(Persona_Usuario usuario) throws UsuarioException;

    Persona_Usuario actualizarUsuario(Persona_Usuario usuarioActualizado) throws UsuarioException;

    void eliminarUsuario(String cedula) throws UsuarioException;

    Persona_Usuario eliminarUsuario(Persona_Usuario usuario);

    List<Persona_Usuario> obtenerUsuarios();

    Persona_Usuario obtenerUsuarioByEmail(String email) throws UsuarioException;

    Persona_Usuario obtenerUsuarioByCedula(String cedula) throws UsuarioException;

    void agregarHotelFavorito(Hotel hotel, String emailUsuario) throws UsuarioException, HotelException;

    void eliminarHotelFavorito(Hotel hotel, String emailUsuario) throws UsuarioException, HotelException;

    List<Hotel> obtenerHotelesFavoritos(String emailUsuario);

    List<Hotel> obtenerHotelesFavoritosByName(String emailUsuario, String nombreHotel) throws UsuarioException;

    Persona_Usuario validarLogin(String correo, String password) throws Exception;

    Comentario crearComentario (Comentario comentario, String cedula, Integer codigo) throws Exception;

    Comentario editarComentario (Comentario comentarioAntiguo, Comentario comentarioNuevo, String cedula, Integer codigo) throws Exception;

    void eliminarComentario (Comentario comentario, String cedula, Integer codigo) throws Exception;

    Boolean reservarVuelo(List<Reserva> reservas, Integer codigoVuelo, Integer codigoSilla, Integer codigoReserva, Integer codigoReservaSilla)throws Exception;

    Boolean esUsuario(String email);
}
