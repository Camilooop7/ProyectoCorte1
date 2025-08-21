package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase DTO que representa un dispositivo móvil. Extiende la clase
 * DispositivoElectronico e implementa Serializable.
 */
public class MovilDTO extends DispositivoElectronico implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Capacidad de almacenamiento del móvil. */
	private String almacenamiento;

	/**
	 * Constructor por defecto.
	 */
	public MovilDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con la capacidad de almacenamiento.
	 * 
	 * @param almacenamiento Capacidad de almacenamiento del móvil.
	 */
	public MovilDTO(String almacenamiento) {
		super();
		this.almacenamiento = almacenamiento;
	}

	/**
	 * Constructor con todos los atributos del móvil.
	 * 
	 * @param nombre         Nombre del móvil.
	 * @param precio         Precio del móvil.
	 * @param imagen         Ruta o descripción de la imagen del móvil.
	 * @param modelo         Modelo del móvil.
	 * @param almacenamiento Capacidad de almacenamiento del móvil.
	 */
	public MovilDTO(String nombre, int precio, String imagen, String modelo, String almacenamiento) {
		super(nombre, precio, imagen, modelo);
		this.almacenamiento = almacenamiento;
	}

	/**
	 * Constructor sin la capacidad de almacenamiento.
	 * 
	 * @param nombre Nombre del móvil.
	 * @param precio Precio del móvil.
	 * @param imagen Ruta o descripción de la imagen del móvil.
	 * @param modelo Modelo del móvil.
	 */
	public MovilDTO(String nombre, int precio, String imagen, String modelo) {
		super(nombre, precio, imagen, modelo);
	}

	/**
	 * Obtiene la capacidad de almacenamiento del móvil.
	 * 
	 * @return Capacidad de almacenamiento.
	 */
	public String getAlmacenamiento() {
		return almacenamiento;
	}

	/**
	 * Establece la capacidad de almacenamiento del móvil.
	 * 
	 * @param almacenamiento Capacidad de almacenamiento.
	 */
	public void setAlmacenamiento(String almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	/**
	 * Representa el objeto Móvil como una cadena de texto.
	 * 
	 * @return Cadena de texto que representa al móvil.
	 */
	@Override
	public String toString() {
		return super.toString() + "Movil [almacenamiento=" + almacenamiento + "]";
	}
}
