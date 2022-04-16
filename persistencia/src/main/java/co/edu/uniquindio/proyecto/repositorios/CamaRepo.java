package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Cama;
import co.edu.uniquindio.proyecto.entidades.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamaRepo extends JpaRepository<Cama, Integer>
{
    Boolean existsByCodigoCama(Integer codigo);

    List<Cama> findAllByHabitacion(Habitacion habitacion);
}
