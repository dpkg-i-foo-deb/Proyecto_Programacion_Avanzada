package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.repositorios.HabitacionRepo;
import co.edu.uniquindio.proyecto.servicios.IHabitacionServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.HabitacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServicioImpl implements IHabitacionServicio {
    @Autowired
    private HabitacionRepo habitacionRepo;

    @Override
    public Habitacion registrarHabitacion(Habitacion habitacion) {
        return habitacionRepo.save(habitacion);
    }

    @Override
    public Habitacion actualizarHabitacion(Habitacion habitacion) throws HabitacionException {
        boolean existe = habitacionRepo.existsByCodigoHabitacion(habitacion.getCodigoHabitacion());

        if ( !existe ) {
            throw new HabitacionException("La habitación especificada no está registrada");
        }

        return habitacionRepo.save(habitacion);
    }

    @Override
    public void eliminarHabitacion(Habitacion habitacion) throws HabitacionException {
        boolean existe = habitacionRepo.existsByCodigoHabitacion(habitacion.getCodigoHabitacion());

        if ( !existe ) {
            throw new HabitacionException("La habitación especificada no está registrada");
        }

        habitacionRepo.deleteById(habitacion.getCodigoHabitacion());
    }

    @Override
    public Habitacion obtenerHabitacion(Integer codigo) throws HabitacionException {
        Optional<Habitacion> habitacion = habitacionRepo.findById(codigo);

        if ( habitacion.isEmpty() ) {
            throw new HabitacionException("No hay registro que coincida con el código de habitación");
        }

        return habitacion.get();
    }

    @Override
    public List<Habitacion> listarHabitaciones() {
        return habitacionRepo.findAll();
    }

    @Override
    public List<Habitacion> listarHabitacionesPorHotel(Hotel hotel) {
        return habitacionRepo.findAllByHotel(hotel);
    }
}
