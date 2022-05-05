package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.servicios.IComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.ComentarioException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServicioImpl implements IComentarioServicio
{
    private final ComentarioRepo comentarioRepo;

    public ComentarioServicioImpl(ComentarioRepo comentarioRepo) {
        this.comentarioRepo = comentarioRepo;
    }


    @Override
    public Comentario crearComentario(Comentario comentario)
    {
        return comentarioRepo.save(comentario);
    }

    @Override
    public Comentario editarComentario(Comentario comentario)
    {
        return comentarioRepo.save(comentario);
    }

    @Override
    public void eliminarComentario(Comentario comentario)
    {
         comentarioRepo.delete(comentario);
    }

    @Override
    public List<Comentario> obtenerComentariosHotel(Hotel hotel)
    {
        return comentarioRepo.findByCodigoHotel(hotel);
    }
}
