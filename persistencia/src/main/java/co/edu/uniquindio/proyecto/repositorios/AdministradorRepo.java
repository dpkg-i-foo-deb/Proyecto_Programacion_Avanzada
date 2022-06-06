package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Persona_Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepo extends JpaRepository <Persona_Administrador, String>
{
    Persona_Administrador findByCedula(String cedula);

    Persona_Administrador findByEmail(String email);

    Optional<Persona_Administrador> findByEmailAndContrasena(String email, String password);

    Boolean existsByCedulaOrEmail(String cedula, String email);

    Boolean existsByCedula(String cedula);

    @Query("select case when count(u) > 0 then true else false end from Persona_Administrador u where u.email = :email")
    Boolean esAdmin(String email);
}
