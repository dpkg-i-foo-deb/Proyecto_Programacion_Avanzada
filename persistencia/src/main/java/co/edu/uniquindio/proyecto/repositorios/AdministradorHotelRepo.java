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

    Optional<Persona_Administrador_Hotel> findByEmailAndContrasena(String email, String password);

    Boolean existsByCedulaOrEmail(String cedula, String email);

    Boolean existsByCedula(String cedula);

    @Query("select case when count(u) > 0 then true else false end from Persona_Administrador_Hotel u where u.email = :email")
    Boolean esAdminHotel(String email);
}
