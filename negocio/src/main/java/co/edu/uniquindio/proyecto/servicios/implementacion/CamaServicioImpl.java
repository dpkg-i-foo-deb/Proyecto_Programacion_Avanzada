package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Cama;
import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.repositorios.CamaRepo;
import co.edu.uniquindio.proyecto.repositorios.HabitacionRepo;
import co.edu.uniquindio.proyecto.servicios.ICamaServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.CamaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CamaServicioImpl implements ICamaServicio {
    private final CamaRepo camaRepo;

    private CamaServicioImpl(CamaRepo camaRepo) {
        this.camaRepo = camaRepo;
    }

    @Override
    public Cama registrarCama(Cama cama) {
        return camaRepo.save(cama);
    }

    @Override
    public Cama actualizarCama(Cama cama) throws CamaException {
        boolean existe = camaRepo.existsByCodigoCama(cama.getCodigoCama());
        if( !existe ) {
            throw new CamaException("La cama especificada no está registrada");
        }
        return camaRepo.save(cama);
    }

    @Override
    public void eliminarCama(Cama cama) throws CamaException {
        boolean existe = camaRepo.existsByCodigoCama(cama.getCodigoCama());
        if( !existe ) {
            throw new CamaException("La cama especificada no está registrada");
        }
        camaRepo.deleteById(cama.getCodigoCama());
    }

    @Override
    public Cama obtenerCama(Integer codigo) throws CamaException {
        Optional<Cama> cama = camaRepo.findById(codigo);
        if ( cama.isEmpty() ) {
            throw new CamaException("No hay registro que coincida con el código de la cama");
        }
        return cama.get();
    }

    @Override
    public List<Cama> listarCamasPorHabitacion(Habitacion habitacion) {
        return camaRepo.findAllByHabitacion(habitacion);
    }

    @Override
    public List<Cama> listarCamas() {
        return camaRepo.findAll();
    }
}
