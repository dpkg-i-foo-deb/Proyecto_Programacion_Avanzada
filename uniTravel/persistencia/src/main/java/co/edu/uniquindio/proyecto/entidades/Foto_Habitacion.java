package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Foto_Habitacion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo_foto_habitacion;
    
    @Column(unique = true)
    private String ruta;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "codigo_habitacion")
    private Habitacion habitacion;

}
