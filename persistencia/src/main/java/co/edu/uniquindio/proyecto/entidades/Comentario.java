package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotBlank
    @Size(max = 150)
    private String observacion;

    @NotNull
    @Min(1)
    @Max(5)
    private short calificacion;

    @ManyToOne
    @NotNull
    @JoinColumn(updatable = false)
    private Persona_Usuario cedulaUsuario;

    @ManyToOne
    @NotNull
    @JoinColumn(updatable = false)
    private Hotel codigoHotel;

}
