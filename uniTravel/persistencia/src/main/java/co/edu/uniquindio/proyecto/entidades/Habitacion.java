package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Habitacion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo_habitacion;

    //TODO colocarle a esto un tipo de dato que sea válido
    private String precio;

    @OneToMany(mappedBy = "habitacion")
    private List<Foto_Habitacion> fotos;

    @OneToMany(mappedBy = "habitacion")
    private List<Cama> camas;
}
