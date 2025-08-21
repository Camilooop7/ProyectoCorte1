package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.HistorialDTO;
import co.edu.unbosque.model.persistence.HistorialDAO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HistorialService {

    private final HistorialDAO dao = new HistorialDAO();

    public List<String> listarEntradas(String nombreCarrito) {
        HistorialDTO dto = dao.findC(nombreCarrito);
        return (dto == null) ? new ArrayList<>() : dto.getListaHitorial();
    }

    public boolean agregarEntrada(String nombreCarrito, String detalle) {
        if (nombreCarrito == null || nombreCarrito.isBlank() || detalle == null || detalle.isBlank()) return false;
        HistorialDTO dto = dao.findC(nombreCarrito);
        if (dto == null) {
            dto = new HistorialDTO(nombreCarrito, new ArrayList<>());
            dto.getListaHitorial().add(detalle);
            return dao.add(dto);
        } else {
            dto.getListaHitorial().add(detalle);
            return dao.update(dto, dto);
        }
    }

    public boolean limpiar(String nombreCarrito) {
        HistorialDTO dto = dao.findC(nombreCarrito);
        if (dto == null) return true;
        dto.getListaHitorial().clear();
        return dao.update(dto, dto);
    }
}
