package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase abstracta que representa un producto de maquillaje. Extiende la clase
 * Producto e implementa Serializable.
 */
public abstract class Maquillaje extends Producto implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Indica si el maquillaje es a prueba de agua. */
	private String esApruebaDeAgua;

	/**
	 * Constructor por defecto.
	 */
	public Maquillaje() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con la información de resistencia al agua.
	 * 
	 * @param esApruebaDeAgua Indica si el maquillaje es a prueba de agua.
	 */
	public Maquillaje(String esApruebaDeAgua) {
		super();
		this.esApruebaDeAgua = esApruebaDeAgua;
	}

	/**
	 * Constructor con todos los atributos del maquillaje.
	 * 
	 * @param nombre          Nombre del maquillaje.
	 * @param precio          Precio del maquillaje.
	 * @param imagen          Ruta o descripción de la imagen del maquillaje.
	 * @param esApruebaDeAgua Indica si el maquillaje es a prueba de agua.
	 */
	public Maquillaje(String nombre, int precio, String imagen, String esApruebaDeAgua) {
		super(nombre, precio, imagen);
		this.esApruebaDeAgua = esApruebaDeAgua;
	}

	/**
	 * Constructor sin la información de resistencia al agua.
	 * 
	 * @param nombre Nombre del maquillaje.
	 * @param precio Precio del maquillaje.
	 * @param imagen Ruta o descripción de la imagen del maquillaje.
	 */
	public Maquillaje(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
	}

	/**
	 * Obtiene la información de resistencia al agua.
	 * 
	 * @return Información de resistencia al agua.
	 */
	public String isEsApruebaDeAgua() {
		return esApruebaDeAgua;
	}

	/**
	 * Establece la información de resistencia al agua.
	 * 
	 * @param esApruebaDeAgua Información de resistencia al agua.
	 */
	public void setEsApruebaDeAgua(String esApruebaDeAgua) {
		this.esApruebaDeAgua = esApruebaDeAgua;
	}

	/**
	 * Representa el objeto Maquillaje como una cadena de texto.
	 * 
	 * @return Cadena de texto que representa al maquillaje.
	 */
	@Override
	public String toString() {
		return super.toString() + "Maquillaje [esApruebaDeAgua=" + esApruebaDeAgua + "]";
	}
}
