package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    private Integer codigoCama;

    @NotBlank
    @Size(max = 50)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "codigo_habitacion")
    private Habitacion habitacion;

    public Cama(String tipo, Habitacion habitacion) {
        this.tipo = tipo;
        this.habitacion = habitacion;
    }
}
