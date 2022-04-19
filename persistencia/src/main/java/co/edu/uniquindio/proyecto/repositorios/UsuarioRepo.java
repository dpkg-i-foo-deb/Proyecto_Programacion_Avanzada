package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository <Persona_Usuario, String>
{
    Persona_Usuario getPersona_UsuarioByCedula(String cedula);

    Optional<Persona_Usuario> findByEmail(String email);

    boolean existsByCedulaOrEmail(String cedula, String email);
}
