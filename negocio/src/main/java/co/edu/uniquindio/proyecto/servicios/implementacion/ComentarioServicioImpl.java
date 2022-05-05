package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.servicios.IComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServicioImpl implements IComentarioServicio {

    @Autowired
    private ComentarioRepo comentarioRepo;

}
