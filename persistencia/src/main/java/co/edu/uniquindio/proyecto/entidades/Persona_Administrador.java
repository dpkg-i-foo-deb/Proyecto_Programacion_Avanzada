package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@DiscriminatorValue("admin")
public class Persona_Administrador extends Persona
{
    public Persona_Administrador(String cedula, String nombreCompleto, String email, String contrasena, Ciudad ciudad) {
        super(cedula, nombreCompleto, email, contrasena, ciudad);
    }
}
