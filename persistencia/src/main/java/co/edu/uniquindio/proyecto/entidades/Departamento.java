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
public class Departamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigoDepartamento;

    @NotNull
    @Column(length = 50)
    private String nombre;

    @OneToMany(mappedBy = "departamento")
    @ToString.Exclude
    private List<Ciudad> ciudades;

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

}
