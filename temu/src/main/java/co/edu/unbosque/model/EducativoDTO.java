package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase DTO que representa un juguete educativo. Extiende la clase Juguete e
 * implementa Serializable.
 */
public class EducativoDTO extends Juguete implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Indica si el juguete es didáctico. */
	private String esDidactico;

	/**
	 * Constructor por defecto.
	 */
	public EducativoDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con la información sobre si el juguete es didáctico.
	 * 
	 * @param esDidactico Indica si el juguete es didáctico.
	 */
	public EducativoDTO(String esDidactico) {
		super();
		this.esDidactico = esDidactico;
	}

	/**
	 * Constructor con todos los atributos del juguete y la información didáctica.
	 * 
	 * @param nombre          Nombre del juguete.
	 * @param precio          Precio del juguete.
	 * @param imagen          Ruta o descripción de la imagen del juguete.
	 * @param edadRecomendada Edad recomendada para el juguete.
	 * @param esDidactico     Indica si el juguete es didáctico.
	 */
	public EducativoDTO(String nombre, int precio, String imagen, String edadRecomendada, String esDidactico) {
		super(nombre, precio, imagen, edadRecomendada);
		this.esDidactico = esDidactico;
	}

	/**
	 * Constructor sin la información didáctica.
	 * 
	 * @param nombre          Nombre del juguete.
	 * @param precio          Precio del juguete.
	 * @param imagen          Ruta o descripción de la imagen del juguete.
	 * @param edadRecomendada Edad recomendada para el juguete.
	 */
	public EducativoDTO(String nombre, int precio, String imagen, String edadRecomendada) {
		super(nombre, precio, imagen, edadRecomendada);
	}

	/**
	 * Obtiene la información sobre si el juguete es didáctico.
	 * 
	 * @return Información didáctica del juguete.
	 */
	public String getEsDidactico() {
		return esDidactico;
	}

	/**
	 * Establece si el juguete es didáctico.
	 * 
	 * @param esDidactico Información didáctica del juguete.
	 */
	public void setEsDidactico(String esDidactico) {
		this.esDidactico = esDidactico;
	}

	/**
	 * Representación en cadena de la instancia de EducativoDTO.
	 * 
	 * @return Cadena con la información del juguete y si es didáctico.
	 */
	@Override
	public String toString() {
		return super.toString() + "Educativo [esDidactico=" + esDidactico + "]";
	}
}
