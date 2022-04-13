package co.edu.uniquindio.proyecto.entidades.intermediate;

import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.entidades.Reserva;
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
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Detalle_Reserva_Habitacion implements Serializable {
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
    @JoinColumn(name = "codigo_habitacion")
    private Habitacion codigoHabitacion;

    @NotNull
    @PositiveOrZero
    private Double precio;

    @NotNull
    @Positive // Mayor a 0.
    private short cantidadHabitaciones;
}