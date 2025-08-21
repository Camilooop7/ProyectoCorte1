package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.edu.unbosque.model.CarritoDTO;
import co.edu.unbosque.model.ModelFacade;

/**
 * Servicio para gestionar el carrito de compras. Permite agregar, listar,
 * eliminar y vaciar productos del carrito.
 */
public class CarritoService {

	/** Instancia de ModelFacade para acceder al DAO del carrito. */
	private final ModelFacade mf;

	/**
	 * Constructor por defecto.
	 */
	public CarritoService() {
		this.mf = new ModelFacade();
	}

	/**
	 * Clase interna que representa un ítem del carrito para la vista.
	 */
	public static final class ItemView {
		private final String nombre;
		private final int precio;
		private final String token;

		/**
		 * Constructor de ItemView.
		 * 
		 * @param nombre Nombre del producto.
		 * @param precio Precio del producto.
		 * @param token  Token crudo del producto (nombre|precio).
		 */
		public ItemView(String nombre, int precio, String token) {
			this.nombre = nombre;
			this.precio = precio;
			this.token = token;
		}

		/**
		 * Obtiene el nombre del producto.
		 * 
		 * @return Nombre del producto.
		 */
		public String getNombre() {
			return nombre;
		}

		/**
		 * Obtiene el precio del producto.
		 * 
		 * @return Precio del producto.
		 */
		public int getPrecio() {
			return precio;
		}

		/**
		 * Obtiene el token crudo del producto.
		 * 
		 * @return Token crudo del producto.
		 */
		public String getToken() {
			return token;
		}
	}

	/**
	 * Obtiene un carrito por su nombre.
	 * 
	 * @param nombreCarrito Nombre del carrito.
	 * @return DTO del carrito.
	 */
	public CarritoDTO obtenerCarrito(String nombreCarrito) {
		if (nombreCarrito == null || nombreCarrito.isBlank())
			return null;
		CarritoDTO probe = new CarritoDTO();
		probe.setNombre(nombreCarrito);
		return mf.getCarritoDAO().findDTO(probe);
	}

	/**
	 * Agrega un producto al carrito con su nombre y precio.
	 * 
	 * @param nombreCarrito  Nombre del carrito.
	 * @param nombreProducto Nombre del producto.
	 * @param precio         Precio del producto.
	 * @return true si se agregó correctamente, false en caso contrario.
	 */
	public boolean agregarProducto(String nombreCarrito, String nombreProducto, int precio) {
		if (isBlank(nombreCarrito) || isBlank(nombreProducto))
			return false;
		String token = nombreProducto.trim() + "|" + precio;
		CarritoDTO actual = obtenerCarrito(nombreCarrito);
		if (actual == null) {
			actual = new CarritoDTO();
			actual.setNombre(nombreCarrito);
			actual.getListaCarrito().add(token);
			return mf.getCarritoDAO().add(actual);
		} else {
			actual.getListaCarrito().add(token);
			return mf.getCarritoDAO().update(actual, actual);
		}
	}

	/**
	 * Agrega un producto al carrito sin precio (compatibilidad).
	 * 
	 * @param nombreCarrito  Nombre del carrito.
	 * @param nombreProducto Nombre del producto.
	 * @return true si se agregó correctamente, false en caso contrario.
	 */
	public boolean agregarProducto(String nombreCarrito, String nombreProducto) {
		return agregarProducto(nombreCarrito, nombreProducto, 0);
	}

	/**
	 * Lista los ítems del carrito en formato crudo.
	 * 
	 * @param nombreCarrito Nombre del carrito.
	 * @return Lista de tokens de los productos.
	 */
	public List<String> listarItems(String nombreCarrito) {
		CarritoDTO c = obtenerCarrito(nombreCarrito);
		if (c == null || c.getListaCarrito() == null)
			return Collections.emptyList();
		return new ArrayList<>(c.getListaCarrito());
	}

	/**
	 * Lista los ítems del carrito en formato ItemView.
	 * 
	 * @param nombreCarrito Nombre del carrito.
	 * @return Lista de ItemView.
	 */
	public List<ItemView> listarItemsView(String nombreCarrito) {
		List<String> raw = listarItems(nombreCarrito);
		List<ItemView> out = new ArrayList<>();
		for (String token : raw) {
			if (token == null)
				continue;
			String nombre = token;
			int precio = 0;
			int idx = token.lastIndexOf('|');
			if (idx > -1) {
				nombre = token.substring(0, idx).trim();
				String p = token.substring(idx + 1).trim();
				precio = parsePrecioSeguro(p);
			}
			out.add(new ItemView(nombre, precio, token));
		}
		return out;
	}

	/**
	 * Calcula el total del carrito.
	 * 
	 * @param nombreCarrito Nombre del carrito.
	 * @return Total del carrito.
	 */
	public int total(String nombreCarrito) {
		int sum = 0;
		for (ItemView iv : listarItemsView(nombreCarrito)) {
			sum += iv.getPrecio();
		}
		return sum;
	}

	/**
	 * Elimina un ítem del carrito por su token.
	 * 
	 * @param nombreCarrito Nombre del carrito.
	 * @param token         Token del producto a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarItemPorToken(String nombreCarrito, String token) {
		CarritoDTO c = obtenerCarrito(nombreCarrito);
		if (c == null || c.getListaCarrito() == null)
			return false;
		boolean removed = c.getListaCarrito().remove(token);
		return removed && mf.getCarritoDAO().update(c, c);
	}

	/**
	 * Elimina un ítem del carrito por su nombre.
	 * 
	 * @param nombreCarrito Nombre del carrito.
	 * @param item          Nombre del producto a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarItem(String nombreCarrito, String item) {
		CarritoDTO c = obtenerCarrito(nombreCarrito);
		if (c == null || c.getListaCarrito() == null)
			return false;
		boolean removed = c.getListaCarrito().remove(item);
		return removed && mf.getCarritoDAO().update(c, c);
	}

	/**
	 * Vacía el carrito.
	 * 
	 * @param nombreCarrito Nombre del carrito.
	 * @return true si se vació correctamente, false en caso contrario.
	 */
	public boolean vaciar(String nombreCarrito) {
		CarritoDTO c = obtenerCarrito(nombreCarrito);
		if (c == null || c.getListaCarrito() == null)
			return false;
		c.getListaCarrito().clear();
		return mf.getCarritoDAO().update(c, c);
	}

	/**
	 * Verifica si una cadena está vacía o es nula.
	 * 
	 * @param s Cadena a verificar.
	 * @return true si la cadena está vacía o es nula, false en caso contrario.
	 */
	private static boolean isBlank(String s) {
		return s == null || s.trim().isEmpty();
	}

	/**
	 * Parsea un precio de forma segura.
	 * 
	 * @param s Cadena que representa el precio.
	 * @return Precio parseado.
	 */
	private int parsePrecioSeguro(String s) {
		if (s == null)
			return 0;
		String limpio = s.replaceAll("[^0-9-]", "");
		if (limpio.isEmpty() || "-".equals(limpio))
			return 0;
		try {
			return Integer.parseInt(limpio);
		} catch (NumberFormatException e) {
			try {
				long l = Long.parseLong(limpio);
				if (l > Integer.MAX_VALUE)
					return Integer.MAX_VALUE;
				if (l < Integer.MIN_VALUE)
					return Integer.MIN_VALUE;
				return (int) l;
			} catch (NumberFormatException ex) {
				return 0;
			}
		}
	}
}
