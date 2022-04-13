package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Ciudad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigoCiudad;

    @NotBlank
    @Column(length = 50)
    private String nombre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_departamento")
    private Departamento departamento;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Persona> listaPersonas;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Hotel> listaHoteles;

    @OneToMany(mappedBy = "ciudadOrigen")
    @ToString.Exclude
    private List<Vuelo> ciudadesOrigen;

    @OneToMany(mappedBy = "ciudadDestino")
    @ToString.Exclude
    private List<Vuelo> ciudadesDestino;

    public Ciudad(String nombre, Departamento departamento) {
        this.nombre = nombre;
        this.departamento = departamento;
    }

}
