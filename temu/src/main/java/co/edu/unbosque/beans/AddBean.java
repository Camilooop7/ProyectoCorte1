package co.edu.unbosque.beans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "addbean")
@ViewScoped
public class AddBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String producto;
	private String subtipo;
	private String subsubtipo;

	private List<String> productos;
	private List<String> subtipos;
	private List<String> subsubtipos;

	public AddBean() {
		productos = new ArrayList<>();
		productos.add("DispositivoElectronico");
		productos.add("Maquillaje");
		productos.add("Juguete");
		productos.add("Peluche");
		productos.add("Papeleria");
		productos.add("Ropa");

		subtipos = new ArrayList<>();
		subsubtipos = new ArrayList<>();
	}

	// Evento al cambiar el producto
	public void onProductoChange() {
		subtipos.clear();
		subsubtipos.clear();
		subsubtipo = null;

		if ("DispositivoElectronico".equals(producto)) {
			subtipos.add("Audifono");
			subtipos.add("Movil");

		} else if ("Maquillaje".equals(producto)) {
			subtipos.add("Labial");
			subtipos.add("Pestanina");

		} else if ("Juguete".equals(producto)) {
			subtipos.add("JuegoMesa");
			subtipos.add("Educativo");

		} else if ("Peluche".equals(producto)) {
			subtipos.add("Pelicula");
			subtipos.add("Animal");

		} else if ("Papeleria".equals(producto)) {
			subtipos.add("Colegio");
			subtipos.add("Oficina");

		} else if ("Ropa".equals(producto)) {
			subtipos.add("Hombre");
			subtipos.add("Mujer");
		}

		if (!subtipos.contains(subtipo)) {
			subtipo = null;
		}
	}

	// Evento al cambiar el subtipo
	public void onSubtipoChange() {
	    subsubtipo = null; // limpiar por defecto

	    if ("Audifono".equals(subtipo)) {
	        subsubtipo = "Tipo de conexión";
	    } else if ("Mujer".equals(subtipo)) {
	        subsubtipo = "Vestidos / Faldas / Blusas";
	    } else if ("Movil".equals(subtipo)) {
	        subsubtipo = "Gama Alta / Media / Baja";
	    }
	}

	// Guardar datos
	public void guardar() {
		System.out.println("Producto: " + producto);
		System.out.println("Subtipo: " + subtipo);
		System.out.println("Sub-subtipo: " + subsubtipo);
	}

	// ===== GETTERS y SETTERS =====
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	public String getSubsubtipo() {
		return subsubtipo;
	}

	public void setSubsubtipo(String subsubtipo) {
		this.subsubtipo = subsubtipo;
	}

	public List<String> getProductos() {
		return productos;
	}

	public void setProductos(List<String> productos) {
		this.productos = productos;
	}

	public List<String> getSubtipos() {
		return subtipos;
	}

	public void setSubtipos(List<String> subtipos) {
		this.subtipos = subtipos;
	}

	public List<String> getSubsubtipos() {
		return subsubtipos;
	}

	public void setSubsubtipos(List<String> subsubtipos) {
		this.subsubtipos = subsubtipos;
	}
}
