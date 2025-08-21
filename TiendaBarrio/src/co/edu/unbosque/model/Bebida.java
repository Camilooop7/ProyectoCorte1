package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase abstracta que representa una bebida, extendiendo la funcionalidad de
 * Producto. Implementa Serializable para permitir la
 * serialización de sus instancias.
 */
public abstract class Bebida extends Producto implements Serializable {

	/** Versión por defecto para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Atributo que indica la presentación de la bebida. */
	private String presentacion;

	/**
	 * Constructor por defecto de la clase Bebida.
	 */
	public Bebida() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor que inicializa la presentación de la bebida.
	 *
	 * @param presentacion La presentación de la bebida.
	 */
	public Bebida(String presentacion) {
		super();
		this.presentacion = presentacion;
	}

	/**
	 * Constructor que inicializa los atributos nombre, precio, imagen y
	 * presentación de la bebida.
	 *
	 * @param nombre       El nombre de la bebida.
	 * @param precio       El precio de la bebida.
	 * @param imagen       La ruta o nombre de la imagen asociada a la bebida.
	 * @param presentacion La presentación de la bebida.
	 */
	public Bebida(String nombre, int precio, String imagen, String presentacion) {
		super(nombre, precio, imagen);
		this.presentacion = presentacion;
	}

	/**
	 * Constructor que inicializa los atributos nombre, precio e imagen de la
	 * bebida. La presentación se inicializa por defecto o debe ser establecida
	 * posteriormente.
	 *
	 * @param nombre El nombre de la bebida.
	 * @param precio El precio de la bebida.
	 * @param imagen La ruta o nombre de la imagen asociada a la bebida.
	 */
	public Bebida(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Obtiene la presentación de la bebida.
	 *
	 * @return La presentación de la bebida.
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * Establece la presentación de la bebida.
	 *
	 * @param presentacion La nueva presentación de la bebida.
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * Devuelve una representación en cadena de la bebida, incluyendo su
	 * presentación.
	 *
	 * @return Una cadena que representa la bebida.
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
