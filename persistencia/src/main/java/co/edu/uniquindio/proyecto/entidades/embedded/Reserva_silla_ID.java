package co.edu.uniquindio.proyecto.entidades.embedded;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Reserva_silla_ID implements Serializable {
    private Integer codigoReserva;

    private Silla_ID codigoSilla;
}
