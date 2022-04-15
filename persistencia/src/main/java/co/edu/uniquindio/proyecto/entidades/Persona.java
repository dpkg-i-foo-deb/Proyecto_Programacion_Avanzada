package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@ToString
public class  Persona implements Serializable
{
    @Id
    @EqualsAndHashCode.Include
    @Size(max = 10)
    @NotBlank
    private String cedula;

    @NotBlank
    @Size(max = 40)
    private String nombreCompleto;

    @NotBlank
    @Column(unique = true, updatable = false)
    @Size(max = 200)
    @Email
    private String email;

    @ElementCollection
    private List<String> telefonos;

    @NotBlank
    @Size(max = 100)
    private String contrasena;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "codigo_ciudad")
    private Ciudad ciudad;

    public Persona(String cedula, String nombreCompleto, String email, String contrasena, Ciudad ciudad) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.contrasena = contrasena;
        this.ciudad = ciudad;
    }

}