package co.edu.unbosque.beans;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import co.edu.unbosque.service.PrincipalService;
import co.edu.unbosque.service.CarritoService;

import co.edu.unbosque.model.*;

/**
 * Bean para gestionar la página principal del sistema. Muestra los productos
 * disponibles y permite eliminarlos o agregarlos al carrito.
 */
@Named("paginaprincipalbean")
@ViewScoped
public class PrincipalBean implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	// Estado de la UI
	private boolean modoEliminar = false;

	/** Clase interna que representa un producto en la UI. */
	public static class Producto {
		private int id;
		private String tipo;
		private String categoria;
		private String nombre;
		private String imagen;
		private String precio;

		public Producto(int id, String tipo, String categoria, String nombre, String imagen, String precio) {
			this.id = id;
			this.tipo = tipo;
			this.categoria = categoria;
			this.nombre = nombre;
			this.imagen = imagen;
			this.precio = precio;
		}

		// Getters
		public int getId() {
			return id;
		}

		public String getTipo() {
			return tipo;
		}

		public String getCategoria() {
			return categoria;
		}

		public String getNombre() {
			return nombre;
		}

		public String getImagen() {
			return imagen;
		}

		public String getPrecio() {
			return precio;
		}
	}

	private List<Producto> productos = new ArrayList<>();
	private Producto productoSeleccionado;

	// Servicios
	@Inject
	private PrincipalService principalService;
	private String nombreCarrito = "CarritoGlobal";
	private transient CarritoService carritoService = new CarritoService();

	/**
	 * Inicializa el bean y carga los productos.
	 */
	@PostConstruct
	public void init() {
		cargarProductos();
	}

	/**
	 * Carga los productos desde el servicio.
	 */
	public void cargarProductos() {
		List<Producto> lista = new ArrayList<>();
		int i = 1;
		for (Movil m : principalService.listarMoviles()) {
			lista.add(new Producto(i++, "Dispositivo - Móvil", "Movil", s(m::getNombre), s(m::getImagen),
					String.valueOf(m.getPrecio())));
		}
		for (Audifono a : principalService.listarAudifonos()) {
			lista.add(new Producto(i++, "Dispositivo - Audífono", "Audifono", s(a::getNombre), s(a::getImagen),
					String.valueOf(a.getPrecio())));
		}
		for (Pelicula p : principalService.listarPeliculas()) {
			lista.add(new Producto(i++, "Peluche - Pelicula", "Pelicula", s(p::getNombre), s(p::getImagen),
					String.valueOf(p.getPrecio())));
		}
		for (Animal a : principalService.listarAnimal()) {
			lista.add(new Producto(i++, "Peluche - Animal", "Animal", s(a::getNombre), s(a::getImagen),
					String.valueOf(a.getPrecio())));
		}
		for (Labial l : principalService.listarLabiales()) {
			lista.add(new Producto(i++, "Maquillaje - Labial", "Labial", s(l::getNombre), s(l::getImagen),
					String.valueOf(l.getPrecio())));
		}
		for (Pestanina p : principalService.listarPestaninas()) {
			lista.add(new Producto(i++, "Maquillaje - Pestañina", "Pestanina", s(p::getNombre), s(p::getImagen),
					String.valueOf(p.getPrecio())));
		}
		for (JuegoMesa j : principalService.listarJuegoMesa()) {
			lista.add(new Producto(i++, "Juguete - Juego de mesa", "JuegoMesa", s(j::getNombre), s(j::getImagen),
					String.valueOf(j.getPrecio())));
		}
		for (Colegio c : principalService.listarColegios()) {
			lista.add(new Producto(i++, "Papelería - Colegio", "Colegio", s(c::getNombre), s(c::getImagen),
					String.valueOf(c.getPrecio())));
		}
		for (Oficina o : principalService.listarOficinas()) {
			lista.add(new Producto(i++, "Papelería - Oficina", "Oficina", s(o::getNombre), s(o::getImagen),
					String.valueOf(o.getPrecio())));
		}
		for (Hombre h : principalService.listarHombres()) {
			lista.add(new Producto(i++, "Ropa - Hombre", "Hombre", s(h::getNombre), s(h::getImagen),
					String.valueOf(h.getPrecio())));
		}
		for (Mujer mu : principalService.listarMujeres()) {
			lista.add(new Producto(i++, "Ropa - Mujer", "Mujer", s(mu::getNombre), s(mu::getImagen),
					String.valueOf(mu.getPrecio())));
		}
		this.productos = lista;
	}

	/**
	 * Cambia el modo de eliminación.
	 */
	public void cambiarModoEliminar() {
		modoEliminar = !modoEliminar;
	}

	/**
	 * Elimina el producto seleccionado.
	 */
	public void eliminarProducto() {
		if (productoSeleccionado == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se seleccionó producto."));
			return;
		}
		String categoria = productoSeleccionado.getCategoria();
		String nombre = productoSeleccionado.getNombre();
		boolean eliminado = false;
		try {
			switch (categoria) {
			case "Audifono":
				eliminado = principalService.eliminarAudifono(nombre);
				break;
			case "Movil":
				eliminado = principalService.eliminarMovil(nombre);
				break;
			case "Labial":
				eliminado = principalService.eliminarLabial(nombre);
				break;
			case "Pestanina":
				eliminado = principalService.eliminarPestanina(nombre);
				break;
			case "JuegoMesa":
				eliminado = principalService.eliminarJuegoMesa(nombre);
				break;
			case "Colegio":
				eliminado = principalService.eliminarColegio(nombre);
				break;
			case "Oficina":
				eliminado = principalService.eliminarOficina(nombre);
				break;
			case "Hombre":
				eliminado = principalService.eliminarHombre(nombre);
				break;
			case "Mujer":
				eliminado = principalService.eliminarMujer(nombre);
				break;
			case "Pelicula":
				eliminado = principalService.eliminarPelicula(nombre);
				break;
			case "Animal":
				eliminado = principalService.eliminarAnimal(nombre);
				break;
			default:
				eliminado = false;
			}
		} catch (Exception e) {
			eliminado = false;
		}
		if (eliminado) {
			cargarProductos();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Producto eliminado correctamente."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el producto."));
		}
		productoSeleccionado = null;
	}

	/**
	 * Agrega el producto seleccionado al carrito.
	 */
	public void comprarProducto() {
		if (productoSeleccionado == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se seleccionó producto."));
			return;
		}
		String nombre = productoSeleccionado.getNombre();
		int precio = 0;
		try {
			precio = Integer.parseInt(productoSeleccionado.getPrecio().replaceAll("[^0-9-]", ""));
		} catch (Exception ignored) {
		}
		boolean ok = carritoService.agregarProducto(nombreCarrito, nombre, precio);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(ok ? FacesMessage.SEVERITY_INFO : FacesMessage.SEVERITY_ERROR,
						ok ? "Añadido" : "Error",
						ok ? (nombre + " agregado al carrito por $" + precio) : "No se pudo agregar."));
		productoSeleccionado = null;
	}

	/**
	 * Ejecuta la acción correspondiente según el modo actual (eliminar o comprar).
	 */
	public void accionProducto() {
		if (modoEliminar)
			eliminarProducto();
		else
			comprarProducto();
	}

	/**
	 * Obtiene la fuente de la imagen del producto.
	 * 
	 * @param p Producto.
	 * @return Fuente de la imagen.
	 */
	public String imagenSrc(Producto p) {
		if (p == null)
			return placeholder();
		String img = p.getImagen();
		if (img == null || img.isBlank())
			return placeholder();
		String lower = img.toLowerCase();
		if (lower.startsWith("http://") || lower.startsWith("https://") || lower.startsWith("data:"))
			return img;
		boolean base64ish = img.matches("^[A-Za-z0-9+/=\\r\\n]+$") && img.length() > 100;
		if (base64ish)
			return "data:image/png;base64," + img.replaceAll("\\s+", "");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String ctx = ec.getRequestContextPath();
		if (!img.startsWith("/"))
			img = "/" + img;
		return ctx + img;
	}

	/**
	 * Obtiene un placeholder para imágenes no disponibles.
	 * 
	 * @return URL del placeholder.
	 */
	private String placeholder() {
		return "https://via.placeholder.com/400x300?text=Sin+imagen";
	}

	/**
	 * Ejecuta un proveedor de forma segura.
	 * 
	 * @param sup Proveedor de cadenas.
	 * @return Cadena resultante o cadena vacía en caso de error.
	 */
	private String s(Supplier<String> sup) {
		try {
			String v = sup.get();
			return v == null ? "" : v;
		} catch (Exception e) {
			return "";
		}
	}

	public boolean isModoEliminar() {
		return modoEliminar;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}
}
