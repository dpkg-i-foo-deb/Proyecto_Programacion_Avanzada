package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

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
    @NotNull
    @Size(max = 255)
    @Column(name="nombre")
    private String nombre;

    @NotBlank
    @NotNull
    @Size(max = 255)
    private String direccion;

    @ManyToOne
    @NotNull
    @JoinColumn(name="codigo_ciudad")
    private Ciudad ciudad;

    @NotNull
    @Min(1)
    @Max(5)
    private short numeroEstrellas;

    private String telefono;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "administrador")
    private Persona_Administrador_Hotel administrador;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private EstadoHotel estadoHotel;

    @OneToMany(mappedBy = "codigoHotel")
    @ToString.Exclude
    private List<Comentario> listaComentarios;

    @ManyToMany
    @ToString.Exclude
    private List<Caracteristica> listaCaracteristicas;

    @ManyToMany(mappedBy = "hotelesFavoritos")
    @ToString.Exclude
    private List<Persona_Usuario> usuariosFavoritos;

    @OneToMany(mappedBy = "hotel")
    @ToString.Exclude
    private List<Habitacion> habitaciones;

    @ElementCollection(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<String> listaFotos;

    public Hotel(String nombre, short numeroEstrellas, String direccion, Ciudad ciudad, Persona_Administrador_Hotel administrador, EstadoHotel estadoHotel) {
        this.nombre = nombre;
        this.numeroEstrellas = numeroEstrellas;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.administrador = administrador;
        this.estadoHotel = estadoHotel;

        habitaciones = new ArrayList<>();
    }

}
