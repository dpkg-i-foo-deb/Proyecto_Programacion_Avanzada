package co.edu.uniquindio.proyecto.entidades;

import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Silla;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Silla implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigoSilla;

    @NotNull
    @PositiveOrZero
    private Double precio;

    @OneToMany(mappedBy = "codigoSilla")
    private List<Detalle_Reserva_Silla> listaReservas;

    @ManyToOne
    @NotNull
    @JoinColumn(name="codigo_vuelo")
    private Vuelo vuelo;

    public Silla(Double precio, Vuelo vuelo) {
        this.precio = precio;
        this.vuelo = vuelo;
    }

}
