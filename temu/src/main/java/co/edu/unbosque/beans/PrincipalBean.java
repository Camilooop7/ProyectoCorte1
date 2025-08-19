package co.edu.unbosque.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@Named(value = "paginaprincipalbean")
@RequestScoped
public class PrincipalBean {

    private boolean modoEliminar = false;
    private int idProductoSeleccionado;

    // Clase interna para representar un producto
    public static class Producto {
        private int id;
        private String tipo;
        private String nombre;
        private String imagen;
        private String precio;

        public Producto(int id, String tipo, String nombre, String imagen, String precio) {
            this.id = id;
            this.tipo = tipo;
            this.nombre = nombre;
            this.imagen = imagen;
            this.precio = precio;
        }

        // Getters
        public int getId() { return id; }
        public String getTipo() { return tipo; }
        public String getNombre() { return nombre; }
        public String getImagen() { return imagen; }
        public String getPrecio() { return precio; }
    }

    // Lista de productos
    private List<Producto> productos;

    public PrincipalBean() {
        productos = new ArrayList<>();
        productos.add(new Producto(1, "JEAN", "CARGO PANT BLACK", "https://m.media-amazon.com/images/I/81Ax3qHF-cL._UF894,1000_QL80_.jpg", "$90.000"));
        productos.add(new Producto(2, "BUZO", "RED HOODIE", "https://acdn-us.mitiendanube.com/stores/001/642/173/products/buzo-hoodie-rojo-b2e6d774c33c3375fb17197766136403-1024-1024.png", "$28,450"));
        productos.add(new Producto(3, "CAMISAS", "CAMISA verde", "https://img.freepik.com/fotos-premium/modelo-camiseta-verde-adelante-atras-vista-aislada-camisa-verde-plana-plantilla-diseno-camisa_21336-10799.jpg", "$29,000"));
        productos.add(new Producto(4, "Iphone", "Iphone 19", "https://sm.mashable.com/mashable_in/seo/default/z3wq-71_57j7.jpg", "$500,000"));
        productos.add(new Producto(5, "Juego de mesas", "monopoly", "https://www.shutterstock.com/image-photo/monopoly-3d-character-cartoon-funny-2453789293.jpg", "$75,000"));
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public boolean isModoEliminar() {
        return modoEliminar;
    }

    public void cambiarModoEliminar() {
        modoEliminar = !modoEliminar;
    }

    public void setIdProductoSeleccionado(int idProductoSeleccionado) {
        this.idProductoSeleccionado = idProductoSeleccionado;
    }

    public int getIdProductoSeleccionado() {
        return idProductoSeleccionado;
    }

    public void eliminarProducto() {
        productos.removeIf(producto -> producto.getId() == idProductoSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto eliminado con éxito."));
    }

    public void comprar() {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto añadido al carrito."));
    }

    public void accionProducto() {
        if (modoEliminar) {
            eliminarProducto();
        } else {
            comprar();
        }
    }
}
