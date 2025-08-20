package co.edu.unbosque.beans;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import co.edu.unbosque.service.AddService;
import co.edu.unbosque.service.CarritoService;
import co.edu.unbosque.model.*;

@Named("paginaprincipalbean")
@ViewScoped
public class PrincipalBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean modoEliminar = false;

    /** 
     * Objeto que recibiremos desde la vista al presionar ELIMINAR.
     * Debes setearlo en el commandButton con f:setPropertyActionListener.
     */
    private Producto productoSeleccionado;

    /**
     * Clase de apoyo para pintar la tarjeta en la vista y saber qué borrar.
     * - tipo: etiqueta legible (ej. "Dispositivo - Audífono") que muestras en el badge.
     * - categoria: clave lógica (ej. "Audifono", "Movil", "Labial", etc.) para decidir a qué DAO llamar.
     */
    public static class Producto {
        private int id;              // id SOLO visual, no se usa para borrar
        private String tipo;         // etiqueta visible en la UI
        private String categoria;    // clave lógica para el switch de eliminación
        private String nombre;
        private String imagen;
        private String precio;

        public Producto(int id, String tipo, String categoria, String nombre, String imagen, String precio) {
            this.id = id;
            this.tipo = tipo;
            this.categoria = categoria;
            this.nombre = nombre;
            this.imagen = imagen;
            this.precio = precio;
        }

        public int getId() { return id; }
        public String getTipo() { return tipo; }
        public String getCategoria() { return categoria; }
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
            lista.add(new Producto(
                i++,
                "Dispositivo - Móvil",
                "Movil",
                s(m::getNombre),
                s(m::getImagen),
                String.valueOf(m.getPrecio())
            ));
        }
        for (Audifono a : addService.listarAudifonos()) {
            lista.add(new Producto(
                i++,
                "Dispositivo - Audífono",
                "Audifono",
                s(a::getNombre),
                s(a::getImagen),
                String.valueOf(a.getPrecio())
            ));
        }
        
        for (Pelicula p : addService.listarPeliculas()) {
        	lista.add(new Producto(
                    i++,
                    "Peluche - Pelicula",
                    "Pelicula",
                    s(p::getNombre),
                    s(p::getImagen),
                    String.valueOf(p.getPrecio())
                ));
        	
        }
        
        
        for (Animal a : addService.listarAnimal()) {
        	lista.add(new Producto(
                    i++,
                    "Peluche - Animal",
                    "Animal",
                    s(a::getNombre),
                    s(a::getImagen),
                    String.valueOf(a.getPrecio())
                ));
        	
        }
        
        

        // Maquillaje
        for (Labial l : addService.listarLabiales()) {
            lista.add(new Producto(
                i++,
                "Maquillaje - Labial",
                "Labial",
                s(l::getNombre),
                s(l::getImagen),
                String.valueOf(l.getPrecio())
            ));
        }
        for (Pestanina p : addService.listarPestaninas()) {
            lista.add(new Producto(
                i++,
                "Maquillaje - Pestañina",
                "Pestanina",
                s(p::getNombre),
                s(p::getImagen),
                String.valueOf(p.getPrecio())
            ));
        }

        // Juguete
        for (JuegoMesa j : addService.listarJuegoMesa()) {
            lista.add(new Producto(
                i++,
                "Juguete - Juego de mesa",
                "JuegoMesa",
                s(j::getNombre),
                s(j::getImagen),
                String.valueOf(j.getPrecio())
            ));
        }

        // Papelería
        for (Colegio c : addService.listarColegios()) {
            lista.add(new Producto(
                i++,
                "Papelería - Colegio",
                "Colegio",
                s(c::getNombre),
                s(c::getImagen),
                String.valueOf(c.getPrecio())
            ));
        }
        for (Oficina o : addService.listarOficinas()) {
            lista.add(new Producto(
                i++,
                "Papelería - Oficina",
                "Oficina",
                s(o::getNombre),
                s(o::getImagen),
                String.valueOf(o.getPrecio())
            ));
        }

        // Ropa
        for (Hombre h : addService.listarHombres()) {
            lista.add(new Producto(
                i++,
                "Ropa - Hombre",
                "Hombre",
                s(h::getNombre),
                s(h::getImagen),
                String.valueOf(h.getPrecio())
            ));
        }
        for (Mujer mu : addService.listarMujeres()) {
            lista.add(new Producto(
                i++,
                "Ropa - Mujer",
                "Mujer",
                s(mu::getNombre),
                s(mu::getImagen),
                String.valueOf(mu.getPrecio())
            ));
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

    public Producto getProductoSeleccionado() { return productoSeleccionado; }
    public void setProductoSeleccionado(Producto productoSeleccionado) { this.productoSeleccionado = productoSeleccionado; }

    /**
     * Elimina realmente desde el almacenamiento (DAO/archivo) usando AddService
     * y una clave real (nombre) según la categoria lógica del producto.
     */
    public void eliminarProducto() {
        if (productoSeleccionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se seleccionó producto."));
            return;
        }

        String categoria = productoSeleccionado.getCategoria();
        String nombre = productoSeleccionado.getNombre();
        boolean eliminado = false;

        try {
            switch (categoria) {
                case "Audifono":
                    eliminado = addService.eliminarAudifono(nombre);
                    break;
                case "Movil":
                    eliminado = addService.eliminarMovil(nombre);
                    break;
                case "Labial":
                    eliminado = addService.eliminarLabial(nombre);
                    break;
                case "Pestanina":
                    eliminado = addService.eliminarPestanina(nombre);
                    break;
                case "JuegoMesa":
                    eliminado = addService.eliminarJuegoMesa(nombre);
                    break;
                case "Colegio":
                    eliminado = addService.eliminarColegio(nombre);
                    break;
                case "Oficina":
                    eliminado = addService.eliminarOficina(nombre);
                    break;
                case "Hombre":
                    eliminado = addService.eliminarHombre(nombre);
                    break;
                case "Mujer":
                    eliminado = addService.eliminarMujer(nombre);
                    break;
                case"Pelicula":
                	eliminado = addService.eliminarPelicula(nombre);
                	break;
                case"Animal":
                	eliminado = addService.eliminarAnimal(nombre);
                	break;
                default:
                    eliminado = false;
            }
        } catch (Exception e) {
            eliminado = false;
        }

        if (eliminado) {
            cargarProductos(); // refresca desde los DAOs -> ya no aparece
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Producto eliminado correctamente."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el producto."));
        }

        // limpiar selección
        productoSeleccionado = null;
    }

    public void comprar() {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto añadido al carrito."));
    }

    public void accionProducto() {
        if (modoEliminar) eliminarProducto();
        else comprar();
    }

    public String imagenSrc(Producto p) {
        if (p == null) return placeholder();
        String img = p.getImagen();
        if (img == null || img.isBlank()) return placeholder();

        String lower = img.toLowerCase();

        // 1) URL absoluta o data URI
        if (lower.startsWith("http://") || lower.startsWith("https://") || lower.startsWith("data:")) {
            return img;
        }

        // 2) Base64 crudo (heurística)
        boolean base64ish = img.matches("^[A-Za-z0-9+/=\\r\\n]+$") && img.length() > 100;
        if (base64ish) {
            return "data:image/png;base64," + img.replaceAll("\\s+", "");
        }

        // 3) Ruta relativa dentro de la app
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String ctx = ec.getRequestContextPath(); // p.ej. /temu
        if (!img.startsWith("/")) {
            img = "/" + img;
        }
        return ctx + img;
    }

    private String placeholder() {
        return "https://via.placeholder.com/400x300?text=Sin+imagen";
    }
    
 // dentro de PrincipalBean
    private String nombreCarrito = "CarritoGlobal"; // o el username
    private transient CarritoService carritoService = new CarritoService();

    public void comprarProducto() {
        if (productoSeleccionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se seleccionó producto."));
            return;
        }

        String nombre = productoSeleccionado.getNombre();
        int precio = 0;
        try {
            // si precio viene con símbolos, limpia:
            precio = Integer.parseInt(productoSeleccionado.getPrecio().replaceAll("[^0-9-]", ""));
        } catch (Exception ignored) {}

        CarritoService carritoService = new CarritoService(); // o inyectado si ya lo tienes
        boolean ok = carritoService.agregarProducto(nombreCarrito, nombre, precio);

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(ok ? FacesMessage.SEVERITY_INFO : FacesMessage.SEVERITY_ERROR,
                             ok ? "Añadido" : "Error",
                             ok ? (nombre + " agregado al carrito por $" + precio) : "No se pudo agregar."));
        productoSeleccionado = null;
    }
}
