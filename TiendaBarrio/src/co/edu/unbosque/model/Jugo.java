package co.edu.unbosque.model;
import java.io.Serializable;

/**
 * Clase que representa un jugo, extendiendo la funcionalidad de  Bebida.
 * Implementa  Serializable para permitir la serialización de sus instancias.
 */
public class Jugo extends Bebida implements Serializable {

    /** Versión por defecto para la serialización. */
    private static final long serialVersionUID = 1L;

    /** Indica si el jugo es natural. */
    private boolean esNatural;

    /**
     * Constructor por defecto de la clase Jugo.
     */
    public Jugo() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor que inicializa si el jugo es natural.
     *
     * @param esNatural Indica si el jugo es natural.
     */
    public Jugo(boolean esNatural) {
        super();
        this.esNatural = esNatural;
    }

    /**
     * Constructor que inicializa todos los atributos del jugo.
     *
     * @param nombre        El nombre del jugo.
     * @param precio        El precio del jugo.
     * @param imagen        La ruta o nombre de la imagen asociada al jugo.
     * @param presentacion  La presentación del jugo.
     * @param esNatural     Indica si el jugo es natural.
     */
    public Jugo(String nombre, int precio, String imagen, String presentacion, boolean esNatural) {
        super(nombre, precio, imagen, presentacion);
        this.esNatural = esNatural;
    }

    /**
     * Constructor que inicializa los atributos básicos del jugo.
     *
     * @param nombre        El nombre del jugo.
     * @param precio        El precio del jugo.
     * @param imagen        La ruta o nombre de la imagen asociada al jugo.
     * @param presentacion  La presentación del jugo.
     */
    public Jugo(String nombre, int precio, String imagen, String presentacion) {
        super(nombre, precio, imagen, presentacion);
        // TODO Auto-generated constructor stub
    }

    /**
     * Indica si el jugo es natural.
     *
     * @return {@code true} si el jugo es natural, {@code false} en caso contrario.
     */
    public boolean isEsNatural() {
        return esNatural;
    }

    /**
     * Establece si el jugo es natural.
     *
     * @param esNatural {@code true} si el jugo es natural, {@code false} en caso contrario.
     */
    public void setEsNatural(boolean esNatural) {
        this.esNatural = esNatural;
    }

    /**
     * Devuelve una representación en cadena del jugo, incluyendo si es natural.
     *
     * @return Una cadena que representa el jugo.
     */
    @Override
    public String toString() {
        return super.toString() + "Jugo [esNatural=" + esNatural + "]";
    }
}
