package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase DTO que representa una gaseosa. Extiende la funcionalidad de Bebida e
 * implementa Serializable.
 */
public class GaseosaDTO extends Bebida implements Serializable {

	/** Versión por defecto para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Indica si la gaseosa es de tipo zero (sin azúcar). */
	private boolean esZero;

	/**
	 * Constructor por defecto de la clase GaseosaDTO.
	 */
	public GaseosaDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor que inicializa si la gaseosa es zero.
	 *
	 * @param esZero Indica si la gaseosa es zero.
	 */
	public GaseosaDTO(boolean esZero) {
		super();
		this.esZero = esZero;
	}

	/**
	 * Constructor que inicializa todos los atributos de la gaseosa.
	 *
	 * @param nombre       El nombre de la gaseosa.
	 * @param precio       El precio de la gaseosa.
	 * @param imagen       La ruta o nombre de la imagen asociada a la gaseosa.
	 * @param presentacion La presentación de la gaseosa.
	 * @param esZero       Indica si la gaseosa es zero.
	 */
	public GaseosaDTO(String nombre, int precio, String imagen, String presentacion, boolean esZero) {
		super(nombre, precio, imagen, presentacion);
		this.esZero = esZero;
	}

	/**
	 * Constructor que inicializa los atributos básicos de la gaseosa.
	 *
	 * @param nombre       El nombre de la gaseosa.
	 * @param precio       El precio de la gaseosa.
	 * @param imagen       La ruta o nombre de la imagen asociada a la gaseosa.
	 * @param presentacion La presentación de la gaseosa.
	 */
	public GaseosaDTO(String nombre, int precio, String imagen, String presentacion) {
		super(nombre, precio, imagen, presentacion);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Indica si la gaseosa es zero.
	 *
	 * @return {@code true} si la gaseosa es zero, {@code false} en caso contrario.
	 */
	public boolean isEsZero() {
		return esZero;
	}

	/**
	 * Establece si la gaseosa es zero.
	 *
	 * @param esZero {@code true} si la gaseosa es zero, {@code false} en caso
	 *               contrario.
	 */
	public void setEsZero(boolean esZero) {
		this.esZero = esZero;
	}

	/**
	 * Devuelve una representación en cadena de la gaseosa, incluyendo si es zero.
	 *
	 * @return Una cadena que representa la gaseosa.
	 */
	@Override
	public String toString() {
		return super.toString() + "Gaseosa [esZero=" + esZero + "]";
	}
}
