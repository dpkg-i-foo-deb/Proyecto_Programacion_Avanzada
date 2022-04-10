package co.edu.uniquindio.proyecto.entidades;

import co.edu.uniquindio.proyecto.entidades.embedded.Silla_ID;
import co.edu.uniquindio.proyecto.entidades.intermediate.Reserva_silla;
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
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Silla implements Serializable {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private Silla_ID id;

    @NotNull
    @PositiveOrZero
    private Double precio;

    @OneToMany(mappedBy = "codigoSilla")
    private List<Reserva_silla> listaReservas;
}
