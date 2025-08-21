package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.HistorialDTO;
import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.persistence.HistorialDAO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HistorialService {

    // Usamos la fachada del modelo para obtener el DAO
    private final ModelFacade mf = new ModelFacade();

    /**
     * Devuelve las entradas del historial para un carrito (por nombre).
     */
    public List<String> listarEntradas(String nombreCarrito) {
        HistorialDAO dao = mf.getHistorialDAO();
        HistorialDTO dto = dao.findC(nombreCarrito);
        return (dto == null) ? new ArrayList<>() : dto.getListaHitorial();
    }

    /**
     * Agrega una línea de historial al carrito dado.
     */
    public boolean agregarEntrada(String nombreCarrito, String detalle) {
        if (nombreCarrito == null || nombreCarrito.isBlank()
                || detalle == null || detalle.isBlank()) {
            return false;
        }

        HistorialDAO dao = mf.getHistorialDAO();
        HistorialDTO dto = dao.findC(nombreCarrito);

        if (dto == null) {
            dto = new HistorialDTO(nombreCarrito, new ArrayList<>());
            dto.getListaHitorial().add(detalle);
            return dao.add(dto);
        } else {
            dto.getListaHitorial().add(detalle);
            // reutilizamos update para persistir el cambio
            return dao.update(dto, dto);
        }
    }

    /**
     * Limpia el historial de un carrito (deja la lista vacía).
     */
    public boolean limpiar(String nombreCarrito) {
        HistorialDAO dao = mf.getHistorialDAO();
        HistorialDTO dto = dao.findC(nombreCarrito);
        if (dto == null) {
            return true; // nada que limpiar
        }
        dto.getListaHitorial().clear();
        return dao.update(dto, dto);
    }
}
