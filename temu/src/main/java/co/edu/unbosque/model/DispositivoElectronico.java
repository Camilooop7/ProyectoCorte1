package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase abstracta que representa un dispositivo electrónico. Extiende la clase
 * {@link Producto} e implementa Serializable.
 */
public abstract class DispositivoElectronico extends Producto implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Modelo del dispositivo electrónico. */
	private String modelo;

	/**
	 * Constructor por defecto.
	 */
	public DispositivoElectronico() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con el modelo del dispositivo.
	 * 
	 * @param modelo Modelo del dispositivo.
	 */
	public DispositivoElectronico(String modelo) {
		super();
		this.modelo = modelo;
	}

	/**
	 * Constructor con todos los atributos del dispositivo.
	 * 
	 * @param nombre Nombre del dispositivo.
	 * @param precio Precio del dispositivo.
	 * @param imagen Ruta o descripción de la imagen del dispositivo.
	 * @param modelo Modelo del dispositivo.
	 */
	public DispositivoElectronico(String nombre, int precio, String imagen, String modelo) {
		super(nombre, precio, imagen);
		this.modelo = modelo;
	}

	/**
	 * Constructor sin el modelo del dispositivo.
	 * 
	 * @param nombre Nombre del dispositivo.
	 * @param precio Precio del dispositivo.
	 * @param imagen Ruta o descripción de la imagen del dispositivo.
	 */
	public DispositivoElectronico(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
	}

	/**
	 * Obtiene el modelo del dispositivo.
	 * 
	 * @return Modelo del dispositivo.
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Establece el modelo del dispositivo.
	 * 
	 * @param modelo Modelo del dispositivo.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * Representación en cadena de la instancia de {@code DispositivoElectronico}.
	 * 
	 * @return Cadena con la información del dispositivo y su modelo.
	 */
	@Override
	public String toString() {
		return super.toString() + "DispositivoElectronico [modelo=" + modelo + "]";
	}
}
