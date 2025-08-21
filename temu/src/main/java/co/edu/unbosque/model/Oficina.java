package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase que representa un artículo de papelería para oficina. Extiende la clase
 * Papeleria e implementa Serializable.
 */
public class Oficina extends Papeleria implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Indica si el artículo es decorativo. */
	private String esDecorativo;

	/**
	 * Constructor por defecto.
	 */
	public Oficina() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con la información de si es decorativo.
	 * 
	 * @param esDecorativo Indica si el artículo es decorativo.
	 */
	public Oficina(String esDecorativo) {
		super();
		this.esDecorativo = esDecorativo;
	}

	/**
	 * Constructor con todos los atributos del artículo.
	 * 
	 * @param nombre          Nombre del artículo.
	 * @param precio          Precio del artículo.
	 * @param imagen          Ruta o descripción de la imagen del artículo.
	 * @param cantidadPaquete Cantidad por paquete del artículo.
	 * @param esDecorativo    Indica si el artículo es decorativo.
	 */
	public Oficina(String nombre, int precio, String imagen, String cantidadPaquete, String esDecorativo) {
		super(nombre, precio, imagen, cantidadPaquete);
		this.esDecorativo = esDecorativo;
	}

	/**
	 * Constructor sin la información de si es decorativo.
	 * 
	 * @param nombre          Nombre del artículo.
	 * @param precio          Precio del artículo.
	 * @param imagen          Ruta o descripción de la imagen del artículo.
	 * @param cantidadPaquete Cantidad por paquete del artículo.
	 */
	public Oficina(String nombre, int precio, String imagen, String cantidadPaquete) {
		super(nombre, precio, imagen, cantidadPaquete);
	}

	/**
	 * Obtiene la información de si el artículo es decorativo.
	 * 
	 * @return Información de si es decorativo.
	 */
	public String isEsDecorativo() {
		return esDecorativo;
	}

	/**
	 * Establece si el artículo es decorativo.
	 * 
	 * @param esDecorativo Información de si es decorativo.
	 */
	public void setEsDecorativo(String esDecorativo) {
		this.esDecorativo = esDecorativo;
	}

	/**
	 * Representa el objeto Oficina como una cadena de texto.
	 * 
	 * @return Cadena de texto que representa al artículo.
	 */
	@Override
	public String toString() {
		return super.toString() + "Oficina [esDecorativo=" + esDecorativo + "]";
	}
}
