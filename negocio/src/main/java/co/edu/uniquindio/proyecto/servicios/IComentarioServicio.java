package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.servicios.excepciones.ComentarioException;

import java.util.List;

public interface IComentarioServicio
{
    Comentario crearComentario(Comentario comentario);

    Comentario editarComentario(Comentario comentario);

    void eliminarComentario(Comentario comentario);

    List<Comentario> obtenerComentariosHotel(Hotel hotel);
}
