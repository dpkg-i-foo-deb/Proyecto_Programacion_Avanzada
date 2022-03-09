package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ciudad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo_ciudad;

    @NotNull
    @Column(length = 50)
    private String nombre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_departamento")
    private Departamento departamento;

    //@OneToMany(mappedBy = "ciudad")
    //private List<Persona> personas;

    @OneToMany(mappedBy = "ciudad")
    private List<Hotel> hoteles;
}
