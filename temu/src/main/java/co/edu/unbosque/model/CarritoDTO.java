package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa un carrito de compras.
 */
public class CarritoDTO implements Serializable {
	private static final long serialVersionUID = 1L;		
	/**
	 * Lista de productos en el carrito.
	 */
	private ArrayList<Producto> listaCarrito;

	/**
	 * Constructor vacío.
	 */
	public CarritoDTO() {
		listaCarrito = new ArrayList<>();
	}

	/**
	 * Obtiene la lista de productos en el carrito.
	 * 
	 * @return Lista de productos en el carrito.
	 */
	public ArrayList<Producto> getListaCarrito() {
		return listaCarrito;
	}

	/**
	 * Establece la lista de productos en el carrito.
	 * 
	 * @param listaCarrito Nueva lista de productos.
	 */
	public void setListaCarrito(ArrayList<Producto> listaCarrito) {
		this.listaCarrito = listaCarrito;
	}
}

