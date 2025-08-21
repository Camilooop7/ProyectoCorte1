package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase DTO que representa ropa para hombre. Extiende la clase Ropa e
 * implementa Serializable.
 */
public class HombreDTO extends Ropa implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Indica si la ropa es deportiva. */
	private String esDeportiva;

	/**
	 * Constructor por defecto.
	 */
	public HombreDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con la información sobre si la ropa es deportiva.
	 * 
	 * @param esDeportiva Indica si la ropa es deportiva.
	 */
	public HombreDTO(String esDeportiva) {
		super();
		this.esDeportiva = esDeportiva;
	}

	/**
	 * Constructor con todos los atributos de la ropa y la información deportiva.
	 * 
	 * @param nombre      Nombre de la prenda.
	 * @param precio      Precio de la prenda.
	 * @param imagen      Ruta o descripción de la imagen de la prenda.
	 * @param talla       Talla de la prenda.
	 * @param color       Color de la prenda.
	 * @param esDeportiva Indica si la ropa es deportiva.
	 */
	public HombreDTO(String nombre, int precio, String imagen, String talla, String color, String esDeportiva) {
		super(nombre, precio, imagen, talla, color);
		this.esDeportiva = esDeportiva;
	}

	/**
	 * Constructor sin la información deportiva.
	 * 
	 * @param nombre Nombre de la prenda.
	 * @param precio Precio de la prenda.
	 * @param imagen Ruta o descripción de la imagen de la prenda.
	 * @param talla  Talla de la prenda.
	 * @param color  Color de la prenda.
	 */
	public HombreDTO(String nombre, int precio, String imagen, String talla, String color) {
		super(nombre, precio, imagen, talla, color);
	}

	/**
	 * Obtiene la información sobre si la ropa es deportiva.
	 * 
	 * @return Información deportiva de la prenda.
	 */
	public String getEsDeportiva() {
		return esDeportiva;
	}

	/**
	 * Establece si la ropa es deportiva.
	 * 
	 * @param esDeportiva Información deportiva de la prenda.
	 */
	public void setEsDeportiva(String esDeportiva) {
		this.esDeportiva = esDeportiva;
	}

	/**
	 * Representación en cadena de la instancia de HombreDTO.
	 * 
	 * @return Cadena con la información de la prenda y si es deportiva.
	 */
	@Override
	public String toString() {
		return super.toString() + "Hombre [esDeportiva=" + esDeportiva + "]";
	}
}
