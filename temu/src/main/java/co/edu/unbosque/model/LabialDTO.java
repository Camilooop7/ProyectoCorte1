package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase DTO que representa un labial. Extiende la clase Maquillaje e implementa
 * Serializable.
 */
public class LabialDTO extends Maquillaje implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Tono del labial. */
	private String tono;

	/**
	 * Constructor por defecto.
	 */
	public LabialDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con el tono del labial.
	 * 
	 * @param tono Tono del labial.
	 */
	public LabialDTO(String tono) {
		super();
		this.tono = tono;
	}

	/**
	 * Constructor con todos los atributos del labial.
	 * 
	 * @param nombre          Nombre del labial.
	 * @param precio          Precio del labial.
	 * @param imagen          Ruta o descripción de la imagen del labial.
	 * @param esApruebaDeAgua Indica si el labial es a prueba de agua.
	 * @param tono            Tono del labial.
	 */
	public LabialDTO(String nombre, int precio, String imagen, String esApruebaDeAgua, String tono) {
		super(nombre, precio, imagen, esApruebaDeAgua);
		this.tono = tono;
	}

	/**
	 * Constructor sin el tono del labial.
	 * 
	 * @param nombre          Nombre del labial.
	 * @param precio          Precio del labial.
	 * @param imagen          Ruta o descripción de la imagen del labial.
	 * @param esApruebaDeAgua Indica si el labial es a prueba de agua.
	 */
	public LabialDTO(String nombre, int precio, String imagen, String esApruebaDeAgua) {
		super(nombre, precio, imagen, esApruebaDeAgua);
	}

	/**
	 * Obtiene el tono del labial.
	 * 
	 * @return Tono del labial.
	 */
	public String getTono() {
		return tono;
	}

	/**
	 * Establece el tono del labial.
	 * 
	 * @param tono Tono del labial.
	 */
	public void setTono(String tono) {
		this.tono = tono;
	}

	/**
	 * Representa el objeto Labial como una cadena de texto.
	 * 
	 * @return Cadena de texto que representa al labial.
	 */
	@Override
	public String toString() {
		return super.toString() + "Labial [tono=" + tono + "]";
	}
}
