package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase DTO que representa ropa para mujer. Extiende la clase Ropa e implementa
 * Serializable.
 */
public class MujerDTO extends Ropa implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Indica si la prenda es un conjunto. */
	private String esConjunto;

	/**
	 * Constructor por defecto.
	 */
	public MujerDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con la información de si es un conjunto.
	 * 
	 * @param esConjunto Indica si la prenda es un conjunto.
	 */
	public MujerDTO(String esConjunto) {
		super();
		this.esConjunto = esConjunto;
	}

	/**
	 * Constructor con todos los atributos de la prenda.
	 * 
	 * @param nombre     Nombre de la prenda.
	 * @param precio     Precio de la prenda.
	 * @param imagen     Ruta o descripción de la imagen de la prenda.
	 * @param talla      Talla de la prenda.
	 * @param color      Color de la prenda.
	 * @param esConjunto Indica si la prenda es un conjunto.
	 */
	public MujerDTO(String nombre, int precio, String imagen, String talla, String color, String esConjunto) {
		super(nombre, precio, imagen, talla, color);
		this.esConjunto = esConjunto;
	}

	/**
	 * Constructor sin la información de si es un conjunto.
	 * 
	 * @param nombre Nombre de la prenda.
	 * @param precio Precio de la prenda.
	 * @param imagen Ruta o descripción de la imagen de la prenda.
	 * @param talla  Talla de la prenda.
	 * @param color  Color de la prenda.
	 */
	public MujerDTO(String nombre, int precio, String imagen, String talla, String color) {
		super(nombre, precio, imagen, talla, color);
	}

	/**
	 * Obtiene la información de si la prenda es un conjunto.
	 * 
	 * @return Información de si es un conjunto.
	 */
	public String isEsConjunto() {
		return esConjunto;
	}

	/**
	 * Establece si la prenda es un conjunto.
	 * 
	 * @param esConjunto Información de si es un conjunto.
	 */
	public void setEsConjunto(String esConjunto) {
		this.esConjunto = esConjunto;
	}

	/**
	 * Representa el objeto Mujer como una cadena de texto.
	 * 
	 * @return Cadena de texto que representa la prenda.
	 */
	@Override
	public String toString() {
		return super.toString() + "Mujer [esConjunto=" + esConjunto + "]";
	}
}
