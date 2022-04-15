package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorHotelRepo extends JpaRepository <Persona_Administrador_Hotel, String>
{
    Optional<Persona_Administrador_Hotel> findByCedula(String cedula);

    Optional<Persona_Administrador_Hotel> findByEmail(String email);

    Boolean existsByCedulaOrEmail(String cedula, String email);

    Boolean existsByCedula(String cedula);

    @Query("SELECT a FROM Persona_Administrador_Hotel a WHERE a.email = :email AND a.cedula <> :cedulaAdmin")
    Optional<Persona_Administrador_Hotel> findAnotherAdminWithSameEmail(String email, String cedulaAdmin);
}
