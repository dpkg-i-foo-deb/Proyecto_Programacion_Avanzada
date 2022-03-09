package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cama
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo_cama;

    private String descripcion;

    //TODO no le coloqué not null porque ps en teorìa una cama puede estar en mantenimiento y sin habitacion ¿No?
    @ManyToOne
    @JoinColumn(name = "codigo_habitacion")
    private Habitacion habitacion;

}
