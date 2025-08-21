package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase abstracta que representa un juguete. Extiende la clase Producto e
 * implementa Serializable.
 */
public abstract class Juguete extends Producto implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Edad recomendada para el juguete. */
	private String edadRecomendada;

	/**
	 * Constructor por defecto.
	 */
	public Juguete() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con la edad recomendada para el juguete.
	 * 
	 * @param edadRecomendada Edad recomendada para el juguete.
	 */
	public Juguete(String edadRecomendada) {
		super();
		this.edadRecomendada = edadRecomendada;
	}

	/**
	 * Constructor con todos los atributos del juguete.
	 * 
	 * @param nombre          Nombre del juguete.
	 * @param precio          Precio del juguete.
	 * @param imagen          Ruta o descripción de la imagen del juguete.
	 * @param edadRecomendada Edad recomendada para el juguete.
	 */
	public Juguete(String nombre, int precio, String imagen, String edadRecomendada) {
		super(nombre, precio, imagen);
		this.edadRecomendada = edadRecomendada;
	}

	/**
	 * Constructor sin la edad recomendada.
	 * 
	 * @param nombre Nombre del juguete.
	 * @param precio Precio del juguete.
	 * @param imagen Ruta o descripción de la imagen del juguete.
	 */
	public Juguete(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
	}

	/**
	 * Obtiene la edad recomendada para el juguete.
	 * 
	 * @return Edad recomendada.
	 */
	public String getEdadRecomendada() {
		return edadRecomendada;
	}

	/**
	 * Establece la edad recomendada para el juguete.
	 * 
	 * @param edadRecomendada Edad recomendada.
	 */
	public void setEdadRecomendada(String edadRecomendada) {
		this.edadRecomendada = edadRecomendada;
	}

	/**
	 * Representación en cadena de la instancia de Juguete.
	 * 
	 * @return Cadena con la información del juguete y la edad recomendada.
	 */
	@Override
	public String toString() {
		return super.toString() + "Juguete [edadRecomendada=" + edadRecomendada + "]";
	}
}
