package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase que representa un audífono. Extiende la clase DispositivoElectronico e
 * implementa Serializable.
 */
public class Audifono extends DispositivoElectronico implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Tipo de conexión del audífono. */
	private String tipoConexion;

	/**
	 * Constructor por defecto.
	 */
	public Audifono() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con el tipo de conexión.
	 * 
	 * @param tipoConexion Tipo de conexión del audífono.
	 */
	public Audifono(String tipoConexion) {
		super();
		this.tipoConexion = tipoConexion;
	}

	/**
	 * Constructor con todos los atributos del dispositivo y el tipo de conexión.
	 * 
	 * @param nombre       Nombre del audífono.
	 * @param precio       Precio del audífono.
	 * @param imagen       Ruta o descripción de la imagen del audífono.
	 * @param modelo       Modelo del audífono.
	 * @param tipoConexion Tipo de conexión del audífono.
	 */
	public Audifono(String nombre, int precio, String imagen, String modelo, String tipoConexion) {
		super(nombre, precio, imagen, modelo);
		this.tipoConexion = tipoConexion;
	}

	/**
	 * Constructor sin el tipo de conexión.
	 * 
	 * @param nombre Nombre del audífono.
	 * @param precio Precio del audífono.
	 * @param imagen Ruta o descripción de la imagen del audífono.
	 * @param modelo Modelo del audífono.
	 */
	public Audifono(String nombre, int precio, String imagen, String modelo) {
		super(nombre, precio, imagen, modelo);
	}

	/**
	 * Obtiene el tipo de conexión.
	 * 
	 * @return Tipo de conexión.
	 */
	public String getTipoConexion() {
		return tipoConexion;
	}

	/**
	 * Establece el tipo de conexión.
	 * 
	 * @param tipoConexion Tipo de conexión.
	 */
	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}

	/**
	 * Representación en cadena de la instancia de Audifono.
	 * 
	 * @return Cadena con la información del dispositivo y el tipo de conexión.
	 */
	@Override
	public String toString() {
		return super.toString() + "Audifono [tipoConexion=" + tipoConexion + "]";
	}
}
