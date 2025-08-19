package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa un carrito de compras.
 */
public class Carrito implements Serializable {
	private static final long serialVersionUID = 1L;		
	private String nombre;
	/**
	 * Lista de productos en el carrito.
	 */
	private ArrayList<String> listaCarrito;

	/**
	 * Constructor vacío.
	 */
	public Carrito() {
		listaCarrito = new ArrayList<>();
	}
	
	

	public Carrito(String nombre, ArrayList<String> listaCarrito) {
		super();
		this.nombre = nombre;
		this.listaCarrito = listaCarrito;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public ArrayList<String> getListaCarrito() {
		return listaCarrito;
	}



	public void setListaCarrito(ArrayList<String> listaCarrito) {
		this.listaCarrito = listaCarrito;
	}





	/**
	 * Obtiene la lista de productos en el carrito.
	 * 
	 * @return Lista de productos en el carrito.
	 */

	
	
	/**
	 * Establece la lista de productos en el carrito.
	 * 
	 * @param listaCarrito Nueva lista de productos.
	 */

}
