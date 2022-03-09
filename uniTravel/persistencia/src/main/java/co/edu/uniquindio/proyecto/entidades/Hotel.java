package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo_hotel;

    @NotNull
    @Column(unique = true)
    private String direccion;

    private int numero_estrellas;

    @ManyToOne
    @NotNull
    @JoinColumn(name="codigo_ciudad")
    private Ciudad ciudad;

    @OneToMany(mappedBy = "hotel")
    private List<Foto_Hotel> fotos;

    @OneToMany(mappedBy = "hotel")
    private List<Habitacion> habitaciones;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "cedula")
    private Persona_Administrador administrador;

}
