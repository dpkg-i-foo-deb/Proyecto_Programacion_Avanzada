package co.edu.uniquindio.proyecto.entidades;

import co.edu.uniquindio.proyecto.entidades.intermediate.Reserva_Habitacion;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Habitacion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo_habitacion;

    @NotNull
    @PositiveOrZero
    private Double precio;

    @NotNull
    @Positive //Mayor a 0.
    private int capacidad;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "codigo_hotel")
    private Hotel hotel;

    @ElementCollection
    private List<String> listaFotos;

    @OneToMany(mappedBy = "habitacion")
    @ToString.Exclude
    private List<Cama> listaCamas;

    @OneToMany(mappedBy = "codigoHabitacion")
    private List<Reserva_Habitacion> listaReservas;

    public Habitacion(Integer codigo_habitacion, Double precio, int capacidad, Hotel hotel) {
        this.codigo_habitacion = codigo_habitacion;
        this.precio = precio;
        this.capacidad = capacidad;
        this.hotel = hotel;
    }
}
