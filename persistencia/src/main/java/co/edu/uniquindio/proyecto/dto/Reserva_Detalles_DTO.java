package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Habitacion;
import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Silla;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reserva_Detalles_DTO {
    private Reserva reserva;
    private Detalle_Reserva_Habitacion habitaciones;
    private Detalle_Reserva_Silla sillas;
}
