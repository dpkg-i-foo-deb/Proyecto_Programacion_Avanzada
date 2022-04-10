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
public class Silla_ID implements Serializable {
    private int numero;

    private int codigoVuelo;
}
