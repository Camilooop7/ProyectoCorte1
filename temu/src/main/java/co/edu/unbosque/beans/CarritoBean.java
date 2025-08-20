package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.List;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;

import co.edu.unbosque.service.CarritoService;

@Named("carritoBean")
@ViewScoped
public class CarritoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // podrías inyectar el username aquí; por ahora fijo
    private String nombreCarrito = "CarritoGlobal";

    private transient CarritoService carritoService = new CarritoService();

    private String itemSeleccionado;

    public List<String> getItems() {
        return carritoService.listarItems(nombreCarrito);
    }

    public void eliminarItem() {
        if (itemSeleccionado == null || itemSeleccionado.isBlank()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se seleccionó ítem."));
            return;
        }
        boolean ok = carritoService.eliminarItem(nombreCarrito, itemSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(ok ? FacesMessage.SEVERITY_INFO : FacesMessage.SEVERITY_ERROR,
                             ok ? "Eliminado" : "Error",
                             ok ? (itemSeleccionado + " removido del carrito") : "No se pudo eliminar."));
        itemSeleccionado = null;
    }

    public void vaciar() {
        boolean ok = carritoService.vaciar(nombreCarrito);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(ok ? FacesMessage.SEVERITY_INFO : FacesMessage.SEVERITY_ERROR,
                             ok ? "OK" : "Error",
                             ok ? "Carrito vaciado." : "No se pudo vaciar."));
    }

    // getters/setters
    public String getItemSeleccionado() { return itemSeleccionado; }
    public void setItemSeleccionado(String itemSeleccionado) { this.itemSeleccionado = itemSeleccionado; }
}
