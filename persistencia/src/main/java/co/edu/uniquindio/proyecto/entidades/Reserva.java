package co.edu.uniquindio.proyecto.entidades;

import co.edu.uniquindio.proyecto.entidades.intermediate.Reserva_Habitacion;
import co.edu.uniquindio.proyecto.entidades.intermediate.Reserva_silla;
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
@AllArgsConstructor
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

    /*
    @ManyToMany
    @ToString.Exclude
    private List<Habitacion> listaHabitaciones;
     */

    @ManyToOne
    @JoinColumn(updatable = false)
    private Persona_Usuario codigoUsuario;

    /*
    @ManyToMany
    @ToString.Exclude
    private List<Silla> listaSillas;
     */

    @OneToMany(mappedBy = "codigoReserva")
    @ToString.Exclude
    private List<Reserva_Habitacion> listaHabitaciones;

    @OneToMany(mappedBy = "codigoReserva")
    @ToString.Exclude
    private List<Reserva_silla> listaSillas;
}
