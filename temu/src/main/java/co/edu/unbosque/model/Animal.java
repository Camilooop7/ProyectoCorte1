package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase que representa un animal de peluche. Extiende la clase Peluche e
 * implementa Serializable.
 */
public class Animal extends Peluche implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Tipo de animal que representa el peluche. */
	private String animal;

	/**
	 * Constructor por defecto.
	 */
	public Animal() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con el tipo de animal.
	 * 
	 * @param animal Tipo de animal que representa el peluche.
	 */
	public Animal(String animal) {
		super();
		this.animal = animal;
	}

	/**
	 * Constructor con todos los atributos del peluche y el tipo de animal.
	 * 
	 * @param nombre Nombre del peluche.
	 * @param precio Precio del peluche.
	 * @param imagen Ruta o descripción de la imagen del peluche.
	 * @param tamano Tamaño del peluche.
	 * @param animal Tipo de animal que representa el peluche.
	 */
	public Animal(String nombre, int precio, String imagen, String tamano, String animal) {
		super(nombre, precio, imagen, tamano);
		this.animal = animal;
	}

	/**
	 * Constructor sin el tipo de animal.
	 * 
	 * @param nombre Nombre del peluche.
	 * @param precio Precio del peluche.
	 * @param imagen Ruta o descripción de la imagen del peluche.
	 * @param tamano Tamaño del peluche.
	 */
	public Animal(String nombre, int precio, String imagen, String tamano) {
		super(nombre, precio, imagen, tamano);
	}

	/**
	 * Obtiene el tipo de animal.
	 * 
	 * @return Tipo de animal.
	 */
	public String getAnimal() {
		return animal;
	}

	/**
	 * Establece el tipo de animal.
	 * 
	 * @param animal Tipo de animal.
	 */
	public void setAnimal(String animal) {
		this.animal = animal;
	}

	/**
	 * Representación en cadena de la instancia de Animal.
	 * 
	 * @return Cadena con la información del peluche y el tipo de animal.
	 */
	@Override
	public String toString() {
		return super.toString() + "Animal [animal=" + animal + "]";
	}
}
