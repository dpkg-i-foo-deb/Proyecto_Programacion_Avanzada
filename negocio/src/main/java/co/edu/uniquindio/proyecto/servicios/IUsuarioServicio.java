package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;

import javax.mail.MessagingException;
import java.util.List;

public interface IUsuarioServicio {
    Persona_Usuario registrarUsuario(Persona_Usuario usuario) throws Exception;

    Persona_Usuario obtenerUsuarioByEmail(String email) throws UsuarioException;

    Persona_Usuario obtenerUsuarioByCedula(String cedula) throws UsuarioException;

    void agregarHotelFavorito(Hotel hotel, String emailUsuario) throws UsuarioException, HotelException;

    void eliminarHotelFavorito(Hotel hotel, String emailUsuario) throws UsuarioException, HotelException;

    List<Hotel> obtenerHotelesFavoritos(String emailUsuario);

    List<Hotel> obtenerHotelesFavoritosByName(String emailUsuario, String nombreHotel) throws UsuarioException;
}
