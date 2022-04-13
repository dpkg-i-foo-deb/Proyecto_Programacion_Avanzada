package co.edu.uniquindio.proyecto.entidades.intermediate;

import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.entidades.Silla;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Detalle_Reserva_Silla implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "codigo_reserva")
    private Reserva codigoReserva;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "codigo_silla")
    private Silla codigoSilla;

    @NotNull
    @PositiveOrZero
    private Double precio;

    public Detalle_Reserva_Silla(Reserva codigoReserva, Silla codigoSilla, Double precio) {
        this.codigoReserva = codigoReserva;
        this.codigoSilla = codigoSilla;
        this.precio = precio;
    }
}