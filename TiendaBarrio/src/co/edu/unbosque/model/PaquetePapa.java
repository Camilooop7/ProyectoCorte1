package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase que representa un paquete de papa, extendiendo la funcionalidad de  Producto.
 * Implementa  Serializable para permitir la serialización de sus instancias.
 */
public class PaquetePapa extends Producto implements Serializable {

    /** Versión por defecto para la serialización. */
    private static final long serialVersionUID = 1L;

    /** Indica si el paquete de papa es picante. */
    private boolean esPicante;

    /**
     * Constructor por defecto de la clase PaquetePapa.
     */
    public PaquetePapa() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor que inicializa si el paquete de papa es picante.
     *
     * @param esPicante Indica si el paquete de papa es picante.
     */
    public PaquetePapa(boolean esPicante) {
        super();
        this.esPicante = esPicante;
    }

    /**
     * Constructor que inicializa todos los atributos del paquete de papa.
     *
     * @param nombre El nombre del paquete de papa.
     * @param precio El precio del paquete de papa.
     * @param imagen La ruta o nombre de la imagen asociada al paquete de papa.
     * @param esPicante Indica si el paquete de papa es picante.
     */
    public PaquetePapa(String nombre, int precio, String imagen, boolean esPicante) {
        super(nombre, precio, imagen);
        this.esPicante = esPicante;
    }

    /**
     * Constructor que inicializa los atributos básicos del paquete de papa.
     *
     * @param nombre El nombre del paquete de papa.
     * @param precio El precio del paquete de papa.
     * @param imagen La ruta o nombre de la imagen asociada al paquete de papa.
     */
    public PaquetePapa(String nombre, int precio, String imagen) {
        super(nombre, precio, imagen);
        // TODO Auto-generated constructor stub
    }

    /**
     * Indica si el paquete de papa es picante.
     *
     * @return {@code true} si el paquete de papa es picante, {@code false} en caso contrario.
     */
    public boolean isEsPicante() {
        return esPicante;
    }

    /**
     * Establece si el paquete de papa es picante.
     *
     * @param esPicante {@code true} si el paquete de papa es picante, {@code false} en caso contrario.
     */
    public void setEsPicante(boolean esPicante) {
        this.esPicante = esPicante;
    }

    /**
     * Devuelve una representación en cadena del paquete de papa, incluyendo si es picante.
     *
     * @return Una cadena que representa el paquete de papa.
     */
    @Override
    public String toString() {
    	return super.toString();
    }
}
