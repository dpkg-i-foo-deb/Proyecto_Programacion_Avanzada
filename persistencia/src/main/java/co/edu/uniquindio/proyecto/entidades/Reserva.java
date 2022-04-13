package co.edu.uniquindio.proyecto.entidades;

import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Habitacion;
import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Silla;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(updatable = false)
    @FutureOrPresent
    private Date fechaLlegada;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(updatable = false)
    @Future
    private Date fechaSalida;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(updatable = false)
    @PastOrPresent
    private Date fechaReserva;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EstadoReserva estadoReserva;

    @ManyToOne
    @JoinColumn(updatable = false, name= "codigo_usuario")
    private Persona_Usuario usuario;

    @OneToMany(mappedBy = "codigoReserva")
    @ToString.Exclude
    private List<Detalle_Reserva_Habitacion> listaHabitaciones;

    @OneToMany(mappedBy = "codigoReserva")
    @ToString.Exclude
    private List<Detalle_Reserva_Silla> listaSillas;

    public Reserva(Date fechaLlegada, Date fechaSalida, Date fechaReserva, EstadoReserva estadoReserva, Persona_Usuario usuario) {
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.fechaReserva = fechaReserva;
        this.estadoReserva = estadoReserva;
        this.usuario = usuario;
    }

}
