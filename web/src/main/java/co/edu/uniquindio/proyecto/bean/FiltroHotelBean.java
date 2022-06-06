package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.servicios.ICiudadServicio;
import co.edu.uniquindio.proyecto.servicios.IDepartamentoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class FiltroHotelBean {
    @Getter @Setter
    private List<SelectItem> ciudadesGrupo;

    private final IDepartamentoServicio departamentoServicio;

    private final ICiudadServicio ciudadServicio;

    public FiltroHotelBean(IDepartamentoServicio departamentoServicio, ICiudadServicio ciudadServicio) {
        this.ciudadServicio = ciudadServicio;
        this.departamentoServicio = departamentoServicio;
    }

    @PostConstruct
    public void init() {
        //Populating selector options.
        ciudadesGrupo = new ArrayList<>();

        List<Departamento> departamentos = departamentoServicio.obtenerDepartamentos();

        departamentos.forEach(d -> {
            SelectItemGroup group = new SelectItemGroup(d.getNombre());

            SelectItem[] ciudades = ciudadServicio.obtenerCiudadesDepartamento(d)
                    .stream().map(c -> new SelectItem(c, c.getNombre()))
                    .toArray(SelectItem[]::new);

            group.setSelectItems(ciudades);

            ciudadesGrupo.add(group);
        });
    }
}
