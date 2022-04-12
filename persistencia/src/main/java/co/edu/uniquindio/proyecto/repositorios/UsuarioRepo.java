package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository <Persona_Usuario, String>
{
    Persona_Usuario getPersona_UsuarioByCedula(String cedula);
}
