package co.edu.uniquindio.proyecto.bean.hotel;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.IAdministradorHotelServicio;
import co.edu.uniquindio.proyecto.servicios.ICiudadServicio;
import co.edu.uniquindio.proyecto.servicios.IDepartamentoServicio;
import co.edu.uniquindio.proyecto.servicios.IHotelServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import co.edu.uniquindio.proyecto.utils.Mensaje;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class RegistroHotelBean implements Serializable {
    private final IHotelServicio hotelServicio;

    private final IAdministradorHotelServicio administradorHotelServicio;

    private final IDepartamentoServicio departamentoServicio;

    private final ICiudadServicio ciudadServicio;

    @Getter @Setter
    private Hotel hotel;

    @Getter @Setter
    private Ciudad ciudad;
    @Getter @Setter
    private List<SelectItem> ciudadesGrupo;
    @Getter @Setter
    private Persona_Administrador_Hotel administradorHotel;
    @Getter @Setter
    private List<Persona_Administrador_Hotel> administradoresHotelLista;

    public RegistroHotelBean(IHotelServicio hotelServicio, IAdministradorHotelServicio administradorHotelServicio, IDepartamentoServicio departamentoServicio, ICiudadServicio ciudadServicio) {
        this.hotelServicio = hotelServicio;
        this.administradorHotelServicio = administradorHotelServicio;
        this.departamentoServicio = departamentoServicio;
        this.ciudadServicio = ciudadServicio;
    }

    @PostConstruct
    public void init() {
        hotel = new Hotel();

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

        administradoresHotelLista = administradorHotelServicio.listarAdministradoresHotel();
    }

    public void registrarHotel() {
        try {
            hotel.setEstadoHotel(EstadoHotel.DISPONIBLE);

            hotelServicio.registrarHotel(hotel);

            Mensaje.mostrarMensaje(
                    FacesMessage.SEVERITY_INFO,
                    "Registro exitoso",
                    "Hotel registrado."
            );
        } catch (HotelException e) {
            System.err.println("ERROR: " + e.getMessage());

            Mensaje.mostrarMensaje(
                    FacesMessage.SEVERITY_ERROR,
                    "Error en el registro",
                    e.getMessage()
            );
        }
    }
}
