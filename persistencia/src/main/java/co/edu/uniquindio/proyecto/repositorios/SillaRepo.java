package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Silla;
import co.edu.uniquindio.proyecto.entidades.embedded.Silla_ID;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SillaRepo extends JpaRepository <Silla, Silla_ID>
{
    Silla findByIdSilla(Silla_ID silla_id);
}
