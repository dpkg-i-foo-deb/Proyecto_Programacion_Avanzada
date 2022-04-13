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
public class Hotel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigoHotel;

    @NotBlank
    @Size(max = 255)
    @Column(name="nombre")
    private String nombre;

    @NotBlank
    @Column(unique = true)
    @Size(max = 255)
    private String direccion;

    @ManyToOne
    @NotNull
    @JoinColumn(name="codigo_ciudad")
    private Ciudad ciudad;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "cedula")
    private Persona_Administrador_Hotel administrador;

    @OneToMany(mappedBy = "codigoHotel")
    @ToString.Exclude
    private List<Comentario> listaComentarios;

    @ManyToMany
    @ToString.Exclude
    private List<Caracteristica> listaCaracteristicas;

    @OneToMany(mappedBy = "hotel")
    @ToString.Exclude
    private List<Habitacion> habitaciones;

    @ElementCollection
    private List<String> listaFotos;

    @ManyToMany
    @ToString.Exclude
    private List<Persona_Usuario> listaFavoritosUsuarios;

    public Hotel(Integer codigoHotel, String direccion, Ciudad ciudad, Persona_Administrador_Hotel administrador) {
        this.codigoHotel = codigoHotel;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.administrador = administrador;
    }
}
