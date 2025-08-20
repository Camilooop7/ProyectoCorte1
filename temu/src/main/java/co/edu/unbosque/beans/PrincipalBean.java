package co.edu.unbosque.beans;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import co.edu.unbosque.service.AddService;
import co.edu.unbosque.model.*;

@Named("paginaprincipalbean")
@ViewScoped
public class PrincipalBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean modoEliminar = false;
    private int idProductoSeleccionado;

    public static class Producto {
        private int id;
        private String tipo;
        private String nombre;
        private String imagen;
        private String precio;

        public Producto(int id, String tipo, String nombre, String imagen, String precio) {
            this.id = id; this.tipo = tipo; this.nombre = nombre; this.imagen = imagen; this.precio = precio;
        }
        public int getId() { return id; }
        public String getTipo() { return tipo; }
        public String getNombre() { return nombre; }
        public String getImagen() { return imagen; }
        public String getPrecio() { return precio; }
    }

    private List<Producto> productos = new ArrayList<>();

    @Inject
    private AddService addService;

    @PostConstruct
    public void init() { cargarProductos(); }

    public void cargarProductos() {
        List<Producto> lista = new ArrayList<>();
        int i = 1;

        // Dispositivo
        for (Movil m : addService.listarMoviles()) {
            lista.add(new Producto(i++, "Dispositivo - Móvil", s(m::getNombre), s(m::getImagen), String.valueOf(m.getPrecio())));
        }
        for (Audifono a : addService.listarAudifonos()) {
            lista.add(new Producto(i++, "Dispositivo - Audífono", s(a::getNombre), s(a::getImagen), String.valueOf(a.getPrecio())));
        }

        // Maquillaje
        for (Labial l : addService.listarLabiales()) {
            lista.add(new Producto(i++, "Maquillaje - Labial", s(l::getNombre), s(l::getImagen), String.valueOf(l.getPrecio())));
        }
        for (Pestanina p : addService.listarPestaninas()) {
            lista.add(new Producto(i++, "Maquillaje - Pestañina", s(p::getNombre), s(p::getImagen), String.valueOf(p.getPrecio())));
        }

        // Juguete
        for (JuegoMesa j : addService.listarJuegoMesa()) {
            lista.add(new Producto(i++, "Juguete - Juego de mesa", s(j::getNombre), s(j::getImagen), String.valueOf(j.getPrecio())));
        }
        // (Si luego agregas Educativo, mapea aquí)

        // Papelería
        for (Colegio c : addService.listarColegios()) {
            lista.add(new Producto(i++, "Papelería - Colegio", s(c::getNombre), s(c::getImagen), String.valueOf(c.getPrecio())));
        }
        for (Oficina o : addService.listarOficinas()) {
            lista.add(new Producto(i++, "Papelería - Oficina", s(o::getNombre), s(o::getImagen), String.valueOf(o.getPrecio())));
        }

        // Ropa
        for (Hombre h : addService.listarHombres()) {
            lista.add(new Producto(i++, "Ropa - Hombre", s(h::getNombre), s(h::getImagen), String.valueOf(h.getPrecio())));
        }
        for (Mujer mu : addService.listarMujeres()) {
            lista.add(new Producto(i++, "Ropa - Mujer", s(mu::getNombre), s(mu::getImagen), String.valueOf(mu.getPrecio())));
        }

        this.productos = lista;
    }

    private String s(Supplier<String> sup) {
        try { String v = sup.get(); return v == null ? "" : v; } catch (Exception e) { return ""; }
    }

    // Getters/acciones
    public List<Producto> getProductos() { return productos; }
    public boolean isModoEliminar() { return modoEliminar; }
    public void cambiarModoEliminar() { modoEliminar = !modoEliminar; }
    public int getIdProductoSeleccionado() { return idProductoSeleccionado; }
    public void setIdProductoSeleccionado(int idProductoSeleccionado) { this.idProductoSeleccionado = idProductoSeleccionado; }

    public void eliminarProducto() {
        // Solo elimina en la vista (id artificial). Para borrado real, agrega métodos eliminarXxx en AddService.
        productos.removeIf(p -> p.getId() == idProductoSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto eliminado (vista)."));
    }

    public void comprar() {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto añadido al carrito."));
    }

    public void accionProducto() {
        if (modoEliminar) eliminarProducto();
        else comprar();
    }
}
