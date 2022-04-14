package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Persona_Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepo extends JpaRepository <Persona_Administrador, String>
{
    Persona_Administrador findByCedula(String cedula);

    Persona_Administrador findByEmail(String email);
}
