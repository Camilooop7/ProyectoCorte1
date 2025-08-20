package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.edu.unbosque.model.CarritoDTO;
import co.edu.unbosque.model.ModelFacade;

public class CarritoService {
    private final ModelFacade mf;

    public CarritoService() {
        this.mf = new ModelFacade();
    }

    // Obtiene el carrito por nombre
    public CarritoDTO obtenerCarrito(String nombreCarrito) {
        if (nombreCarrito == null || nombreCarrito.isBlank()) return null;
        CarritoDTO probe = new CarritoDTO();
        probe.setNombre(nombreCarrito);
        return mf.getCarritoDAO().findDTO(probe);
    }

    // Agrega un nombre de producto (String) al carrito
    public boolean agregarProducto(String nombreCarrito, String producto) {
        if (nombreCarrito == null || nombreCarrito.isBlank() || producto == null || producto.isBlank())
            return false;

        CarritoDTO actual = obtenerCarrito(nombreCarrito);
        if (actual == null) {
            actual = new CarritoDTO();
            actual.setNombre(nombreCarrito);
            actual.getListaCarrito().add(producto);
            return mf.getCarritoDAO().add(actual);
        } else {
            actual.getListaCarrito().add(producto);
            return mf.getCarritoDAO().update(actual, actual);
        }
    }

    // Lista de items (nombres)
    public List<String> listarItems(String nombreCarrito) {
        CarritoDTO c = obtenerCarrito(nombreCarrito);
        if (c == null || c.getListaCarrito() == null) return Collections.emptyList();
        return new ArrayList<>(c.getListaCarrito());
    }

    // Elimina una sola coincidencia
    public boolean eliminarItem(String nombreCarrito, String item) {
        CarritoDTO c = obtenerCarrito(nombreCarrito);
        if (c == null || c.getListaCarrito() == null) return false;
        boolean removed = c.getListaCarrito().remove(item);
        if (removed) {
            return mf.getCarritoDAO().update(c, c);
        }
        return false;
    }

    // Vacía el carrito
    public boolean vaciar(String nombreCarrito) {
        CarritoDTO c = obtenerCarrito(nombreCarrito);
        if (c == null || c.getListaCarrito() == null) return false;
        c.getListaCarrito().clear();
        return mf.getCarritoDAO().update(c, c);
    }
}
