package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("usuario")
public class Persona_Usuario extends Persona
{
    @ManyToMany
    @ToString.Exclude
    private List<Hotel> hotelesFavoritos;

    @OneToMany(mappedBy = "cedulaUsuario")
    @ToString.Exclude
    private List<Comentario> listaComentarios;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Reserva> reservas;

    public Persona_Usuario(String cedula, String nombreCompleto, String email, String contrasena, Ciudad ciudad) {
        super(cedula, nombreCompleto, email, contrasena, ciudad);
        this.hotelesFavoritos = new ArrayList<>(0);
    }

}
