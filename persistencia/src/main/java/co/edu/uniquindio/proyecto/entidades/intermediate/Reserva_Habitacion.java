package co.edu.uniquindio.proyecto.entidades.intermediate;

import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.entidades.embedded.Reserva_Habitacion_ID;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reserva_Habitacion implements Serializable {
    @EmbeddedId
    private Reserva_Habitacion_ID id;

    @ManyToOne
    @MapsId("codigoReserva") //Nombre del atributo de reserva en Reserva_Habitacion_ID.
    private Reserva codigoReserva;

    @ManyToOne
    @MapsId("codigoHabitacion") //Nombre del atributo de habitación en Reserva_Habitacion_ID.
    private Habitacion codigoHabitacion;

    @NotNull
    @PositiveOrZero
    private Double precio;
}