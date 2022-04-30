package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    @JoinColumn(updatable = false, name = "cedula_usuario")
    private Persona_Usuario cedulaUsuario;

    @NotNull
    @PastOrPresent
    private Date fecha;

    @ManyToOne
    @NotNull
    @JoinColumn(updatable = false, name = "codigo_hotel")
    private Hotel codigoHotel;

    public Comentario(String observacion, short calificacion, Date fecha, Persona_Usuario cedulaUsuario, Hotel codigoHotel) {
        this.observacion = observacion;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.cedulaUsuario = cedulaUsuario;
        this.codigoHotel = codigoHotel;
    }
}
