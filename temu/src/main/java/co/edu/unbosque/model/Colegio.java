package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase que representa un artículo de papelería para colegio. Extiende la clase
 * Papeleria e implementa Serializable.
 */
public class Colegio extends Papeleria implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Indica si el artículo es seguro. */
	private String esSeguro;

	/**
	 * Constructor por defecto.
	 */
	public Colegio() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con la información de seguridad.
	 * 
	 * @param esSeguro Indica si el artículo es seguro.
	 */
	public Colegio(String esSeguro) {
		super();
		this.esSeguro = esSeguro;
	}

	/**
	 * Constructor con todos los atributos del artículo y la información de
	 * seguridad.
	 * 
	 * @param nombre          Nombre del artículo.
	 * @param precio          Precio del artículo.
	 * @param imagen          Ruta o descripción de la imagen del artículo.
	 * @param cantidadPaquete Cantidad por paquete del artículo.
	 * @param esSeguro        Indica si el artículo es seguro.
	 */
	public Colegio(String nombre, int precio, String imagen, String cantidadPaquete, String esSeguro) {
		super(nombre, precio, imagen, cantidadPaquete);
		this.esSeguro = esSeguro;
	}

	/**
	 * Constructor sin la información de seguridad.
	 * 
	 * @param nombre          Nombre del artículo.
	 * @param precio          Precio del artículo.
	 * @param imagen          Ruta o descripción de la imagen del artículo.
	 * @param cantidadPaquete Cantidad por paquete del artículo.
	 */
	public Colegio(String nombre, int precio, String imagen, String cantidadPaquete) {
		super(nombre, precio, imagen, cantidadPaquete);
	}

	/**
	 * Obtiene la información de seguridad.
	 * 
	 * @return Información de seguridad.
	 */
	public String getEsSeguro() {
		return esSeguro;
	}

	/**
	 * Establece la información de seguridad.
	 * 
	 * @param esSeguro Información de seguridad.
	 */
	public void setEsSeguro(String esSeguro) {
		this.esSeguro = esSeguro;
	}

	/**
	 * Representación en cadena de la instancia de Colegio.
	 * 
	 * @return Cadena con la información del artículo y la seguridad.
	 */
	@Override
	public String toString() {
		return super.toString() + "Colegio [esSeguro=" + esSeguro + "]";
	}
}
