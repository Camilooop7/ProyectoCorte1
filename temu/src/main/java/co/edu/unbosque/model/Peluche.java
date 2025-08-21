package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase abstracta que representa un peluche. Extiende la clase Producto e
 * implementa Serializable.
 */
public abstract class Peluche extends Producto implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Tamaño del peluche. */
	private String tamano;

	/**
	 * Constructor por defecto.
	 */
	public Peluche() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con el tamaño del peluche.
	 * 
	 * @param tamano Tamaño del peluche.
	 */
	public Peluche(String tamano) {
		super();
		this.tamano = tamano;
	}

	/**
	 * Constructor con todos los atributos del peluche.
	 * 
	 * @param nombre Nombre del peluche.
	 * @param precio Precio del peluche.
	 * @param imagen Ruta o descripción de la imagen del peluche.
	 * @param tamano Tamaño del peluche.
	 */
	public Peluche(String nombre, int precio, String imagen, String tamano) {
		super(nombre, precio, imagen);
		this.tamano = tamano;
	}

	/**
	 * Constructor sin el tamaño del peluche.
	 * 
	 * @param nombre Nombre del peluche.
	 * @param precio Precio del peluche.
	 * @param imagen Ruta o descripción de la imagen del peluche.
	 */
	public Peluche(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
	}

	/**
	 * Obtiene el tamaño del peluche.
	 * 
	 * @return Tamaño del peluche.
	 */
	public String getTamano() {
		return tamano;
	}

	/**
	 * Establece el tamaño del peluche.
	 * 
	 * @param tamano Tamaño del peluche.
	 */
	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	/**
	 * Representa el objeto Peluche como una cadena de texto.
	 * 
	 * @return Cadena de texto que representa al peluche.
	 */
	@Override
	public String toString() {
		return super.toString() + "Peluche [tamano=" + tamano + "]";
	}
}
