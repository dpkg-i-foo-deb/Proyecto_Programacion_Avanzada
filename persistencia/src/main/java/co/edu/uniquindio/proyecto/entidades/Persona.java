package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString
@MappedSuperclass
public class  Persona implements Serializable
{
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private String cedula;

    @NotNull
    private String nombre;

    @NotNull
    @Column(length = 20, unique = true)
    private String email;

    @NotNull
    private String contrasena;

    @ElementCollection
    private List<String> telefonos;



}