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

    // ==== VISTA ====
    public static final class ItemView {
        private final String nombre;
        private final int precio;
        /** token crudo almacenado (nombre|precio) */
        private final String token;

        public ItemView(String nombre, int precio, String token) {
            this.nombre = nombre;
            this.precio = precio;
            this.token = token;
        }
        public String getNombre() { return nombre; }
        public int getPrecio() { return precio; }
        public String getToken() { return token; }
    }

    // ==== DAO helpers ====
    public CarritoDTO obtenerCarrito(String nombreCarrito) {
        if (nombreCarrito == null || nombreCarrito.isBlank()) return null;
        CarritoDTO probe = new CarritoDTO();
        probe.setNombre(nombreCarrito);
        return mf.getCarritoDAO().findDTO(probe);
    }

    // ==== AGREGAR ====
    /** Nuevo: agrega con nombre y precio ⇒ guarda "nombre|precio" */
    public boolean agregarProducto(String nombreCarrito, String nombreProducto, int precio) {
        if (isBlank(nombreCarrito) || isBlank(nombreProducto)) return false;
        String token = nombreProducto.trim() + "|" + precio;

        CarritoDTO actual = obtenerCarrito(nombreCarrito);
        if (actual == null) {
            actual = new CarritoDTO();
            actual.setNombre(nombreCarrito);
            actual.getListaCarrito().add(token);
            return mf.getCarritoDAO().add(actual);
        } else {
            actual.getListaCarrito().add(token);
            return mf.getCarritoDAO().update(actual, actual);
        }
    }

    /** Compat: si aún llaman sin precio, guarda 0 */
    public boolean agregarProducto(String nombreCarrito, String nombreProducto) {
        return agregarProducto(nombreCarrito, nombreProducto, 0);
    }

    // ==== LISTAR (crudo y parseado) ====
    public List<String> listarItems(String nombreCarrito) {
        CarritoDTO c = obtenerCarrito(nombreCarrito);
        if (c == null || c.getListaCarrito() == null) return Collections.emptyList();
        return new ArrayList<>(c.getListaCarrito());
    }

    public List<ItemView> listarItemsView(String nombreCarrito) {
        List<String> raw = listarItems(nombreCarrito);
        List<ItemView> out = new ArrayList<>();
        for (String token : raw) {
            if (token == null) continue;
            String nombre = token;
            int precio = 0;

            int idx = token.lastIndexOf('|');
            if (idx > -1) {
                nombre = token.substring(0, idx).trim();
                String p = token.substring(idx + 1).trim();
                precio = parsePrecioSeguro(p);
            }
            out.add(new ItemView(nombre, precio, token));
        }
        return out;
    }

    public int total(String nombreCarrito) {
        int sum = 0;
        for (ItemView iv : listarItemsView(nombreCarrito)) {
            sum += iv.getPrecio();
        }
        return sum;
    }

    // ==== ELIMINAR / VACIAR ====
    /** Elimina exactamente el token crudo (nombre|precio) */
    public boolean eliminarItemPorToken(String nombreCarrito, String token) {
        CarritoDTO c = obtenerCarrito(nombreCarrito);
        if (c == null || c.getListaCarrito() == null) return false;
        boolean removed = c.getListaCarrito().remove(token);
        return removed && mf.getCarritoDAO().update(c, c);
    }

    /** Mantengo tu versión por nombre (quita primera coincidencia) */
    public boolean eliminarItem(String nombreCarrito, String item) {
        CarritoDTO c = obtenerCarrito(nombreCarrito);
        if (c == null || c.getListaCarrito() == null) return false;
        boolean removed = c.getListaCarrito().remove(item);
        return removed && mf.getCarritoDAO().update(c, c);
    }

    public boolean vaciar(String nombreCarrito) {
        CarritoDTO c = obtenerCarrito(nombreCarrito);
        if (c == null || c.getListaCarrito() == null) return false;
        c.getListaCarrito().clear();
        return mf.getCarritoDAO().update(c, c);
    }

    // ==== Utils ====
    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    /** Acepta "$120.000", "120,000", " 120000 ", etc. */
    private int parsePrecioSeguro(String s) {
        if (s == null) return 0;
        String limpio = s.replaceAll("[^0-9-]", "");
        if (limpio.isEmpty() || "-".equals(limpio)) return 0;
        try {
            return Integer.parseInt(limpio);
        } catch (NumberFormatException e) {
            try {
                long l = Long.parseLong(limpio);
                if (l > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                if (l < Integer.MIN_VALUE) return Integer.MIN_VALUE;
                return (int) l;
            } catch (NumberFormatException ex) {
                return 0;
            }
        }
    }
}
