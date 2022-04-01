package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Persona_Administrador_Hotel extends Persona
{
    @OneToMany(mappedBy = "administrador")
    private List<Hotel> hoteles;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "codigo_ciudad")
    private Ciudad ciudad;
}
