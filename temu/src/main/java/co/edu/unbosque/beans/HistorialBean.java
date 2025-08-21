package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.List;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.faces.context.FacesContext;

import co.edu.unbosque.service.HistorialService;

@Named("historialBean")
@ViewScoped
public class HistorialBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombreCarrito = "CarritoGlobal";

    @Inject
    private HistorialService historialService; 

    public List<String> getEntradas() {
        return historialService != null 
                ? historialService.listarEntradas(nombreCarrito)
                : java.util.Collections.emptyList();
    }

    public void limpiar() {
        boolean ok = (historialService != null) && historialService.limpiar(nombreCarrito);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(ok ? FacesMessage.SEVERITY_INFO : FacesMessage.SEVERITY_ERROR,
                             ok ? "Historial" : "Error",
                             ok ? "Historial limpiado." : "No se pudo limpiar el historial."));
    }

    // getters/setters
    public String getNombreCarrito() { return nombreCarrito; }
    public void setNombreCarrito(String nombreCarrito) { this.nombreCarrito = nombreCarrito; }
}
