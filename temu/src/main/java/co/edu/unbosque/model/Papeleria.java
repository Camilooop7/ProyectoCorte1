package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase abstracta que representa un artículo de papelería. Extiende la clase
 * Producto e implementa Serializable.
 */
public abstract class Papeleria extends Producto implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Cantidad por paquete del artículo. */
	private String cantidadPaquete;

	/**
	 * Constructor por defecto.
	 */
	public Papeleria() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con la cantidad por paquete.
	 * 
	 * @param cantidadPaquete Cantidad por paquete del artículo.
	 */
	public Papeleria(String cantidadPaquete) {
		super();
		this.cantidadPaquete = cantidadPaquete;
	}

	/**
	 * Constructor con todos los atributos del artículo.
	 * 
	 * @param nombre          Nombre del artículo.
	 * @param precio          Precio del artículo.
	 * @param imagen          Ruta o descripción de la imagen del artículo.
	 * @param cantidadPaquete Cantidad por paquete del artículo.
	 */
	public Papeleria(String nombre, int precio, String imagen, String cantidadPaquete) {
		super(nombre, precio, imagen);
		this.cantidadPaquete = cantidadPaquete;
	}

	/**
	 * Constructor sin la cantidad por paquete.
	 * 
	 * @param nombre Nombre del artículo.
	 * @param precio Precio del artículo.
	 * @param imagen Ruta o descripción de la imagen del artículo.
	 */
	public Papeleria(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
	}

	/**
	 * Obtiene la cantidad por paquete del artículo.
	 * 
	 * @return Cantidad por paquete.
	 */
	public String getCantidadPaquete() {
		return cantidadPaquete;
	}

	/**
	 * Establece la cantidad por paquete del artículo.
	 * 
	 * @param cantidadPaquete Cantidad por paquete.
	 */
	public void setCantidadPaquete(String cantidadPaquete) {
		this.cantidadPaquete = cantidadPaquete;
	}

	/**
	 * Representa el objeto Papelería como una cadena de texto.
	 * 
	 * @return Cadena de texto que representa al artículo.
	 */
	@Override
	public String toString() {
		return super.toString() + "Papeleria [cantidadPaquete=" + cantidadPaquete + "]";
	}
}
