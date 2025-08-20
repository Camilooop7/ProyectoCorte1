package co.edu.unbosque.model;
import java.io.Serializable;
import co.edu.unbosque.util.structure.LinkedList;

/**
 * Clase que representa un carrito de compras.
 * Implementa  Serializable para permitir la serialización de sus instancias.
 */
public class Carrito implements Serializable {

    /** Versión por defecto para la serialización. */
    private static final long serialVersionUID = 1L;

    /** Nombre del carrito. */
    private String nombre;

    /** Nombre del usuario asociado al carrito. */
    private String nombreU;

    /** Lista enlazada que almacena los nombres de los productos en el carrito. */
    private LinkedList<String> listaNombresProductos;

    /**
     * Constructor por defecto de la clase Carrito.
     * Inicializa la lista de nombres de productos como una lista enlazada vacía.
     */
    public Carrito() {
        this.listaNombresProductos = new LinkedList<>();
    }

    /**
     * Constructor que inicializa el nombre del carrito y el nombre del usuario.
     *
     * @param nombre El nombre del carrito.
     * @param nombreU El nombre del usuario asociado al carrito.
     */
    public Carrito(String nombre, String nombreU) {
        this.nombre = nombre;
        this.nombreU = nombreU;
        this.listaNombresProductos = new LinkedList<>();
    }

    /**
     * Agrega el nombre de un producto a la lista de nombres de productos del carrito.
     *
     * @param nombreProducto El nombre del producto a agregar.
     */
    public void agregarNombreProducto(String nombreProducto) {
        listaNombresProductos.addLastR(nombreProducto);
    }

    /**
     * Obtiene el nombre del carrito.
     *
     * @return El nombre del carrito.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del carrito.
     *
     * @param nombre El nuevo nombre del carrito.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del usuario asociado al carrito.
     *
     * @return El nombre del usuario.
     */
    public String getNombreU() {
        return nombreU;
    }

    /**
     * Establece el nombre del usuario asociado al carrito.
     *
     * @param nombreU El nuevo nombre del usuario.
     */
    public void setNombreU(String nombreU) {
        this.nombreU = nombreU;
    }

    /**
     * Obtiene la lista de nombres de productos en el carrito.
     *
     * @return La lista enlazada de nombres de productos.
     */
    public LinkedList<String> getListaNombresProductos() {
        return listaNombresProductos;
    }

    /**
     * Establece la lista de nombres de productos en el carrito.
     *
     * @param listaNombresProductos La nueva lista de nombres de productos.
     */
    public void setListaNombresProductos(LinkedList<String> listaNombresProductos) {
        this.listaNombresProductos = listaNombresProductos;
    }
}
