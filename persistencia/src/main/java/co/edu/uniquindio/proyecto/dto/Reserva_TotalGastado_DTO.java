package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Reserva;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reserva_TotalGastado_DTO {
    private Integer codigoReserva;
    private Double totalGastadoReservas;
    private Double totalGastadoSillas;
}
