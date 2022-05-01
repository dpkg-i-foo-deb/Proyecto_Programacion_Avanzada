package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Ciudad_Vuelos_DTO {
    private String nombreCiudadOrigen;
    private Long totalRegistros;
}
