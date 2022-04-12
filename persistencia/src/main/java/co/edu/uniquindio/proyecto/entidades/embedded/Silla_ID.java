package co.edu.uniquindio.proyecto.entidades.embedded;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Silla_ID implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;

    private int codigoVuelo;
}
