package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Vuelo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigoVuelo;

    @NotBlank
    @Size(max = 150)
    private String aerolinea;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ciudad_origen")
    private Ciudad ciudadOrigen;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoVuelo estado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ciudad_destino")
    private Ciudad ciudadDestino;

    @OneToMany (mappedBy = "vuelo")
    @ToString.Exclude
    private List<Silla> sillas;

    public Vuelo(String aerolinea, EstadoVuelo estado, Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        this.aerolinea = aerolinea;
        this.estado = estado;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
    }

}
