package co.edu.uniquindio.proyecto.entidades;

import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Habitacion;
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
    private Integer codigoHabitacion;

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
    @ToString.Exclude
    private List<Detalle_Reserva_Habitacion> listaReservas;

    public Habitacion(Double precio, int capacidad, Hotel hotel) {
        this.precio = precio;
        this.capacidad = capacidad;
        this.hotel = hotel;
    }

}
