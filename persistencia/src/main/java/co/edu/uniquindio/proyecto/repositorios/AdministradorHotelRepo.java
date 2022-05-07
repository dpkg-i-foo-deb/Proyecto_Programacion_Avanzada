package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorHotelRepo extends JpaRepository <Persona_Administrador_Hotel, String>
{
    Optional<Persona_Administrador_Hotel> findByCedula(String cedula);

    Optional<Persona_Administrador_Hotel> findByEmail(String email);

    Optional<Persona_Administrador_Hotel> findByEmailAndContrasena(String email, String password);

    Boolean existsByCedulaOrEmail(String cedula, String email);

    Boolean existsByCedula(String cedula);
}
