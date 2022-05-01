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
public class Usuario_Reservas_DTO {
    private String email;
    private Reserva reserva;
}
