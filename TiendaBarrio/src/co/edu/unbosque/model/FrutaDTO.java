package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase DTO que representa una fruta. Extiende la funcionalidad de Producto e
 * implementa Serializable.
 */
public class FrutaDTO extends Producto implements Serializable {

	/** Versión por defecto para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Aroma característico de la fruta. */
	private String aroma;

	/** Sabor característico de la fruta. */
	private String sabor;

	/**
	 * Constructor por defecto de la clase FrutaDTO.
	 */
	public FrutaDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor que inicializa el aroma y el sabor de la fruta.
	 *
	 * @param aroma El aroma de la fruta.
	 * @param sabor El sabor de la fruta.
	 */
	public FrutaDTO(String aroma, String sabor) {
		super();
		this.aroma = aroma;
		this.sabor = sabor;
	}

	/**
	 * Constructor que inicializa todos los atributos de la fruta.
	 *
	 * @param nombre El nombre de la fruta.
	 * @param precio El precio de la fruta.
	 * @param imagen La ruta o nombre de la imagen asociada a la fruta.
	 * @param aroma  El aroma de la fruta.
	 * @param sabor  El sabor de la fruta.
	 */
	public FrutaDTO(String nombre, int precio, String imagen, String aroma, String sabor) {
		super(nombre, precio, imagen);
		this.aroma = aroma;
		this.sabor = sabor;
	}

	/**
	 * Constructor que inicializa los atributos básicos de la fruta.
	 *
	 * @param nombre El nombre de la fruta.
	 * @param precio El precio de la fruta.
	 * @param imagen La ruta o nombre de la imagen asociada a la fruta.
	 */
	public FrutaDTO(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Obtiene el aroma de la fruta.
	 *
	 * @return El aroma de la fruta.
	 */
	public String getAroma() {
		return aroma;
	}

	/**
	 * Establece el aroma de la fruta.
	 *
	 * @param aroma El nuevo aroma de la fruta.
	 */
	public void setAroma(String aroma) {
		this.aroma = aroma;
	}

	/**
	 * Obtiene el sabor de la fruta.
	 *
	 * @return El sabor de la fruta.
	 */
	public String getSabor() {
		return sabor;
	}

	/**
	 * Establece el sabor de la fruta.
	 *
	 * @param sabor El nuevo sabor de la fruta.
	 */
	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	/**
	 * Devuelve una representación en cadena de la fruta, incluyendo su aroma y
	 * sabor.
	 *
	 * @return Una cadena que representa la fruta.
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
