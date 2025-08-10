package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class Ropa extends Producto implements Serializable {
	/** talla de la prenda */
	private int talla;
	/** color de la ropa */
	private String color;
	/**
	 * Constructor vacío de la clase Ropa.
	 */
	public Ropa() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor con parámetros para inicializar un producto.
	 * 
	 * @param talla de la prenda.
	 * @param color de la ropa.
	
	 */
	public Ropa(int talla, String color) {
		super();
		this.talla = talla;
		this.color = color;
	}
	/**
	 * Constructor con parámetros para inicializar un producto.
	 * 
	 * @param nombre Nombre del producto.
	 * @param precio Precio del producto.
	 * @param imagen Ruta de la imagen del producto.
	 */
	public Ropa(String nombre, int precio, String imagen) {
		super(nombre, precio,  imagen);
		// TODO Auto-generated constructor stub
	}/**
	 * Constructor con parámetros para inicializar un producto.
	 * 
	 * @param nombre Nombre del producto.
	 * @param precio Precio del producto.
	 * @param imagen Ruta de la imagen del producto.
	 *@param talla de la prenda.
	 * @param color de la ropa.
	 */

	public Ropa(String nombre, int precio, int id, String fecha, String imagen, int talla, String color) {
		super(nombre, precio, imagen);
		this.talla = talla;
		this.color = color;
	}

	public int getTalla() {
		return talla;
	}

	public void setTalla(int talla) {
		this.talla = talla;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Representa el objeto Ropa como una cadena de texto.
	 * 
	 * @return Cadena de texto que representa la ropa.
	 */

	@Override
	public String toString() {
		return super.toString()+"Ropa [talla=" + talla + ", color=" + color + "]";
	}
	

}
