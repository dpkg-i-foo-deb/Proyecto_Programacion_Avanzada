package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleReservaHabitacionRepo extends JpaRepository<Detalle_Reserva_Habitacion, Integer> {


}
