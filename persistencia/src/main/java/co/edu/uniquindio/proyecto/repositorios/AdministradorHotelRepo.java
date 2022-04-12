package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorHotelRepo extends JpaRepository <Persona_Administrador_Hotel, String>
{
    Persona_Administrador_Hotel findByCedula(String cedula);
}
