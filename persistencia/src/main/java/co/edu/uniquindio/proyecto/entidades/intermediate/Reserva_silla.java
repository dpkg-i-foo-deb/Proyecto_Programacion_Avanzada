package co.edu.uniquindio.proyecto.entidades.intermediate;

import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.entidades.Silla;
import co.edu.uniquindio.proyecto.entidades.embedded.Reserva_silla_ID;
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
public class Reserva_silla implements Serializable {
    @EmbeddedId
    private Reserva_silla_ID id;

    @ManyToOne
    @MapsId("codigoReserva")
    private Reserva codigoReserva;

    @ManyToOne
    @MapsId("codigoSilla")
    private Silla codigoSilla;

    @NotNull
    @PositiveOrZero
    private Double precio;
}