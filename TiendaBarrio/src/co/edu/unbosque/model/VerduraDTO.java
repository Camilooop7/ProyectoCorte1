package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase DTO que representa una verdura. Extiende la funcionalidad de Producto e
 * implementa Serializable.
 */
public class VerduraDTO extends Producto implements Serializable {

	/** Versión por defecto para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Forma de la verdura. */
	private String forma;

	/** Tamaño de la verdura. */
	private String tamano;

	/**
	 * Constructor por defecto de la clase VerduraDTO.
	 */
	public VerduraDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor que inicializa la forma y el tamaño de la verdura.
	 *
	 * @param forma  La forma de la verdura.
	 * @param tamano El tamaño de la verdura.
	 */
	public VerduraDTO(String forma, String tamano) {
		super();
		this.forma = forma;
		this.tamano = tamano;
	}

	/**
	 * Constructor que inicializa todos los atributos de la verdura.
	 *
	 * @param nombre El nombre de la verdura.
	 * @param precio El precio de la verdura.
	 * @param imagen La ruta o nombre de la imagen asociada a la verdura.
	 * @param forma  La forma de la verdura.
	 * @param tamano El tamaño de la verdura.
	 */
	public VerduraDTO(String nombre, int precio, String imagen, String forma, String tamano) {
		super(nombre, precio, imagen);
		this.forma = forma;
		this.tamano = tamano;
	}

	/**
	 * Constructor que inicializa los atributos básicos de la verdura.
	 *
	 * @param nombre El nombre de la verdura.
	 * @param precio El precio de la verdura.
	 * @param imagen La ruta o nombre de la imagen asociada a la verdura.
	 */
	public VerduraDTO(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Obtiene la forma de la verdura.
	 *
	 * @return La forma de la verdura.
	 */
	public String getForma() {
		return forma;
	}

	/**
	 * Establece la forma de la verdura.
	 *
	 * @param forma La nueva forma de la verdura.
	 */
	public void setForma(String forma) {
		this.forma = forma;
	}

	/**
	 * Obtiene el tamaño de la verdura.
	 *
	 * @return El tamaño de la verdura.
	 */
	public String getTamano() {
		return tamano;
	}

	/**
	 * Establece el tamaño de la verdura.
	 *
	 * @param tamano El nuevo tamaño de la verdura.
	 */
	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	/**
	 * Devuelve una representación en cadena de la verdura, incluyendo su forma y
	 * tamaño.
	 *
	 * @return Una cadena que representa la verdura.
	 */
	@Override
	public String toString() {
		return super.toString() + "Verdura [forma=" + forma + ", tamano=" + tamano + "]";
	}
}
