package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Ciudad_TotalHoteles_DTO {
    private Ciudad ciudad;
    private Long totalHoteles;
}
