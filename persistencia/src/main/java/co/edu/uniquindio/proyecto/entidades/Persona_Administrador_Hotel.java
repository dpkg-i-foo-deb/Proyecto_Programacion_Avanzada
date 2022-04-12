package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@DiscriminatorValue("admin_hotel")
public class Persona_Administrador_Hotel extends Persona
{
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Hotel> listaHoteles;

    public Persona_Administrador_Hotel(String cedula, String nombreCompleto, String email, String contrasena,
                                       Ciudad ciudad) {
        super(cedula, nombreCompleto, email, contrasena, ciudad);
    }
}
