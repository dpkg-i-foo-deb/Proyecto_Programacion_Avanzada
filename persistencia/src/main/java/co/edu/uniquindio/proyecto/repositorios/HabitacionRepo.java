package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepo extends JpaRepository<Habitacion, Integer>
{
    Boolean existsByCodigoHabitacion(Integer codigo);

    List<Habitacion> findAllByHotel(Hotel hotel);
}
