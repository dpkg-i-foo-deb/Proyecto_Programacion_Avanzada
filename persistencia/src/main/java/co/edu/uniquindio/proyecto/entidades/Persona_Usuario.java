package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "")
    private List<Reserva> listaReservas;

    public Persona_Usuario(String cedula, String nombreCompleto, String email, String contrasena, Ciudad ciudad) {
        super(cedula, nombreCompleto, email, contrasena, ciudad);
    }
}
