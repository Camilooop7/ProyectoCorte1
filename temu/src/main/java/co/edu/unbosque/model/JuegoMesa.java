package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase que representa un juego de mesa. Extiende la clase Juguete e implementa
 * Serializable.
 */
public class JuegoMesa extends Juguete implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Cantidad de personas para las que está diseñado el juego. */
	private String cantidadPersona;

	/**
	 * Constructor por defecto.
	 */
	public JuegoMesa() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con la cantidad de personas para el juego.
	 * 
	 * @param cantidadPersona Cantidad de personas para las que está diseñado el
	 *                        juego.
	 */
	public JuegoMesa(String cantidadPersona) {
		super();
		this.cantidadPersona = cantidadPersona;
	}

	/**
	 * Constructor con todos los atributos del juego y la cantidad de personas.
	 * 
	 * @param nombre          Nombre del juego.
	 * @param precio          Precio del juego.
	 * @param imagen          Ruta o descripción de la imagen del juego.
	 * @param edadRecomendada Edad recomendada para el juego.
	 * @param cantidadPersona Cantidad de personas para las que está diseñado el
	 *                        juego.
	 */
	public JuegoMesa(String nombre, int precio, String imagen, String edadRecomendada, String cantidadPersona) {
		super(nombre, precio, imagen, edadRecomendada);
		this.cantidadPersona = cantidadPersona;
	}

	/**
	 * Constructor sin la cantidad de personas.
	 * 
	 * @param nombre          Nombre del juego.
	 * @param precio          Precio del juego.
	 * @param imagen          Ruta o descripción de la imagen del juego.
	 * @param edadRecomendada Edad recomendada para el juego.
	 */
	public JuegoMesa(String nombre, int precio, String imagen, String edadRecomendada) {
		super(nombre, precio, imagen, edadRecomendada);
	}

	/**
	 * Obtiene la cantidad de personas para las que está diseñado el juego.
	 * 
	 * @return Cantidad de personas.
	 */
	public String getCantidadPersona() {
		return cantidadPersona;
	}

	/**
	 * Establece la cantidad de personas para las que está diseñado el juego.
	 * 
	 * @param cantidadPersona Cantidad de personas.
	 */
	public void setCantidadPersona(String cantidadPersona) {
		this.cantidadPersona = cantidadPersona;
	}

	/**
	 * Representación en cadena de la instancia de JuegoMesa.
	 * 
	 * @return Cadena con la información del juego y la cantidad de personas.
	 */
	@Override
	public String toString() {
		return super.toString() + "JuegoMesa [cantidadPersona=" + cantidadPersona + "]";
	}
}
