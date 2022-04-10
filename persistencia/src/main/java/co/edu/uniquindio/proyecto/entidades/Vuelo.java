package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vuelo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotBlank
    @Size(max = 150)
    private String aerolinea;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ciudad_origen")
    private Ciudad ciudadOrigen;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ciudad_destino")
    private Ciudad ciudadDestino;
}
