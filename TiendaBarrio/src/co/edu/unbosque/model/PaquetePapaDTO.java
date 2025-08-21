package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase DTO que representa un paquete de papa.
 * Extiende la funcionalidad de  Producto e implementa Serializable.
 */
public class PaquetePapaDTO extends Producto implements Serializable {

    /** Versión por defecto para la serialización. */
    private static final long serialVersionUID = 1L;

    /** Indica si el paquete de papa es picante. */
    private boolean esPicante;

    /**
     * Constructor por defecto de la clase PaquetePapaDTO.
     */
    public PaquetePapaDTO() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor que inicializa si el paquete de papa es picante.
     *
     * @param esPicante Indica si el paquete de papa es picante.
     */
    public PaquetePapaDTO(boolean esPicante) {
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
    public PaquetePapaDTO(String nombre, int precio, String imagen, boolean esPicante) {
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
    public PaquetePapaDTO(String nombre, int precio, String imagen) {
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
}
