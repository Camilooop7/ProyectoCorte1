package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase que representa un peluche de película. Extiende la clase Peluche e
 * implementa Serializable.
 */
public class Pelicula extends Peluche implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Personaje que representa el peluche. */
	private String personaje;

	/**
	 * Constructor por defecto.
	 */
	public Pelicula() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con el personaje del peluche.
	 * 
	 * @param personaje Personaje que representa el peluche.
	 */
	public Pelicula(String personaje) {
		super();
		this.personaje = personaje;
	}

	/**
	 * Constructor con todos los atributos del peluche.
	 * 
	 * @param nombre    Nombre del peluche.
	 * @param precio    Precio del peluche.
	 * @param imagen    Ruta o descripción de la imagen del peluche.
	 * @param tamano    Tamaño del peluche.
	 * @param personaje Personaje que representa el peluche.
	 */
	public Pelicula(String nombre, int precio, String imagen, String tamano, String personaje) {
		super(nombre, precio, imagen, tamano);
		this.personaje = personaje;
	}

	/**
	 * Constructor sin el personaje del peluche.
	 * 
	 * @param nombre Nombre del peluche.
	 * @param precio Precio del peluche.
	 * @param imagen Ruta o descripción de la imagen del peluche.
	 * @param tamano Tamaño del peluche.
	 */
	public Pelicula(String nombre, int precio, String imagen, String tamano) {
		super(nombre, precio, imagen, tamano);
	}

	/**
	 * Obtiene el personaje que representa el peluche.
	 * 
	 * @return Personaje del peluche.
	 */
	public String getPersonaje() {
		return personaje;
	}

	/**
	 * Establece el personaje que representa el peluche.
	 * 
	 * @param personaje Personaje del peluche.
	 */
	public void setPersonaje(String personaje) {
		this.personaje = personaje;
	}

	/**
	 * Representa el objeto Película como una cadena de texto.
	 * 
	 * @return Cadena de texto que representa al peluche.
	 */
	@Override
	public String toString() {
		return super.toString() + "Pelicula [personaje=" + personaje + "]";
	}
}
